package com.itmo.banks.Tools;

public class NotEnoughMoneyToWithdrawException extends Exception {
    public NotEnoughMoneyToWithdrawException(String message) {
        super(message);
    }
}
