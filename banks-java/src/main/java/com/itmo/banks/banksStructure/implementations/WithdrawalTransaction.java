package com.itmo.banks.banksStructure.implementations;

import com.itmo.banks.banksStructure.Account;
import com.itmo.banks.banksStructure.Transaction;

public class WithdrawalTransaction extends Transaction {
    private Account _accountToWithdraw;

    public WithdrawalTransaction(int id, Account accountToWithdraw, float amountOfMoney) {
        if (id <= 0)
            throw new IllegalArgumentException("Id should be a positive integer!");
        this.id = id;

        if (accountToWithdraw == null)
            throw new IllegalArgumentException("Account to withdraw cannot be null!");
        setAccountToWithdraw(accountToWithdraw);

        if (amountOfMoney <= 0)
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        this.amountOfMoney = amountOfMoney;
    }

    public Account getAccountToWithdraw() {
        return _accountToWithdraw;
    }

    public void setAccountToWithdraw(Account accountToWithdraw) {
        _accountToWithdraw = accountToWithdraw;
    }
}
