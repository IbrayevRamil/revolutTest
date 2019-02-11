package com.revolut.task.rest.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private final long id;
    private BigDecimal balance = new BigDecimal(0);

    public Account(long id) {
        this.id = id;
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
    public synchronized void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
