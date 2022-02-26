package com.itmo.banks.ConsoleInterface;

import com.itmo.banks.BanksStructure.Implementations.Client;
import com.itmo.banks.BanksStructure.Implementations.ClientBuilder;
import com.itmo.banks.Tools.ClientWithoutNecessaryField;

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
