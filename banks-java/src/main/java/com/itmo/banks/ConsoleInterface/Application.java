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

    private Client _currentClient = new Client();

    private final ArrayList<String> _accountTypes = new ArrayList<>() {{
        add("Deposit");
        add("Debit");
        add("Credit");
    }};

    public void Process() throws ClientWithoutNecessaryField, NotFoundException, NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException, DoubtfulAccountException {
        SetUp();
        _console.Start();
        while (true) {
            String command = _console.GetCommand();
            switch (command) {
                case "commands" -> _console.GetAvailableCommands();
                case "getAccounts" -> _console.GetAccountsStatus(_currentClient.Accounts);
                case "getTransactions" -> _console.GetTransactions(_centralBank.GetClientTransactions(_currentClient));
                case "registerClient" -> RegisterClient(_console.RegisterClient());
                case "registerAccount" -> RegisterAccount(_console.RegisterAccount(_centralBank.GetAllBankNames(), _accountTypes));
                case "closeAccount" -> CloseAccount(_console.CloseAccount(_currentClient.GetAccountIds()));
                case "withdraw" -> WithdrawMoney(_console.WithdrawMoney(_currentClient.GetAccountIds()));
                case "replenish" -> ReplenishMoney(_console.ReplenishMoney(_currentClient.GetAccountIds()));
                case "transfer" -> TransferMoney(_console.TransferMoney(_currentClient.GetAccountIds()));
                case "cancel" -> {
                    ArrayList<Transaction> transactions = _centralBank.GetClientTransactions(_currentClient);
                    CancelTransaction(transactions.get(transactions.size() - 1).GetId());
                }
                case "subscribe" -> Subscribe(_console.Subscribe(_centralBank.GetAllBankNames()));
                case "unsubscribe" -> Unsubscribe(_console.Unsubscribe(_centralBank.GetAllBankNames()));
                case "changePercents" -> ChangePercents(_console.ChangePercents(_centralBank.GetAllBankNames()));
                case "scroll" -> ScrollDays(_console.ScrollDays());
                case "quit" -> {
                    _console.OnEnd();
                    return;
                }
            }
        }
    }

    private void SetUp() {
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
        _centralBank.RegisterBank(firstBank);
        _centralBank.RegisterBank(secondBank);
        Client client = new Client("Alexey", "Ivanov", "Nauki prospekt, 30, 110", "1234567");
        secondBank.RegisterClient(client);
        secondBank.CreateCreditAccount(client, 10000);
        secondBank.Subscribe(client);
    }

    private void RegisterClient(Client client) {
        _currentClient = client;
    }

    private void RegisterAccount(DataForNewAccount dataForNewAccount) {
        Bank bank = _centralBank.GetBankByName(dataForNewAccount.BankName);
        switch (dataForNewAccount.AccountType) {
            case "Deposit" -> bank.CreateDepositAccount(_currentClient, dataForNewAccount.AmountOfMoney);
            case "Debit" -> bank.CreateDebitAccount(_currentClient, dataForNewAccount.AmountOfMoney);
            case "Credit" -> bank.CreateCreditAccount(_currentClient, dataForNewAccount.AmountOfMoney);
        }
    }

    private void CloseAccount(String accountId) throws NotFoundException {
        BankWithAccount bankWithAccount = _centralBank.GetBankAndAccountByAccountId(accountId);
        bankWithAccount.FoundBank.CloseAccount(bankWithAccount.FoundAccount);
    }

    private void WithdrawMoney(DataForOneWayTransaction dataForOneWayTransaction) throws NotFoundException, NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException, DoubtfulAccountException {
        BankWithAccount bankWithAccount = _centralBank.GetBankAndAccountByAccountId(dataForOneWayTransaction.AccountId);
        _centralBank.WithdrawMoney(bankWithAccount.FoundAccount, dataForOneWayTransaction.AmountOfMoney);
    }

    private void ReplenishMoney(DataForOneWayTransaction dataForOneWayTransaction) throws NotFoundException {
        BankWithAccount bankWithAccount = _centralBank.GetBankAndAccountByAccountId(dataForOneWayTransaction.AccountId);
        _centralBank.AddMoney(bankWithAccount.FoundAccount, dataForOneWayTransaction.AmountOfMoney);
    }

    private void TransferMoney(DataForTwoWaysTransactions dataForTwoWaysTransactions) throws NotFoundException {
        BankWithAccount bankWithFirstAccount = _centralBank.GetBankAndAccountByAccountId(dataForTwoWaysTransactions.FirstAccountId);
        BankWithAccount bankWithSecondAccount = _centralBank.GetBankAndAccountByAccountId(dataForTwoWaysTransactions.SecondAccountId);
        try {
            _centralBank.TransferMoney(
                    bankWithFirstAccount.FoundAccount,
                    bankWithSecondAccount.FoundAccount,
                    dataForTwoWaysTransactions.AmountOfMoney);
        } catch (TheSameAccountsException | NotEnoughMoneyToWithdrawException | DoubtfulAccountException | CannotWithdrawMoneyException exception) {
            _console.ReflectException(exception.getMessage());
        }
    }

    private void CancelTransaction(int id) throws NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException {
        _centralBank.CancelTransaction(_centralBank.GetTransaction(id));
    }

    private void Subscribe(String bankName) {
        _centralBank.GetBankByName(bankName).Subscribe(_currentClient);
    }

    private void Unsubscribe(String bankName) {
        _currentClient.Unsubscribe(bankName);
    }

    private void ChangePercents(String bankName) {
        Bank bank = _centralBank.GetBankByName(bankName);
        _console.ShowMessages(bank.SetPercentCalculator(_newPercentCalculator));
    }

    private void ScrollDays(int days) {
        _centralBank.ScrollDays(days);
    }
}
