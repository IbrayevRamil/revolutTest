package com.revolut.task.rest.dao.impl;

import com.revolut.task.rest.Messages;
import com.revolut.task.rest.dao.AccountDao;
import com.revolut.task.rest.datasource.Storage;
import com.revolut.task.rest.model.Account;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class AccountDaoImpl implements AccountDao {

    private static final Map<Long, Account> ACCOUNT_STORAGE = Storage.ACCOUNT_STORAGE.getInstance();

    @Override
    public Account create(Account newAccount) {
        Objects.requireNonNull(newAccount, "newAccount couldn't be null");
        if (ACCOUNT_STORAGE.putIfAbsent(newAccount.getId(), newAccount) != null) {
            throw new IllegalArgumentException(Messages.DUPLICATE_ACCOUNT.getMsg());
        }
        return newAccount;
    }

    @Override
    public Account read(long accountId) {
        return ACCOUNT_STORAGE.get(accountId);
    }

    @Override
    public void transferMoney(Account fromAccount, Account toAccount, BigDecimal amount) {
        Objects.requireNonNull(fromAccount, "fromAccount couldn't be null");
        Objects.requireNonNull(toAccount, "toAccount couldn't be null");
        Objects.requireNonNull(amount, "amount couldn't be null");
        BigDecimal newBalanceFromAccount = fromAccount.getBalance().subtract(amount);
        BigDecimal newBalanceToAccount = toAccount.getBalance().add(amount);
        fromAccount.setBalance(newBalanceFromAccount);
        toAccount.setBalance(newBalanceToAccount);
    }
}

