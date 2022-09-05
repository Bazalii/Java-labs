package com.itmo.banks.banksStructure.implementations;

import com.itmo.banks.banksStructure.*;

import java.util.List;

public class Bank extends BankPrototype {
    private final IHandler _handler;

    public Bank(String name, IPercentCalculator percentCalculator, int accountsTerm, float limitIfDoubtful) {
        if (name == null)
            throw new IllegalArgumentException("Name cannot be null!");
        this.setName(name);

        _handler = new PercentChangesHandler(this.getName());

        if (percentCalculator == null)
            throw new IllegalArgumentException("Percent calculator cannot be null!");
        super.setPercentCalculator(percentCalculator);

        if (accountsTerm <= 0)
            throw new IllegalArgumentException("Term of account should be a positive integer!");
        this.setAccountsTerm(accountsTerm);

        if (limitIfDoubtful <= 0)
            throw new IllegalArgumentException("Limit for account should be a positive float!");
        this.setLimitIfDoubtful(limitIfDoubtful);
    }

    public void subscribe(IObserver observer) {
        observer.subscribe(_handler);
    }

    public List<String> changePercentCalculator(IPercentCalculator percentCalculator) {
        super.setPercentCalculator(percentCalculator);

        return _handler.sendNotifications();
    }

    @Override
    public void addAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public void removeAccount(Account account) {
        getClientByAccount(account).removeAccount(account);

        accounts.remove(account);
    }

    @Override
    public void closeAccount(Account account) {
        removeAccount(account);
    }

    @Override
    public void createDepositAccount(Client client, float amountOfMoney) {
        var account = new DepositAccount(
                String.format("%d_%d", getId(), setAccountIds(getAccountIds() + 1)),
                getAccountsTerm(),
                getPercentCalculator().calculateDepositPercent(amountOfMoney),
                amountOfMoney,
                getClientDoubtfulness(client),
                getLimitIfDoubtful());

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
                String.format("%s_%d", getId(), setAccountIds(getAccountIds() + 1)),
                getAccountsTerm(),
                getPercentCalculator().calculateDebitPercent(amountOfMoney),
                amountOfMoney,
                getClientDoubtfulness(client),
                getLimitIfDoubtful());

        registerAccountAndClient(account, client);
    }

    @Override
    public void createCreditAccount(Client client, float amountOfMoney) {
        var account = new CreditAccount(
                String.format("%s_%d", getId(), setAccountIds(getAccountIds() + 1)),
                getAccountsTerm(),
                getPercentCalculator().calculateCreditCommission(amountOfMoney),
                amountOfMoney,
                getClientDoubtfulness(client),
                getLimitIfDoubtful());

        registerAccountAndClient(account, client);
    }

    @Override
    public void registerAccountAndClient(Account account, Client client) {
        accounts.add(account);
        client.addAccount(account);

        if (!checkIfClientRegistered(client)) {
            registerClient(client);
        }
    }

    @Override
    public void registerClient(Client client) {
        clients.add(client);
    }

    @Override
    public Boolean checkIfClientRegistered(Client client) {
        return clients.contains(client);
    }

    @Override
    public Client getClientByAccount(Account account) {
        for (Client client : clients) {
            if (client.accounts.contains(account))
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
        for (Account account : accounts) {
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
