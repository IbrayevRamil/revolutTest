package com.revolut.task.rest.dto;

/**
 * Data transfer object for transfer between account
 */
public class TransferDto {

    private String fromAccount;
    private String toAccount;
    private String amount;

    public TransferDto(String fromAccount, String toAccount, String amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
