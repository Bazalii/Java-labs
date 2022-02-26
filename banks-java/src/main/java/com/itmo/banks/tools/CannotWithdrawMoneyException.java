package com.itmo.banks.tools;

public class CannotWithdrawMoneyException extends RuntimeException {
    public CannotWithdrawMoneyException(String message) {
        super(message);
    }
}
