package com.revolut.task.rest;

public enum Messages {

    SUCCESS_TRANSFER("Money has been successfully transferred from [%s] to [%s]. Amount: %s."),
    DUPLICATE_ACCOUNT("Account already exists"),
    NO_EXISTING_ACCOUNT("Account doesn't exist"),
    INSUFFICIENT_MONEY("Transfer couldn't be completed due to insufficient money on the account."),
    SUCCESS_CREATION("Account has been successfully created.\n"),
    UNKNOWN_ERROR_TRANSFER("Transfer couldn't be completed due to unknown error. Please, try again later.");

    private final String msg;

    Messages(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
