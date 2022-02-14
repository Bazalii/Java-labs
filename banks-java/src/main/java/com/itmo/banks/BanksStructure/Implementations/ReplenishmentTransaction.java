package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.Account;
import com.itmo.banks.BanksStructure.Transaction;

public class ReplenishmentTransaction extends Transaction {
    private Account _accountToReplenish;

    public ReplenishmentTransaction(int id, Account accountToReplenish, float amountOfMoney) {
        if (id <= 0)
            throw new IllegalArgumentException("Id should be a positive integer!");
        this.id = id;

        if (accountToReplenish == null)
            throw new IllegalArgumentException("Account to replenish cannot be null!");
        setAccountToReplenish(accountToReplenish);

        if (amountOfMoney <= 0)
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        this.amountOfMoney = amountOfMoney;
    }

    public Account getAccountToReplenish() {
        return _accountToReplenish;
    }

    public void setAccountToReplenish(Account accountToReplenish) {
        _accountToReplenish = accountToReplenish;
    }
}
