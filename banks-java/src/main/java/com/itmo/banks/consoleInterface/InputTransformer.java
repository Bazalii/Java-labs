package com.itmo.banks.consoleInterface;

import com.itmo.banks.banksStructure.implementations.Client;
import com.itmo.banks.banksStructure.implementations.ClientBuilder;

import java.util.Objects;

public class InputTransformer {
    public Client CreateClient(String name, String surname, String address, String passportNumber) {
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.setName(name);
        clientBuilder.setSurname(surname);
        if (!Objects.equals(address, "")) {
            clientBuilder.setAddress(address);
        }

        if (!Objects.equals(passportNumber, "")) {
            clientBuilder.setPassportNumber(passportNumber);
        }

        return clientBuilder.getResult();
    }
}
