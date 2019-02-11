package com.revolut.task.rest.service.impl;

import com.revolut.task.rest.dao.AccountDao;
import com.revolut.task.rest.dao.impl.AccountDaoImpl;
import com.revolut.task.rest.model.Account;
import com.revolut.task.rest.service.AccountService;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService {

    private final AccountDao accountDao = new AccountDaoImpl();

    @Override
    public Account add(Account newAccount) {
        return null;
    }

    @Override
    public Account get(long accountId) {
        return null;
    }

    @Override
    public String transferMoney(long from, long to, BigDecimal amount) {
        Account fromAccount = accountDao.read(from);
        Account toAccount = accountDao.read(to);
        return null;
    }
}
