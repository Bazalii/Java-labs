package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.Account;

public class BankWithAccount {
    public Bank FoundBank;
    public Account FoundAccount;

    public BankWithAccount(Bank bank, Account account) {
        if (bank == null)
            throw new IllegalArgumentException("Bank cannot be null!");
        FoundBank = bank;
        if (account == null)
            throw new IllegalArgumentException("Account cannot be null!");
        FoundAccount = account;
    }
}