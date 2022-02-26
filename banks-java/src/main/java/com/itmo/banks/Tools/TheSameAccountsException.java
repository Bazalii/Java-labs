package com.itmo.banks.Tools;

public class TheSameAccountsException extends RuntimeException {
    public TheSameAccountsException(String message) {
        super(message);
    }
}
