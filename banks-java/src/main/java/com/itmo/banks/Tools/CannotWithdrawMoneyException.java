package com.itmo.banks.Tools;

public class CannotWithdrawMoneyException extends RuntimeException {
    public CannotWithdrawMoneyException(String message) {
        super(message);
    }
}
