package com.revolut.task.rest.service;

public enum AccountMoneyTransferResult {

    SUCCESS("Money has been successfully transferred from [%s] to [%s]. Amount: %e"),
    INSUFFICIENT_MONEY("Transfer couldn't be completed due to insufficient money on the account"),
    UNKNOWN_ERROR("Transfer couldn't be completed due to unknown error. Please, try again later.");

    private final String msg;

    AccountMoneyTransferResult(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
