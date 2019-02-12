package com.revolut.task.rest;

public enum Messages {

    SUCCESS_TRANSFER("Money has been successfully transferred from [%s] to [%s]. Amount: %s."),
    INSUFFICIENT_MONEY("Transfer couldn't be completed due to insufficient money on the account."),
    SUCCESS_CREATION("Account has been successfully created. ID: %d."),
    UNKNOWN_ERROR("Transfer couldn't be completed due to unknown error. Please, try again later.");

    private final String msg;

    Messages(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
