package com.revolut.task.rest.dao;

import com.revolut.task.rest.model.Account;

import java.math.BigDecimal;

/**
 * Data access layer for Accounts storage.
 */
public interface AccountDao {

    /**
     * Puts new account into storage
     * @param newAccount New account to put
     * @return New account if success, null otherwise
     */
    Account create(Account newAccount);

    /**
     * Gets account from storage
     * @param accountId Account unique id
     * @return Account from storage
     */
    Account read(long accountId);

    /**
     * Transfer specified amount of money from one account to another
     * @param fromAccount Account to withdraw
     * @param toAccount Account to add
     * @param amount Amount of money
     */
    void transferMoney(Account fromAccount, Account toAccount, BigDecimal amount);
}
