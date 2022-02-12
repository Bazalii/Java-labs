package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.*;

import java.util.ArrayList;

public class Bank extends BankPrototype {
    private final IHandler _handler;

    public Bank(String name, IPercentCalculator percentCalculator, int accountsTerm, float limitIfDoubtful) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null!");
        Name = name;

        _handler = new PercentChangesHandler(Name);

        if (percentCalculator == null)
            throw new IllegalArgumentException("Percent calculator cannot be null!");
        PercentCalculator = percentCalculator;

        if (accountsTerm <= 0)
            throw new IllegalArgumentException("Term of account should be a positive integer!");
        AccountsTerm = accountsTerm;

        if (limitIfDoubtful <= 0)
            throw new IllegalArgumentException("Limit for account should be a positive float!");
        LimitIfDoubtful = limitIfDoubtful;
    }

    public void subscribe(IMyObserver observer) {
        observer.subscribe(_handler);
    }

    public ArrayList<String> setPercentCalculator(IPercentCalculator percentCalculator) {
        PercentCalculator = percentCalculator;
        return _handler.sendNotifications();
    }

    @Override
    public void addAccount(Account account) {
        Accounts.add(account);
    }

    @Override
    public void removeAccount(Account account) {
        getClientByAccount(account).removeAccount(account);
        Accounts.remove(account);
    }

    @Override
    public void closeAccount(Account account) {
        removeAccount(account);
    }

    @Override
    public void createDepositAccount(Client client, float amountOfMoney) {
        var account = new DepositAccount(
                String.format("%d_%d", Id, AccountIds += 1),
                AccountsTerm,
                PercentCalculator.calculateDepositPercent(amountOfMoney),
                amountOfMoney,
                getClientDoubtfulness(client),
                LimitIfDoubtful);
        registerAccountAndClient(account, client);
    }

    @Override
    public void closeDepositAccount(Account account) {
        createDebitAccount(getClientByAccount(account), account.getAmountOfMoney());
        removeAccount(account);
    }

    @Override
    public void createDebitAccount(Client client, float amountOfMoney) {
        var account = new DebitAccount(
                String.format("%s_%d", Id, AccountIds += 1),
                AccountsTerm,
                PercentCalculator.calculateDebitPercent(amountOfMoney),
                amountOfMoney,
                getClientDoubtfulness(client),
                LimitIfDoubtful);
        registerAccountAndClient(account, client);
    }

    @Override
    public void createCreditAccount(Client client, float amountOfMoney) {
        var account = new CreditAccount(
                String.format("%s_%d", Id, AccountIds += 1),
                AccountsTerm,
                PercentCalculator.calculateCreditCommission(amountOfMoney),
                amountOfMoney,
                getClientDoubtfulness(client),
                LimitIfDoubtful);
        registerAccountAndClient(account, client);
    }

    @Override
    public void registerAccountAndClient(Account account, Client client) {
        Accounts.add(account);
        client.addAccount(account);
        if (!checkIfClientRegistered(client)) {
            registerClient(client);
        }
    }

    @Override
    public void registerClient(Client client) {
        Clients.add(client);
    }

    @Override
    public Boolean checkIfClientRegistered(Client client) {
        return Clients.contains(client);
    }

    @Override
    public Client getClientByAccount(Account account) {
        for (Client client : Clients) {
            if (client.Accounts.contains(account))
                return client;
        }
        return null;
    }

    @Override
    public Boolean getClientDoubtfulness(Client client) {
        return client.getAddress() == null || client.getPassportNumber() == null;
    }

    @Override
    public Boolean checkIfMonthPassed(Account account) {
        return account.getTermAndDaysLeftDiff() % 30 == 0;
    }

    @Override
    public void addDailyIncome() {
        for (Account account : Accounts) {
            account.addDailyIncome();
            reduceDaysLeft(account);
            if (account instanceof SavingsAccount savingsAccount && checkIfMonthPassed(account)) {
                savingsAccount.addMonthlyIncome();
            }
        }
    }

    @Override
    public void reduceDaysLeft(Account account) {
        account.reduceDaysLeft();
    }
}
