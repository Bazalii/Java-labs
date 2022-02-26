package com.itmo.banks.Tools;

public class ClientWithoutNecessaryField extends RuntimeException {
    public ClientWithoutNecessaryField(String message) {
        super(message);
    }
}
