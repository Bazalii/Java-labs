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

    public void RegisterBank(Bank bank) {
        bank.SetId(_bankIds += 1);
        _banks.add(bank);
    }

    public void AddDailyIncome() {
        for (Bank bank : _banks) {
            bank.AddDailyIncome();
        }
    }

    public void RegisterTransaction(Transaction transaction) {
        _transactions.add(transaction);
    }

    public void AddMoney(Account accountToReplenish, float amountOfMoney) {
        accountToReplenish.AddMoney(amountOfMoney);
        RegisterTransaction(new ReplenishmentTransaction(_transactionIds += 1, accountToReplenish, amountOfMoney));
    }

    public void WithdrawMoney(Account accountToWithdraw, float amountOfMoney) throws NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException, DoubtfulAccountException {
        if (accountToWithdraw.GetDoubtfulness() && amountOfMoney > accountToWithdraw.GetLimitIfIsDoubtful()) {
            throw new DoubtfulAccountException(
                    String.format("Your account with id: %s is doubtful." +
                                    " Please add passport number or address to your account or withdraw not more than %f",
                            accountToWithdraw.GetId(),
                            accountToWithdraw.GetLimitIfIsDoubtful()));
        }

        accountToWithdraw.WithdrawMoney(amountOfMoney);
        RegisterTransaction(new WithdrawalTransaction(_transactionIds += 1, accountToWithdraw, amountOfMoney));
    }

    public void TransferMoney(Account accountToWithdraw, Account accountToReplenish, float amountOfMoney) throws TheSameAccountsException, DoubtfulAccountException, NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException {
        if (Objects.equals(accountToWithdraw, accountToReplenish)) {
            throw new TheSameAccountsException("Account to withdraw and account to replenish should be different!");
        }

        if (accountToWithdraw.GetDoubtfulness() && amountOfMoney > accountToWithdraw.GetLimitIfIsDoubtful()) {
            throw new DoubtfulAccountException(
                    String.format("Your account with id: %s is doubtful." +
                                    " Please add passport number or address to your account or withdraw not more than %f",
                            accountToWithdraw.GetId(),
                            accountToWithdraw.GetLimitIfIsDoubtful()));
        }

        accountToWithdraw.WithdrawMoney(amountOfMoney);
        accountToReplenish.AddMoney(amountOfMoney);
        RegisterTransaction(new TransferTransaction(_transactionIds += 1, accountToWithdraw, accountToReplenish, amountOfMoney));
    }

    public void CancelTransaction(Transaction transaction) throws NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException {
        float amountOfMoney = transaction.GetAmountOfMoney();
        switch (transaction) {
            case WithdrawalTransaction withdrawalTransaction -> withdrawalTransaction.AccountToWithdraw.AddMoney(amountOfMoney);
            case ReplenishmentTransaction replenishmentTransaction -> replenishmentTransaction.AccountToReplenish.WithdrawMoney(amountOfMoney);
            case TransferTransaction transferTransaction -> {
                transferTransaction.AccountToWithdraw.AddMoney(amountOfMoney);
                transferTransaction.AccountToReplenish.WithdrawMoney(amountOfMoney);
            }
            default -> throw new IllegalStateException("Unexpected value: " + transaction);
        }

        _transactions.remove(transaction);
    }

    public Transaction GetTransaction(int id) {
        for (Transaction transaction : _transactions) {
            if (transaction.GetId() == id)
                return transaction;
        }

        return null;
    }

    public ArrayList<Transaction> GetClientTransactions(Client client) {
        ArrayList<Transaction> output = new ArrayList<>();
        for (Transaction transaction : _transactions) {
            for (Account account : client.Accounts) {
                switch (transaction) {
                    case WithdrawalTransaction withdrawalTransaction:
                        if (Objects.equals(account, withdrawalTransaction.AccountToWithdraw)) {
                            output.add(withdrawalTransaction);
                        }

                        break;
                    case ReplenishmentTransaction replenishmentTransaction:
                        if (Objects.equals(account, replenishmentTransaction.AccountToReplenish)) {
                            output.add(replenishmentTransaction);
                        }

                        break;
                    case TransferTransaction transferTransaction:
                        if (Objects.equals(account, transferTransaction.AccountToWithdraw) ||
                                Objects.equals(account, transferTransaction.AccountToReplenish)) {
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

    public Bank GetBankByName(String name) {
        for (Bank bank : _banks) {
            if (Objects.equals(bank.GetName(), name))
                return bank;
        }

        return null;
    }

    public Bank GetBankById(int id) {
        for (Bank bank : _banks) {
            if (Objects.equals(bank.GetId(), id))
                return bank;
        }

        return null;
    }

    public ArrayList<String> GetAllBankNames() {
        ArrayList<String> output = new ArrayList<>();
        for (Bank bank : _banks) {
            output.add(bank.GetName());
        }
        return output;
    }

    public BankWithAccount GetBankAndAccountByAccountId(String accountId) throws NotFoundException {
        for (Bank bank : _banks) {
            for (Account account : bank.Accounts) {
                if (Objects.equals(account.GetId(), accountId)) {
                    return new BankWithAccount(bank, account);
                }
            }
        }

        throw new NotFoundException(String.format("Account with Id: %s doesn't exist", accountId));
    }

    public void ScrollDays(int days) {
        for (int i = 0; i < days; i++) {
            AddDailyIncome();
        }
    }
}
