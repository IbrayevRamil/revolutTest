package com.revolut.task.rest.dao;

import com.revolut.task.rest.model.Account;

public interface AccountDao {

    Account create(Account newAccount);

    Account read(long accountId);
}
