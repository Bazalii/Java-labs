package com.itmo.banks.BanksStructure;

import com.itmo.banks.BanksStructure.Implementations.Client;

import java.util.ArrayList;

public abstract class BankPrototype {
    public ArrayList<Account> Accounts = new ArrayList<>();

    protected int Id;

    protected String Name;

    protected int AccountIds;

    protected IPercentCalculator PercentCalculator;

    protected int AccountsTerm;

    protected float LimitIfDoubtful;

    protected ArrayList<Client> Clients = new ArrayList<>();

    public abstract void addAccount(Account account);

    public abstract void removeAccount(Account account);

    public abstract void closeAccount(Account account);

    public abstract void createDepositAccount(Client client, float amountOfMoney);

    public abstract void closeDepositAccount(Account account);

    public abstract void createDebitAccount(Client client, float amountOfMoney);

    public abstract void createCreditAccount(Client client, float amountOfMoney);

    public abstract void registerAccountAndClient(Account account, Client client);

    public abstract void registerClient(Client client);

    public abstract Boolean checkIfClientRegistered(Client client);

    public abstract Client getClientByAccount(Account account);

    public abstract Boolean getClientDoubtfulness(Client client);

    public abstract Boolean checkIfMonthPassed(Account account);

    public abstract void addDailyIncome();

    public abstract void reduceDaysLeft(Account account);

    public void setId(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
}
