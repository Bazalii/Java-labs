package com.itmo.banks.tools;

public class TheSameAccountsException extends RuntimeException {
    public TheSameAccountsException(String message) {
        super(message);
    }
}
