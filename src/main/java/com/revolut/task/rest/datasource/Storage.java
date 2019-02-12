package com.revolut.task.rest.datasource;

import com.revolut.task.rest.model.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton instance of storage
 */
public enum Storage {

    ACCOUNT_STORAGE;

    private final Map<Long, Account> instance = new HashMap<>();

    public Map<Long, Account> getInstance() {
        return instance;
    }
}
