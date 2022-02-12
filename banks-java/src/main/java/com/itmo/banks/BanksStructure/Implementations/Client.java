package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.Account;
import com.itmo.banks.BanksStructure.IHandler;
import com.itmo.banks.BanksStructure.IMyDisposable;
import com.itmo.banks.BanksStructure.IMyObserver;

import java.util.ArrayList;
import java.util.Objects;

public class Client implements IMyObserver {
    public ArrayList<Account> Accounts = new ArrayList<>();
    private final ArrayList<IMyDisposable> _subscriptionCancellations = new ArrayList<>();
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

    public void Subscribe(IHandler handler) {
        _subscriptionCancellations.add(handler.Subscribe(this));
    }

    public void Unsubscribe(String objectName) {
        for (IMyDisposable cancellation : _subscriptionCancellations) {
            if (Objects.equals(cancellation.GetName(), objectName))
                cancellation.Dispose();
        }
    }

    public String Notify() {
        return GetName() + " is notified!";
    }

    public String GetName() {
        return _name;
    }

    public String GetSurname() {
        return _surname;
    }

    public void SetAddress(String address) {
        _address = address;
        ChangeAccountsDoubtfulness();
    }

    public String GetAddress() {
        return _address;
    }

    public void SetPassportNumber(String passportNumber) {
        _passportNumber = passportNumber;
        ChangeAccountsDoubtfulness();
    }

    public String GetPassportNumber() {
        return _passportNumber;
    }

    public void AddAccount(Account account) {
        Accounts.add(account);
    }

    public void RemoveAccount(Account account) {
        Accounts.remove(account);
    }

    public ArrayList<String> GetAccountIds() {
        ArrayList<String> output = new ArrayList<>();
        for (Account account : Accounts) {
            output.add(account.GetId());
        }
        return output;
    }

    void SetName(String name) {
        _name = name;
    }

    void SetSurname(String surname) {
        _surname = surname;
    }

    private void ChangeAccountsDoubtfulness() {
        for (Account account : Accounts) {
            account.SetDoubtfulness(false);
        }
    }
}