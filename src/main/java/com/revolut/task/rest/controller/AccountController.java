package com.revolut.task.rest.controller;

import com.google.gson.Gson;
import com.revolut.task.rest.Messages;
import com.revolut.task.rest.dto.AccountDto;
import com.revolut.task.rest.dto.TransferDto;
import com.revolut.task.rest.model.Account;
import com.revolut.task.rest.service.AccountService;
import spark.Spark;

/**
 * REST API
 */
public class AccountController {

    private final Gson gson = new Gson();

    public AccountController(AccountService accountService) {

        /*
          Creates new account
         */
        Spark.post("/account/create", (req, res) -> {
            AccountDto accountDto = gson.fromJson(req.body(), AccountDto.class);
            return Messages.SUCCESS_CREATION.getMsg() + gson.toJson(accountService.add(accountDto));
        });

        /*
          Gets an account
         */
        Spark.get("/account/:id", (req, res) -> {
            Account account = accountService.get(req.params("id"));
            if (account == null) {
                res.status(404);
                return Messages.NO_EXISTING_ACCOUNT.getMsg();
            }
            return gson.toJson(account);

        });

        /*
          Transfer money from one account to another
         */
        Spark.post("/account/transfer", (req, res) ->
                accountService.transferMoney(gson.fromJson(req.body(), TransferDto.class)));
    }
}
