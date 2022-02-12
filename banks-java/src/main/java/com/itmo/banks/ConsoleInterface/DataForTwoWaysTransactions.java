package com.itmo.banks.ConsoleInterface;

public class DataForTwoWaysTransactions {
    public String FirstAccountId;
    public String SecondAccountId;
    public float AmountOfMoney;

    public DataForTwoWaysTransactions(String firstAccountId, String secondAccountId, float amountOfMoney) {
        if (firstAccountId == null)
            throw new IllegalArgumentException("Account id cannot be null!");
        FirstAccountId = firstAccountId;

        if (secondAccountId == null)
            throw new IllegalArgumentException("Account id cannot be null!");
        SecondAccountId = secondAccountId;

        if (amountOfMoney <= 0)
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        AmountOfMoney = amountOfMoney;
    }
}
