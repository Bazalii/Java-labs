package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.Account;
import com.itmo.banks.BanksStructure.IDisposable;
import com.itmo.banks.BanksStructure.IHandler;
import com.itmo.banks.BanksStructure.IObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client implements IObserver {
    private final List<IDisposable> _subscriptionCancellations = new ArrayList<>();

    public List<Account> accounts = new ArrayList<>();

    private String _name;

    private String _surname;

    private String _address;

    private String _passportNumber;

    public Client(String name, String surname, String address, String passportNumber) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null!");
        _name = name;

        if (surname == null)
            throw new IllegalArgumentException("Surname cannot be null!");
        _surname = surname;

        _address = address;
        _passportNumber = passportNumber;
    }

    public Client() {
    }

    public void subscribe(IHandler handler) {
        _subscriptionCancellations.add(handler.subscribe(this));
    }

    public void unsubscribe(String objectName) {
        for (IDisposable cancellation : _subscriptionCancellations) {
            if (Objects.equals(cancellation.getName(), objectName))
                cancellation.dispose();
        }
    }

    public String showNotification() {
        return getName() + " is notified!";
    }

    public String getName() {
        return _name;
    }

    void setName(String name) {
        _name = name;
    }

    public String getSurname() {
        return _surname;
    }

    void setSurname(String surname) {
        _surname = surname;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String address) {
        _address = address;
        changeAccountsDoubtfulness();
    }

    public String getPassportNumber() {
        return _passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        _passportNumber = passportNumber;
        changeAccountsDoubtfulness();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public List<String> getAccountIds() {
        List<String> output = new ArrayList<>();
        for (Account account : accounts) {
            output.add(account.getId());
        }
        return output;
    }

    private void changeAccountsDoubtfulness() {
        for (Account account : accounts) {
            account.setDoubtfulness(false);
        }
    }
}