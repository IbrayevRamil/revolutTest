package com.revolut.task.rest.controller;

import com.google.gson.Gson;
import com.revolut.task.rest.dto.AccountDto;
import com.revolut.task.rest.service.AccountService;
import spark.Spark;

public class AccountController {

    private final Gson gson = new Gson();

    public AccountController(AccountService accountService) {
        Spark.post("/account/create", (req, res) -> {
            AccountDto accountDto = gson.fromJson(req.body(), AccountDto.class);
            return accountService.add(accountDto);
        });

        Spark.get("/account/:id", (req, res) -> {
            res.type("application/json");
            return gson.toJson(accountService.get(req.params("id")));
        });

        Spark.get("/account/transfer", (req, res) ->
                accountService.transferMoney(
                        req.queryParams("from"),
                        req.queryParams("to"),
                        req.queryParams("amount")));
    }
}
