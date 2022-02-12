package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.Account;
import com.itmo.banks.BanksStructure.Transaction;

public class WithdrawalTransaction extends Transaction {
    public Account AccountToWithdraw;

    public WithdrawalTransaction(int id, Account accountToWithdraw, float amountOfMoney) {
        if (id <= 0) {
            throw new IllegalArgumentException("Id should be a positive integer!");
        }

        Id = id;
        if (accountToWithdraw == null)
            throw new IllegalArgumentException("Account to withdraw cannot be null!");
        AccountToWithdraw = accountToWithdraw;

        if (amountOfMoney <= 0) {
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        }

        AmountOfMoney = amountOfMoney;
    }
}
