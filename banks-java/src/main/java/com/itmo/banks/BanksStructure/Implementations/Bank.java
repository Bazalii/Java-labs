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

    public void Subscribe(IMyObserver observer) {
        observer.Subscribe(_handler);
    }

    public ArrayList<String> SetPercentCalculator(IPercentCalculator percentCalculator) {
        PercentCalculator = percentCalculator;
        return _handler.Notify();
    }

    @Override
    public void AddAccount(Account account) {
        Accounts.add(account);
    }

    @Override
    public void RemoveAccount(Account account) {
        GetClientByAccount(account).RemoveAccount(account);
        Accounts.remove(account);
    }

    @Override
    public void CloseAccount(Account account) {
        RemoveAccount(account);
    }

    @Override
    public void CreateDepositAccount(Client client, float amountOfMoney) {
        var account = new DepositAccount(
                String.format("%d_%d", Id, AccountIds += 1),
                AccountsTerm,
                PercentCalculator.CalculateDepositPercent(amountOfMoney),
                amountOfMoney,
                GetClientDoubtfulness(client),
                LimitIfDoubtful);
        RegisterAccountAndClient(account, client);
    }

    @Override
    public void CloseDepositAccount(Account account) {
        CreateDebitAccount(GetClientByAccount(account), account.GetAmountOfMoney());
        RemoveAccount(account);
    }

    @Override
    public void CreateDebitAccount(Client client, float amountOfMoney) {
        var account = new DebitAccount(
                String.format("%s_%d", Id, AccountIds += 1),
                AccountsTerm,
                PercentCalculator.CalculateDebitPercent(amountOfMoney),
                amountOfMoney,
                GetClientDoubtfulness(client),
                LimitIfDoubtful);
        RegisterAccountAndClient(account, client);
    }

    @Override
    public void CreateCreditAccount(Client client, float amountOfMoney) {
        var account = new CreditAccount(
                String.format("%s_%d", Id, AccountIds += 1),
                AccountsTerm,
                PercentCalculator.CalculateCreditCommission(amountOfMoney),
                amountOfMoney,
                GetClientDoubtfulness(client),
                LimitIfDoubtful);
        RegisterAccountAndClient(account, client);
    }

    @Override
    public void RegisterAccountAndClient(Account account, Client client) {
        Accounts.add(account);
        client.AddAccount(account);
        if (!CheckIfClientRegistered(client)) {
            RegisterClient(client);
        }
    }

    @Override
    public void RegisterClient(Client client) {
        Clients.add(client);
    }

    @Override
    public Boolean CheckIfClientRegistered(Client client) {
        return Clients.contains(client);
    }

    @Override
    public Client GetClientByAccount(Account account) {
        for (Client client : Clients) {
            if (client.Accounts.contains(account))
                return client;
        }
        return null;
    }

    @Override
    public Boolean GetClientDoubtfulness(Client client) {
        return client.GetAddress() == null || client.GetPassportNumber() == null;
    }

    @Override
    public Boolean CheckIfMonthPassed(Account account) {
        return account.GetTermAndDaysLeftDiff() % 30 == 0;
    }

    @Override
    public void AddDailyIncome() {
        for (Account account : Accounts) {
            account.AddDailyIncome();
            ReduceDaysLeft(account);
            if (account instanceof SavingsAccount savingsAccount && CheckIfMonthPassed(account)) {
                savingsAccount.AddMonthlyIncome();
            }
        }
    }

    @Override
    public void ReduceDaysLeft(Account account) {
        account.ReduceDaysLeft();
    }
}
