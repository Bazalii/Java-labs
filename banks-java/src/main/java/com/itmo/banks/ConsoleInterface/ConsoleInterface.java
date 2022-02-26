package com.itmo.banks.ConsoleInterface;

import com.itmo.banks.BanksStructure.Account;
import com.itmo.banks.BanksStructure.Implementations.Client;
import com.itmo.banks.BanksStructure.Implementations.ReplenishmentTransaction;
import com.itmo.banks.BanksStructure.Implementations.TransferTransaction;
import com.itmo.banks.BanksStructure.Implementations.WithdrawalTransaction;
import com.itmo.banks.BanksStructure.Transaction;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleInterface {
    private final InputTransformer _inputTransformer = new InputTransformer();

    private final String _leftAlignCommonFormat = "| %-15s | %-70s |%n";

    public void getAvailableCommands() {
        System.out.format("+-----------------+------------------------------------------------------------------------+%n");
        System.out.format(_leftAlignCommonFormat, "Command", "Description");
        System.out.format("+-----------------+------------------------------------------------------------------------+%n");
        System.out.format(_leftAlignCommonFormat, "commands", "Shows the list of all available commands");
        System.out.format(_leftAlignCommonFormat, "getAccounts", "Shows the list of your accounts");
        System.out.format(_leftAlignCommonFormat, "getTransactions", "Shows the list of your transactions");
        System.out.format(_leftAlignCommonFormat, "registerClient", "Gives you an opportunity to register if you are not registered yet");
        System.out.format(_leftAlignCommonFormat, "registerAccount", "Adds new account");
        System.out.format(_leftAlignCommonFormat, "closeAccount", "Closes one of your accounts");
        System.out.format(_leftAlignCommonFormat, "withdraw", "Withdraws money from your account");
        System.out.format(_leftAlignCommonFormat, "replenish", "Replenishes money to your account");
        System.out.format(_leftAlignCommonFormat, "transfer", "Transfers money from your account to the other account");
        System.out.format(_leftAlignCommonFormat, "cancel", "Cancels transaction");
        System.out.format(_leftAlignCommonFormat, "subscribe", "Subscribes you to the bank");
        System.out.format(_leftAlignCommonFormat, "unsubscribe", "Unsubscribes you from the bank");
        System.out.format(_leftAlignCommonFormat, "changePercents", "Changes percents in one bank and notifies subscribers");
        System.out.format(_leftAlignCommonFormat, "scroll", "Shows what will happen with your accounts in number of days");
        System.out.format(_leftAlignCommonFormat, "quit", "Closes this application");
        System.out.format("+-----------------+------------------------------------------------------------------------+%n");
    }

    public void getAccountsStatus(List<Account> accounts) {
        if (accounts.size() == 0) return;
        System.out.format("+-----------------+------------------------------------------------------------------------+%n");
        System.out.format(_leftAlignCommonFormat, "Account Id", "Amount of money");
        System.out.format("+-----------------+------------------------------------------------------------------------+%n");
        for (Account account : accounts)
            System.out.format(_leftAlignCommonFormat, account.getId(), String.format("%f", account.getAmountOfMoney()));
    }

    public void getTransactions(List<Transaction> transactions) {
        if (transactions.size() == 0) return;
        System.out.format("+-----------------+--------------------------------+--------------------------------+--------------------------------+%n");
        String _leftAlignTransactionFormat = "| %-15s | %-30s | %-30s | %-30s |%n";
        System.out.format(_leftAlignTransactionFormat, "Transaction Id", "Withdrawal account", "Replenishment account", "Amount of money");
        System.out.format("+-----------------+--------------------------------+--------------------------------+--------------------------------+%n");
        for (Transaction transaction : transactions) {
            switch (transaction) {
                case WithdrawalTransaction withdrawalTransaction -> System.out.format(_leftAlignTransactionFormat,
                        withdrawalTransaction.getId(),
                        withdrawalTransaction.getAccountToWithdraw().getId(),
                        "-",
                        String.format("%f", withdrawalTransaction.getAmountOfMoney()));
                case ReplenishmentTransaction replenishmentTransaction -> System.out.format(_leftAlignTransactionFormat,
                        replenishmentTransaction.getId(),
                        "-",
                        replenishmentTransaction.getAccountToReplenish().getId(),
                        String.format("%f", replenishmentTransaction.getAmountOfMoney()));
                case TransferTransaction transferTransaction -> System.out.format(_leftAlignTransactionFormat,
                        transferTransaction.getId(),
                        transferTransaction.getAccountToWithdraw().getId(),
                        transferTransaction.getAccountToReplenish().getId(),
                        String.format("%f", transferTransaction.getAmountOfMoney()));
                default -> throw new IllegalStateException("Unexpected value: " + transaction);
            }
        }
    }

    public Client registerClient() {
        Scanner in = new Scanner(System.in);
        System.out.println("What's your name?");
        String clientName = in.nextLine();
        System.out.println("What's your surname?");
        String clientSurname = in.nextLine();
        System.out.println("Do you want to add address and passport?");
        String answer = in.nextLine();
        String clientAddress = null;
        String clientPassportNumber = null;
        if (Objects.equals(answer, "yes")) {
            System.out.println("What is your address?");
            clientAddress = in.nextLine();
            System.out.println("What is your passport number?");
        }

        return _inputTransformer.CreateClient(clientName, clientSurname, clientAddress, clientPassportNumber);
    }

    public DataForNewAccount registerAccount(List<String> banks, List<String> accountTypes) {
        System.out.println("In which bank do you want to open new account?");
        String bankName = getWantedBank(banks);
        String accountType = getWantedAccount(accountTypes);
        System.out.println("Please enter amount of money:");
        Scanner in = new Scanner(System.in);
        float amountOfMoney = Float.parseFloat(in.nextLine());
        return new DataForNewAccount(bankName, accountType, amountOfMoney);
    }

    public String closeAccount(List<String> personalAccounts) {
        System.out.println("Type the id of account which you want to close:");
        for (String account : personalAccounts)
            System.out.println(account);
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public DataForOneWayTransaction withdrawMoney(List<String> personalAccounts) {
        Scanner in = new Scanner(System.in);
        System.out.println("From which account do you want to withdraw money?");
        for (String account : personalAccounts)
            System.out.println(account);
        String accountId = in.nextLine();
        System.out.println("What amount of money do you want to withdraw?");
        float amountOfMoney = Float.parseFloat(in.nextLine());
        return new DataForOneWayTransaction(accountId, amountOfMoney);
    }

    public DataForOneWayTransaction replenishMoney(List<String> personalAccounts) {
        Scanner in = new Scanner(System.in);
        System.out.println("From which account do you want to replenish money?");
        for (String account : personalAccounts)
            System.out.println(account);
        String accountId = in.nextLine();
        System.out.println("What amount of money do you want to replenish?");
        float amountOfMoney = Float.parseFloat(in.nextLine());
        return new DataForOneWayTransaction(accountId, amountOfMoney);
    }

    public DataForTwoWaysTransactions transferMoney(List<String> personalAccounts) {
        Scanner in = new Scanner(System.in);
        System.out.println("From which account do you want to transfer money?");
        for (String account : personalAccounts)
            System.out.println(account);
        String accountToWithdrawId = in.nextLine();
        System.out.println("Type target account Id:");
        String accountToReplenishId = in.nextLine();
        System.out.println("What amount of money do you want to transfer?");
        float amountOfMoney = Float.parseFloat(in.nextLine());
        return new DataForTwoWaysTransactions(accountToWithdrawId, accountToReplenishId, amountOfMoney);
    }

    public int cancelTransaction(List<Integer> personalTransactions) {
        System.out.println("Which transaction do you want to cancel?");
        for (Integer transaction : personalTransactions)
            System.out.println(transaction);
        Scanner in = new Scanner(System.in);
        return Integer.parseInt(in.nextLine());
    }

    public String subscribe(List<String> banks) {
        return getWantedBank(banks);
    }

    public String unsubscribe(List<String> banks) {
        return getWantedBank(banks);
    }

    public String changePercents(List<String> banks) {
        return getWantedBank(banks);
    }

    public int scrollDays() {
        System.out.println("How many days do you want to scroll?");
        Scanner in = new Scanner(System.in);
        return Integer.parseInt(in.nextLine());
    }

    public void start() {
        System.out.println("Welcome to the Ivan's Bank application!");
        System.out.println("This application emulates process of client working with business logic.");
        System.out.println("You can control all the process with the beginning as a CEO of central bank.");
        System.out.println("This app works with only one registered client(you).");
        System.out.println("So, first of all you have to register as a client.");
        System.out.println("Every time that you use \"registerClient\" you will work with new client.");
        System.out.println("All the commands in this app except changePercents work with this client and their accounts.");
        System.out.println("There are two registered banks: Sber and Spb.");
        System.out.println("There is also one more registered client Alexey Ivanov.");
        System.out.println("He has a credit account with Id 2_1 in Spb bank.");
        System.out.println("You can use all that things to test functionality.");
        System.out.println("Type \"commands\" to get full list of available commands.");
    }

    public void onEnd() {
        System.out.println("See you later!");
    }

    public void showMessages(List<String> messages) {
        for (String message : messages)
            System.out.println(message);
    }

    public String getCommand() {
        System.out.println("What do you want me to do for you?");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public String getWantedBank(List<String> banks) {
        System.out.println("Type bank name:");
        for (String bank : banks)
            System.out.println(bank);

        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public String getWantedAccount(List<String> accountTypes) {
        System.out.println("Which account do you want to open?");
        for (String account : accountTypes)
            System.out.println(account);

        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void reflectException(String message) {
        System.out.println(message);
        System.out.println("\n");
    }
}
