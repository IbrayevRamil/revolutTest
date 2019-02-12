package com.revolut.task.rest.service;

import com.revolut.task.rest.dto.AccountDto;
import com.revolut.task.rest.dto.TransferDto;
import com.revolut.task.rest.model.Account;

/**
 * Service layer for working with accounts
 */
public interface AccountService {

    /**
     * Add account into the storage
     * @param accountDto accountDto
     * @return Result of operation
     */
    Account add(AccountDto accountDto);

    /**
     * Gets account from storage
     * @param accountId accountId
     * @return Account from storage
     */
    Account get(String accountId);

    /**
     * Transfer specified amount of money from one account to another
     * @param transferDto Transfer dto containing information for transfer
     * @return Result of operation
     */
    String transferMoney(TransferDto transferDto);
}
