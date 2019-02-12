package com.revolut.task.rest.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private final long id;
    private final String name;
    private BigDecimal balance = new BigDecimal(0);

    public Account(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Account(long id, String name, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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
        return id == account.id &&
                name.equals(account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
