package com.itmo.banks.banksStructure.implementations;

import com.itmo.banks.tools.ClientWithoutNecessaryField;

public class ClientBuilder {

    private Client _client = new Client();

    public ClientBuilder() {
    }

    public void reset() {
        _client = new Client();
    }

    public void setName(String name) {
        _client.setName(name);
    }

    public void setSurname(String surname) {
        _client.setSurname(surname);
    }

    public void setAddress(String address) {
        _client.setAddress(address);
    }

    public void setPassportNumber(String passportNumber) {
        _client.setPassportNumber(passportNumber);
    }

    public Client getResult() {
        if (_client.getName() == null || _client.getSurname() == null)
            throw new ClientWithoutNecessaryField("Client should have name!");
        Client client = _client;

        reset();

        return client;
    }
}
