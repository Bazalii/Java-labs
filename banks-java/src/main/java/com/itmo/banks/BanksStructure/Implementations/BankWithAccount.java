package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.Account;

public class BankWithAccount {
    private Bank _foundBank;

    private Account _foundAccount;

    public BankWithAccount(Bank bank, Account account) {
        if (bank == null)
            throw new IllegalArgumentException("Bank cannot be null!");

        setFoundBank(bank);

        if (account == null)
            throw new IllegalArgumentException("Account cannot be null!");

        setFoundAccount(account);
    }

    public Bank getFoundBank() {
        return _foundBank;
    }

    public void setFoundBank(Bank foundBank) {
        _foundBank = foundBank;
    }

    public Account getFoundAccount() {
        return _foundAccount;
    }

    public void setFoundAccount(Account foundAccount) {
        _foundAccount = foundAccount;
    }
}