package com.revolut.task.rest.service;

import com.revolut.task.rest.dto.AccountDto;
import com.revolut.task.rest.model.Account;

public interface AccountService {

    String add(AccountDto accountDto);

    Account get(String accountId);

    String transferMoney(String from, String to, String amount);
}
