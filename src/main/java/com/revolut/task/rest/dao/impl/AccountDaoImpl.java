package com.revolut.task.rest.dao.impl;

import com.revolut.task.rest.dao.AccountDao;
import com.revolut.task.rest.datasource.Storage;
import com.revolut.task.rest.model.Account;

import java.util.concurrent.ConcurrentMap;

public class AccountDaoImpl implements AccountDao {

    private static final ConcurrentMap<Long, Account> ACCOUNT_STORAGE = Storage.ACCOUNT__STORAGE.getInstance();

    @Override
    public Account create(Account newAccount) {
        return null;
    }

    @Override
    public Account read(long accountId) {
        return null;
    }
}
