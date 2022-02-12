package com.itmo.banks.ConsoleInterface;

public class DataForOneWayTransaction {
    public String AccountId;
    public float AmountOfMoney;

    public DataForOneWayTransaction(String accountId, float amountOfMoney) {
        if (accountId == null)
            throw new IllegalArgumentException("Account id cannot be null!");
        AccountId = accountId;

        if (amountOfMoney <= 0)
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        AmountOfMoney = amountOfMoney;
    }
}
