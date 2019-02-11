package com.revolut.task.rest.dao;

import com.revolut.task.rest.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    Account create(Account newAccount);

    Account read(long accountId);

    void transferMoney(Account fromAccount, Account toAccount, BigDecimal amount);
}
