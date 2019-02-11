package com.revolut.task.rest;

import com.revolut.task.rest.dao.AccountDao;
import com.revolut.task.rest.dao.impl.AccountDaoImpl;
import com.revolut.task.rest.model.Account;

import java.math.BigDecimal;

public class AppInitializer {

    public static void main(String[] args) {
        AccountDao accountDao = new AccountDaoImpl();
        accountDao.create(new Account(0, new BigDecimal(1240)));
        accountDao.create(new Account(1, new BigDecimal(1240)));
        accountDao.transferMoney(accountDao.read(0), accountDao.read(1), new BigDecimal(240));
        System.out.println("success");
    }
}
