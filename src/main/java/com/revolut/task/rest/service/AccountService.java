package com.revolut.task.rest.service;

import com.revolut.task.rest.model.Account;

public interface AccountService {

    Account add(Account newAccount);

    Account get(long accountId);


}
