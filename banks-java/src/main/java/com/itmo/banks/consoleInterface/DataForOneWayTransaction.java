package com.itmo.banks.consoleInterface;

public class DataForOneWayTransaction {
    private String _accountId;

    private float _amountOfMoney;

    public DataForOneWayTransaction(String accountId, float amountOfMoney) {
        if (accountId == null)
            throw new IllegalArgumentException("Account id cannot be null!");
        setAccountId(accountId);

        if (amountOfMoney <= 0)
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        setAmountOfMoney(amountOfMoney);
    }

    public String getAccountId() {
        return _accountId;
    }

    public void setAccountId(String accountId) {
        _accountId = accountId;
    }

    public float getAmountOfMoney() {
        return _amountOfMoney;
    }

    public void setAmountOfMoney(float amountOfMoney) {
        _amountOfMoney = amountOfMoney;
    }
}
