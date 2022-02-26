package com.itmo.banks.Tools;

public class DoubtfulAccountException extends RuntimeException {
    public DoubtfulAccountException(String message) {
        super(message);
    }
}
