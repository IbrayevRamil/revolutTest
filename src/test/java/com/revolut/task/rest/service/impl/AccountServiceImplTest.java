package com.revolut.task.rest.service.impl;

import com.revolut.task.rest.datasource.Storage;
import com.revolut.task.rest.dto.AccountDto;
import com.revolut.task.rest.dto.TransferDto;
import com.revolut.task.rest.model.Account;
import com.revolut.task.rest.service.AccountService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

public class AccountServiceImplTest {

    private final Map<Long, Account> TESTING_STORAGE = Storage.ACCOUNT_STORAGE.getInstance();
    private final AccountService accountService = new AccountServiceImpl();

    @After
    public void after() {
        TESTING_STORAGE.clear();
    }

    @Test
    public void add() {
        accountService.add(new AccountDto("Test", "3000"));
        Account account = new Account(0L, "Test");
        Assert.assertNotNull(TESTING_STORAGE.get(0L));
        Assert.assertEquals(account, TESTING_STORAGE.get(0L));
    }

    @Test
    public void get() {
        Account account = new Account(0L, "visa", new BigDecimal(2000));
        TESTING_STORAGE.put(account.getId(), account);
        Assert.assertNotNull(accountService.get("0"));
        Assert.assertEquals(account, accountService.get("0"));
    }

    @Test
    public void transferMoney() {
        Account from = new Account(0L, "visa", new BigDecimal(2000));
        Account to = new Account(1L, "mastercard", new BigDecimal(2000));
        TESTING_STORAGE.put(from.getId(), from);
        TESTING_STORAGE.put(to.getId(), to);
        TransferDto transferDto = new TransferDto(
                String.valueOf(from.getId()),
                String.valueOf(to.getId()),
                "2000");
        accountService.transferMoney(transferDto);
        Assert.assertEquals(new BigDecimal(0), TESTING_STORAGE.get(from.getId()).getBalance());
        Assert.assertEquals(new BigDecimal(4000), TESTING_STORAGE.get(to.getId()).getBalance());
    }
}