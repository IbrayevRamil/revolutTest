package com.revolut.task.rest.service.impl;

import com.revolut.task.rest.dao.impl.AccountDaoImpl;
import com.revolut.task.rest.dto.AccountDto;
import com.revolut.task.rest.dto.TransferDto;
import com.revolut.task.rest.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;


@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    @Mock
    private AccountDaoImpl accountDao;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    public void add() {
        AccountDto account = new AccountDto("visa", "333");
        accountService.add(account);
        Mockito.lenient().when(accountService.add(account))
                .thenReturn(new Account(0L, "visa", new BigDecimal(333)));
    }

    @Test
    public void get() {
        accountService.get("0");
        Mockito.verify(accountDao)
                .read(Long.parseLong("0"));
    }

    @Test(expected = NullPointerException.class)
    public void transferMoney() {
        TransferDto transferDto = new TransferDto("0", "1", "222");
        Mockito.when(accountService
                .transferMoney(transferDto))
                .thenThrow(new NullPointerException("Account with ID: 0 doesn't exist"));
    }
}