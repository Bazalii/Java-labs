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

    public abstract void AddAccount(Account account);

    public abstract void RemoveAccount(Account account);

    public abstract void CloseAccount(Account account);

    public abstract void CreateDepositAccount(Client client, float amountOfMoney);

    public abstract void CloseDepositAccount(Account account);

    public abstract void CreateDebitAccount(Client client, float amountOfMoney);

    public abstract void CreateCreditAccount(Client client, float amountOfMoney);

    public abstract void RegisterAccountAndClient(Account account, Client client);

    public abstract void RegisterClient(Client client);

    public abstract Boolean CheckIfClientRegistered(Client client);

    public abstract Client GetClientByAccount(Account account);

    public abstract Boolean GetClientDoubtfulness(Client client);

    public abstract Boolean CheckIfMonthPassed(Account account);

    public abstract void AddDailyIncome();

    public abstract void ReduceDaysLeft(Account account);

    public void SetId(int id) {
        Id = id;
    }

    public int GetId() {
        return Id;
    }

    public String GetName() {
        return Name;
    }
}
