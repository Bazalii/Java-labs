package com.itmo.banks.tools;

public class DoubtfulAccountException extends RuntimeException {
    public DoubtfulAccountException(String message) {
        super(message);
    }
}
