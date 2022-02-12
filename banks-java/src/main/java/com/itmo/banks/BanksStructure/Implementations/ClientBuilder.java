package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.Tools.ClientWithoutNecessaryField;

public class ClientBuilder {
    private Client _client = new Client();

    public ClientBuilder() {
    }

    public void Reset() {
        _client = new Client();
    }

    public void SetName(String name) {
        _client.SetName(name);
    }

    public void SetSurname(String surname) {
        _client.SetSurname(surname);
    }

    public void SetAddress(String address) {
        _client.SetAddress(address);
    }

    public void SetPassportNumber(String passportNumber) {
        _client.SetPassportNumber(passportNumber);
    }

    public Client GetResult() throws ClientWithoutNecessaryField {
        if (_client.GetName() == null || _client.GetSurname() == null)
            throw new ClientWithoutNecessaryField("Client should have name!");

        Client client = _client;
        Reset();
        return client;
    }
}
