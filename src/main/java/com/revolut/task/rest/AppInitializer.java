package com.revolut.task.rest;

import com.revolut.task.rest.controller.AccountController;
import com.revolut.task.rest.dao.impl.AccountDaoImpl;
import com.revolut.task.rest.service.impl.AccountServiceImpl;
import spark.Spark;

public class AppInitializer {

    public static void main(String[] args) {
        Spark.port(8080);
        new AccountController(new AccountServiceImpl(new AccountDaoImpl()));
    }
}
