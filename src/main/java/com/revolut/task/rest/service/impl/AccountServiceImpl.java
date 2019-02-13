package com.revolut.task.rest.service.impl;

import com.revolut.task.rest.Messages;
import com.revolut.task.rest.dao.AccountDao;
import com.revolut.task.rest.dto.AccountDto;
import com.revolut.task.rest.dto.TransferDto;
import com.revolut.task.rest.model.Account;
import com.revolut.task.rest.service.AccountService;
import spark.utils.Assert;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class AccountServiceImpl implements AccountService {

    private static AtomicLong uniqueIdCounter = new AtomicLong(0L);
    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account add(AccountDto accountDto) {
        Account account = new Account(
                uniqueIdCounter.getAndIncrement(),
                accountDto.getName(),
                new BigDecimal(accountDto.getBalance()));
        return accountDao.create(account);
    }

    @Override
    public Account get(String accountId) {
        return accountDao.read(Long.parseLong(accountId));
    }

    @Override
    public String transferMoney(TransferDto transferDto) {
        Account fromAccount = accountDao.read(Long.parseLong(transferDto.getFromAccount()));
        Account toAccount = accountDao.read(Long.parseLong(transferDto.getToAccount()));
        Objects.requireNonNull(fromAccount,
                String.format("Account with ID: %s doesn't exist", transferDto.getFromAccount()));
        Objects.requireNonNull(toAccount,
                String.format("Account with ID: %s doesn't exist", transferDto.getToAccount()));
        BigDecimal bigDecimalAmount = new BigDecimal(transferDto.getAmount());
        Assert.isTrue(bigDecimalAmount.compareTo(new BigDecimal(0)) > 0,
                "Amount can't be negative");
        Account[] sortedAccounts = {fromAccount, toAccount};
        Arrays.sort(sortedAccounts, Comparator.comparingLong(Account::getId));
        synchronized (sortedAccounts[0]) {
            synchronized (sortedAccounts[1]) {
                if (fromAccount.getBalance().compareTo(bigDecimalAmount) >= 0) {
                    accountDao.transferMoney(fromAccount, toAccount, bigDecimalAmount);
                } else {
                    return Messages.INSUFFICIENT_MONEY.getMsg();
                }
            }
        }
        return String.format(Messages.SUCCESS_TRANSFER.getMsg(),
                transferDto.getFromAccount(),
                transferDto.getToAccount(),
                transferDto.getAmount());
    }
}
