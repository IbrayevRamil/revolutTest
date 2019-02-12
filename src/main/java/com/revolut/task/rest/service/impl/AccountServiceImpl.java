package com.revolut.task.rest.service.impl;

import com.revolut.task.rest.Messages;
import com.revolut.task.rest.dao.AccountDao;
import com.revolut.task.rest.dao.impl.AccountDaoImpl;
import com.revolut.task.rest.dto.AccountDto;
import com.revolut.task.rest.model.Account;
import com.revolut.task.rest.service.AccountService;
import spark.utils.Assert;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

public class AccountServiceImpl implements AccountService {

    private static AtomicLong uniqueIdCounter = new AtomicLong(0L);
    private final AccountDao accountDao = new AccountDaoImpl();

    @Override
    public String add(AccountDto accountDto) {
        Account account = new Account(
                uniqueIdCounter.getAndIncrement(),
                accountDto.getName(),
                new BigDecimal(accountDto.getBalance()));
        return accountDao.create(account) == null
                ? String.format(Messages.SUCCESS_CREATION.getMsg(), account.getId())
                : Messages.UNKNOWN_ERROR.getMsg();
    }

    @Override
    public Account get(String accountId) {
        return accountDao.read(new Long(accountId));
    }

    @Override
    public String transferMoney(String from, String to, String amount) {
        Account fromAccount = accountDao.read(new Long(from));
        Account toAccount = accountDao.read(new Long(to));
        Assert.notNull(fromAccount, String.format("Account with ID: %s doesn't exist", from));
        Assert.notNull(to, String.format("Account with ID: %s doesn't exist", to));
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(toAccount.getBalance()) >= 0) {
                    accountDao.transferMoney(fromAccount, toAccount, new BigDecimal(amount));
                } else {
                    return Messages.INSUFFICIENT_MONEY.getMsg();
                }
            }
        }
        return String.format(Messages.SUCCESS_TRANSFER.getMsg(), from, to, amount);
    }
}
