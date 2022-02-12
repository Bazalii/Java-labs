package com.itmo.banks.ConsoleInterface;

import com.itmo.banks.BanksStructure.IPercentCalculator;
import com.itmo.banks.BanksStructure.Implementations.*;
import com.itmo.banks.BanksStructure.Transaction;
import com.itmo.banks.Tools.*;

import java.util.ArrayList;

public class Application {
    private final ConsoleInterface _console = new ConsoleInterface();

    private final CentralBank _centralBank = new CentralBank();

    private final IPercentCalculator _newPercentCalculator = new CommonPercentCalculator(
            new ArrayList<>() {{
                add(new DepositSumWithPercent(20000, 2.5F));
                add(new DepositSumWithPercent(30000, 4F));
                add(new DepositSumWithPercent(70000, 5F));
            }},
            2F,
            30F);

    private final ArrayList<String> _accountTypes = new ArrayList<>() {{
        add("Deposit");
        add("Debit");
        add("Credit");
    }};

    private Client _currentClient = new Client();

    public void process() throws ClientWithoutNecessaryField, NotFoundException, NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException, DoubtfulAccountException {
        setUp();
        _console.start();
        while (true) {
            String command = _console.getCommand();
            switch (command) {
                case "commands" -> _console.getAvailableCommands();
                case "getAccounts" -> _console.getAccountsStatus(_currentClient.Accounts);
                case "getTransactions" -> _console.getTransactions(_centralBank.getClientTransactions(_currentClient));
                case "registerClient" -> registerClient(_console.registerClient());
                case "registerAccount" -> registerAccount(_console.registerAccount(_centralBank.getAllBankNames(), _accountTypes));
                case "closeAccount" -> closeAccount(_console.closeAccount(_currentClient.getAccountIds()));
                case "withdraw" -> withdrawMoney(_console.withdrawMoney(_currentClient.getAccountIds()));
                case "replenish" -> replenishMoney(_console.replenishMoney(_currentClient.getAccountIds()));
                case "transfer" -> transferMoney(_console.transferMoney(_currentClient.getAccountIds()));
                case "cancel" -> {
                    ArrayList<Transaction> transactions = _centralBank.getClientTransactions(_currentClient);
                    cancelTransaction(transactions.get(transactions.size() - 1).getId());
                }
                case "subscribe" -> subscribe(_console.subscribe(_centralBank.getAllBankNames()));
                case "unsubscribe" -> unsubscribe(_console.unsubscribe(_centralBank.getAllBankNames()));
                case "changePercents" -> changePercents(_console.changePercents(_centralBank.getAllBankNames()));
                case "scroll" -> scrollDays(_console.scrollDays());
                case "quit" -> {
                    _console.onEnd();
                    return;
                }
            }
        }
    }

    private void setUp() {
        IPercentCalculator firstPercentCalculator = new CommonPercentCalculator(
                new ArrayList<>() {{
                    add(new DepositSumWithPercent(50000, 3F));
                    add(new DepositSumWithPercent(100000, 4F));
                    add(new DepositSumWithPercent(150000, 5F));
                }},
                2.0F,
                20.0F);
        IPercentCalculator secondPercentCalculator = new CommonPercentCalculator(
                new ArrayList<>() {{
                    add(new DepositSumWithPercent(30000, 3F));
                    add(new DepositSumWithPercent(50000, 4.5F));
                    add(new DepositSumWithPercent(100000, 5.5F));
                }},
                2.5F,
                25.5F);
        Bank firstBank = new Bank("Sber", firstPercentCalculator, 180, 10000);
        Bank secondBank = new Bank("Spb", secondPercentCalculator, 180, 10000);
        _centralBank.registerBank(firstBank);
        _centralBank.registerBank(secondBank);
        Client client = new Client("Alexey", "Ivanov", "Nauki prospekt, 30, 110", "1234567");
        secondBank.registerClient(client);
        secondBank.createCreditAccount(client, 10000);
        secondBank.subscribe(client);
    }

    private void registerClient(Client client) {
        _currentClient = client;
    }

    private void registerAccount(DataForNewAccount dataForNewAccount) {
        Bank bank = _centralBank.getBankByName(dataForNewAccount.getBankName());
        switch (dataForNewAccount.getAccountType()) {
            case "Deposit" -> bank.createDepositAccount(_currentClient, dataForNewAccount.getAmountOfMoney());
            case "Debit" -> bank.createDebitAccount(_currentClient, dataForNewAccount.getAmountOfMoney());
            case "Credit" -> bank.createCreditAccount(_currentClient, dataForNewAccount.getAmountOfMoney());
        }
    }

    private void closeAccount(String accountId) throws NotFoundException {
        BankWithAccount bankWithAccount = _centralBank.getBankAndAccountByAccountId(accountId);
        bankWithAccount.getFoundBank().closeAccount(bankWithAccount.getFoundAccount());
    }

    private void withdrawMoney(DataForOneWayTransaction dataForOneWayTransaction) throws NotFoundException, NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException, DoubtfulAccountException {
        BankWithAccount bankWithAccount = _centralBank.getBankAndAccountByAccountId(dataForOneWayTransaction.getAccountId());
        _centralBank.withdrawMoney(bankWithAccount.getFoundAccount(), dataForOneWayTransaction.getAmountOfMoney());
    }

    private void replenishMoney(DataForOneWayTransaction dataForOneWayTransaction) throws NotFoundException {
        BankWithAccount bankWithAccount = _centralBank.getBankAndAccountByAccountId(dataForOneWayTransaction.getAccountId());
        _centralBank.addMoney(bankWithAccount.getFoundAccount(), dataForOneWayTransaction.getAmountOfMoney());
    }

    private void transferMoney(DataForTwoWaysTransactions dataForTwoWaysTransactions) throws NotFoundException {
        BankWithAccount bankWithFirstAccount = _centralBank.getBankAndAccountByAccountId(dataForTwoWaysTransactions.getFirstAccountId());
        BankWithAccount bankWithSecondAccount = _centralBank.getBankAndAccountByAccountId(dataForTwoWaysTransactions.getSecondAccountId());
        try {
            _centralBank.transferMoney(
                    bankWithFirstAccount.getFoundAccount(),
                    bankWithSecondAccount.getFoundAccount(),
                    dataForTwoWaysTransactions.getAmountOfMoney());
        } catch (TheSameAccountsException | NotEnoughMoneyToWithdrawException | DoubtfulAccountException | CannotWithdrawMoneyException exception) {
            _console.reflectException(exception.getMessage());
        }
    }

    private void cancelTransaction(int id) throws NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException {
        _centralBank.cancelTransaction(_centralBank.getTransaction(id));
    }

    private void subscribe(String bankName) {
        _centralBank.getBankByName(bankName).subscribe(_currentClient);
    }

    private void unsubscribe(String bankName) {
        _currentClient.unsubscribe(bankName);
    }

    private void changePercents(String bankName) {
        Bank bank = _centralBank.getBankByName(bankName);
        _console.showMessages(bank.setPercentCalculator(_newPercentCalculator));
    }

    private void scrollDays(int days) {
        _centralBank.scrollDays(days);
    }
}
