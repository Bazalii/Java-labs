package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.Account;
import com.itmo.banks.BanksStructure.Transaction;

public class TransferTransaction extends Transaction {
    public Account AccountToWithdraw;
    public Account AccountToReplenish;

    public TransferTransaction(int id, Account accountToWithdraw, Account accountToReplenish, float amountOfMoney) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id should be a positive integer!");
        }

        Id = id;
        if (accountToWithdraw == null)
            throw new IllegalArgumentException("Account to withdraw cannot be null!");
        AccountToWithdraw = accountToWithdraw;

        if (accountToReplenish == null)
            throw new IllegalArgumentException("Account to replenish cannot be null!");
        AccountToReplenish = accountToReplenish;

        if (amountOfMoney <= 0) {
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        }

        AmountOfMoney = amountOfMoney;
    }
}
