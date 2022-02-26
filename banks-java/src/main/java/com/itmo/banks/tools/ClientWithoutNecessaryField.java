package com.itmo.banks.tools;

public class ClientWithoutNecessaryField extends RuntimeException {
    public ClientWithoutNecessaryField(String message) {
        super(message);
    }
}
