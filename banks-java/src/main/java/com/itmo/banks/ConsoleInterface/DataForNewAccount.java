package com.itmo.banks.ConsoleInterface;

public class DataForNewAccount {
    public String BankName;
    public String AccountType;
    public float AmountOfMoney;

    public DataForNewAccount(String bankName, String accountType, float amountOfMoney) {
        if (bankName == null)
            throw new IllegalArgumentException("Bank name cannot be null!");
        BankName = bankName;

        if (accountType == null)
            throw new IllegalArgumentException("Account type cannot be null!");
        AccountType = accountType;

        if (amountOfMoney <= 0)
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        AmountOfMoney = amountOfMoney;
    }
}
