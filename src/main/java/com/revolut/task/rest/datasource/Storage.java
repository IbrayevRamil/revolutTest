package com.revolut.task.rest.datasource;

import com.revolut.task.rest.model.Account;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Singleton instance of storage
 */
public enum Storage {

    ACCOUNT_STORAGE;

    private final ConcurrentMap<Long, Account> instance = new ConcurrentHashMap<>();

    public ConcurrentMap<Long, Account> getInstance() {
        return instance;
    }
}
