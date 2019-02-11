package com.revolut.task.rest.model;

import java.math.BigDecimal;

public class Account {

    private final long id;
    private BigDecimal balance;

    public Account(long id) {
        this.id = id;
        this.balance = new BigDecimal(0);
    }

    public Account(long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
