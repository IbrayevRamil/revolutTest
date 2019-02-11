package com.revolut.task.rest.service;

import com.revolut.task.rest.model.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account add(Account newAccount);

    Account get(long accountId);

    String transferMoney(long from, long to, BigDecimal amount);
}
