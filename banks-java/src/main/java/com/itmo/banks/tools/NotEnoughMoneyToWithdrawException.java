package com.itmo.banks.tools;

public class NotEnoughMoneyToWithdrawException extends RuntimeException {
    public NotEnoughMoneyToWithdrawException(String message) {
        super(message);
    }
}
