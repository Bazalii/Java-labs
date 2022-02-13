package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.Account;
import com.itmo.banks.BanksStructure.Transaction;
import com.itmo.banks.Tools.*;

import java.util.ArrayList;
import java.util.Objects;

public class CentralBank {
    private final ArrayList<Bank> _banks = new ArrayList<>();

    private final ArrayList<Transaction> _transactions = new ArrayList<>();

    private int _transactionIds;

    private int _bankIds;

    public void registerBank(Bank bank) {
        bank.setId(_bankIds += 1);
        _banks.add(bank);
    }

    public void addDailyIncome() {
        for (Bank bank : _banks) {
            bank.addDailyIncome();
        }
    }

    public void registerTransaction(Transaction transaction) {
        _transactions.add(transaction);
    }

    public void addMoney(Account accountToReplenish, float amountOfMoney) {
        accountToReplenish.addMoney(amountOfMoney);
        registerTransaction(new ReplenishmentTransaction(_transactionIds += 1, accountToReplenish, amountOfMoney));
    }

    public void withdrawMoney(Account accountToWithdraw, float amountOfMoney)
            throws NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException, DoubtfulAccountException {
        if (accountToWithdraw.getDoubtfulness() && amountOfMoney > accountToWithdraw.getLimitIfIsDoubtful()) {
            throw new DoubtfulAccountException(
                    String.format("Your account with id: %s is doubtful." +
                                    " Please add passport number or address to your account or withdraw not more than %f",
                            accountToWithdraw.getId(),
                            accountToWithdraw.getLimitIfIsDoubtful()));
        }

        accountToWithdraw.withdrawMoney(amountOfMoney);
        registerTransaction(new WithdrawalTransaction(_transactionIds += 1, accountToWithdraw, amountOfMoney));
    }

    public void transferMoney(Account accountToWithdraw, Account accountToReplenish, float amountOfMoney)
            throws TheSameAccountsException, DoubtfulAccountException, NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException {
        if (Objects.equals(accountToWithdraw, accountToReplenish)) {
            throw new TheSameAccountsException("Account to withdraw and account to replenish should be different!");
        }

        if (accountToWithdraw.getDoubtfulness() && amountOfMoney > accountToWithdraw.getLimitIfIsDoubtful()) {
            throw new DoubtfulAccountException(
                    String.format("Your account with id: %s is doubtful." +
                                    " Please add passport number or address to your account or withdraw not more than %f",
                            accountToWithdraw.getId(),
                            accountToWithdraw.getLimitIfIsDoubtful()));
        }

        accountToWithdraw.withdrawMoney(amountOfMoney);
        accountToReplenish.addMoney(amountOfMoney);
        registerTransaction(new TransferTransaction(_transactionIds += 1, accountToWithdraw, accountToReplenish, amountOfMoney));
    }

    public void cancelTransaction(Transaction transaction)
            throws NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException {
        float amountOfMoney = transaction.getAmountOfMoney();
        switch (transaction) {
            case WithdrawalTransaction withdrawalTransaction -> withdrawalTransaction.getAccountToWithdraw().addMoney(amountOfMoney);
            case ReplenishmentTransaction replenishmentTransaction -> replenishmentTransaction.getAccountToReplenish().withdrawMoney(amountOfMoney);
            case TransferTransaction transferTransaction -> {
                transferTransaction.getAccountToWithdraw().addMoney(amountOfMoney);
                transferTransaction.getAccountToReplenish().withdrawMoney(amountOfMoney);
            }
            default -> throw new IllegalStateException("Unexpected value: " + transaction);
        }

        _transactions.remove(transaction);
    }

    public Transaction getTransaction(int id) {
        for (Transaction transaction : _transactions) {
            if (transaction.getId() == id)
                return transaction;
        }

        return null;
    }

    public ArrayList<Transaction> getClientTransactions(Client client) {
        ArrayList<Transaction> output = new ArrayList<>();
        for (Transaction transaction : _transactions) {
            for (Account account : client.Accounts) {
                switch (transaction) {
                    case WithdrawalTransaction withdrawalTransaction:
                        if (Objects.equals(account, withdrawalTransaction.getAccountToWithdraw())) {
                            output.add(withdrawalTransaction);
                        }

                        break;
                    case ReplenishmentTransaction replenishmentTransaction:
                        if (Objects.equals(account, replenishmentTransaction.getAccountToReplenish())) {
                            output.add(replenishmentTransaction);
                        }

                        break;
                    case TransferTransaction transferTransaction:
                        if (Objects.equals(account, transferTransaction.getAccountToWithdraw()) ||
                                Objects.equals(account, transferTransaction.getAccountToReplenish())) {
                            output.add(transferTransaction);
                        }

                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + transaction);
                }
            }
        }

        return output;
    }

    public Bank getBankByName(String name) {
        for (Bank bank : _banks) {
            if (Objects.equals(bank.getName(), name))
                return bank;
        }

        return null;
    }

    public Bank getBankById(int id) {
        for (Bank bank : _banks) {
            if (Objects.equals(bank.getId(), id))
                return bank;
        }

        return null;
    }

    public ArrayList<String> getAllBankNames() {
        ArrayList<String> output = new ArrayList<>();
        for (Bank bank : _banks) {
            output.add(bank.getName());
        }
        return output;
    }

    public BankWithAccount getBankAndAccountByAccountId(String accountId) throws NotFoundException {
        for (Bank bank : _banks) {
            for (Account account : bank.Accounts) {
                if (Objects.equals(account.getId(), accountId)) {
                    return new BankWithAccount(bank, account);
                }
            }
        }

        throw new NotFoundException(String.format("Account with Id: %s doesn't exist", accountId));
    }

    public void scrollDays(int days) {
        for (int i = 0; i < days; i++) {
            addDailyIncome();
        }
    }
}
