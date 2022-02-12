package com.itmo.banks.ConsoleInterface;

public class DataForTwoWaysTransactions {
    private String _firstAccountId;

    private String _secondAccountId;

    private float _amountOfMoney;

    public DataForTwoWaysTransactions(String firstAccountId, String secondAccountId, float amountOfMoney) {
        if (firstAccountId == null)
            throw new IllegalArgumentException("Account id cannot be null!");
        setFirstAccountId(firstAccountId);

        if (secondAccountId == null)
            throw new IllegalArgumentException("Account id cannot be null!");
        setSecondAccountId(secondAccountId);

        if (amountOfMoney <= 0)
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        setAmountOfMoney(amountOfMoney);
    }

    public String getFirstAccountId() {
        return _firstAccountId;
    }

    public void setFirstAccountId(String firstAccountId) {
        _firstAccountId = firstAccountId;
    }

    public String getSecondAccountId() {
        return _secondAccountId;
    }

    public void setSecondAccountId(String secondAccountId) {
        _secondAccountId = secondAccountId;
    }

    public float getAmountOfMoney() {
        return _amountOfMoney;
    }

    public void setAmountOfMoney(float amountOfMoney) {
        _amountOfMoney = amountOfMoney;
    }
}
