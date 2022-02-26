package com.itmo.banks.Tools;

public class NotEnoughMoneyToWithdrawException extends RuntimeException {
    public NotEnoughMoneyToWithdrawException(String message) {
        super(message);
    }
}
