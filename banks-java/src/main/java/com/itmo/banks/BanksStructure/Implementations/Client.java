package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.Account;
import com.itmo.banks.BanksStructure.IHandler;
import com.itmo.banks.BanksStructure.IMyDisposable;
import com.itmo.banks.BanksStructure.IMyObserver;

import java.util.ArrayList;
import java.util.Objects;

public class Client implements IMyObserver {
    private final ArrayList<IMyDisposable> _subscriptionCancellations = new ArrayList<>();

    public ArrayList<Account> Accounts = new ArrayList<>();

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
        for (IMyDisposable cancellation : _subscriptionCancellations) {
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

    public String getSurname() {
        return _surname;
    }

    public void setAddress(String address) {
        _address = address;
        changeAccountsDoubtfulness();
    }

    public String getAddress() {
        return _address;
    }

    public void setPassportNumber(String passportNumber) {
        _passportNumber = passportNumber;
        changeAccountsDoubtfulness();
    }

    public String getPassportNumber() {
        return _passportNumber;
    }

    public void addAccount(Account account) {
        Accounts.add(account);
    }

    public void removeAccount(Account account) {
        Accounts.remove(account);
    }

    public ArrayList<String> getAccountIds() {
        ArrayList<String> output = new ArrayList<>();
        for (Account account : Accounts) {
            output.add(account.getId());
        }
        return output;
    }

    void setName(String name) {
        _name = name;
    }

    void setSurname(String surname) {
        _surname = surname;
    }

    private void changeAccountsDoubtfulness() {
        for (Account account : Accounts) {
            account.setDoubtfulness(false);
        }
    }
}