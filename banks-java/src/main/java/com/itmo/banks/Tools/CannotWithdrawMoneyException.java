package com.itmo.banks.Tools;

public class CannotWithdrawMoneyException extends Exception {
    public CannotWithdrawMoneyException(String message) {
        super(message);
    }
}
