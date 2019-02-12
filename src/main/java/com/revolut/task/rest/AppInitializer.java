package com.revolut.task.rest;

import com.revolut.task.rest.controller.AccountController;
import com.revolut.task.rest.service.impl.AccountServiceImpl;

public class AppInitializer {

    public static void main(String[] args) {
        new AccountController(new AccountServiceImpl());
    }
}
