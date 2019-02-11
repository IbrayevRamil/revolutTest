package com.revolut.task.rest.dao.impl;

import com.revolut.task.rest.dao.AccountDao;
import com.revolut.task.rest.datasource.Storage;
import com.revolut.task.rest.model.Account;
import spark.utils.Assert;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentMap;

public class AccountDaoImpl implements AccountDao {

    private static final ConcurrentMap<Long, Account> ACCOUNT_STORAGE = Storage.ACCOUNT__STORAGE.getInstance();

    @Override
    public Account create(Account newAccount) {
        return ACCOUNT_STORAGE.putIfAbsent(newAccount.getId(), newAccount);
    }

    @Override
    public Account read(long accountId) {
        return ACCOUNT_STORAGE.get(accountId);
    }

    @Override
    public void transferMoney(Account fromAccount, Account toAccount, BigDecimal amount) {
        Assert.notNull(fromAccount, "fromAccount couldn't be null");
        Assert.notNull(toAccount, "toAccount couldn't be null");
        Assert.notNull(amount, "amount couldn't be null");

        BigDecimal newBalanceFromAccount = fromAccount.getBalance().subtract(amount);
        BigDecimal newBalanceToAccount = toAccount.getBalance().add(amount);
        fromAccount.setBalance(newBalanceFromAccount);
        toAccount.setBalance(newBalanceToAccount);
    }
}

