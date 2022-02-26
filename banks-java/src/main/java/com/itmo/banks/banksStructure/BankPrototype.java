package com.itmo.banks.banksStructure;

import com.itmo.banks.banksStructure.implementations.Client;

import java.util.ArrayList;
import java.util.List;

public abstract class BankPrototype {
    public List<Account> accounts = new ArrayList<>();

    public List<Client> clients = new ArrayList<>();

    private int id;

    private String name;

    private int accountIds;

    private IPercentCalculator percentCalculator;

    private int accountsTerm;

    private float limitIfDoubtful;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountIds() {
        return accountIds;
    }

    public int setAccountIds(int accountIds) {
        this.accountIds = accountIds;
        return accountIds;
    }

    public IPercentCalculator getPercentCalculator() {
        return percentCalculator;
    }

    public void setPercentCalculator(IPercentCalculator percentCalculator) {
        this.percentCalculator = percentCalculator;
    }

    public int getAccountsTerm() {
        return accountsTerm;
    }

    public void setAccountsTerm(int accountsTerm) {
        this.accountsTerm = accountsTerm;
    }

    public float getLimitIfDoubtful() {
        return limitIfDoubtful;
    }

    public void setLimitIfDoubtful(float limitIfDoubtful) {
        this.limitIfDoubtful = limitIfDoubtful;
    }
}
