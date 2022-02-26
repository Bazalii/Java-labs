package com.itmo.banks.consoleInterface;

public class DataForNewAccount {
    private String _bankName;

    private String _accountType;

    private float _amountOfMoney;

    public DataForNewAccount(String bankName, String accountType, float amountOfMoney) {
        if (bankName == null)
            throw new IllegalArgumentException("Bank name cannot be null!");
        setBankName(bankName);

        if (accountType == null)
            throw new IllegalArgumentException("Account type cannot be null!");
        setAccountType(accountType);

        if (amountOfMoney <= 0)
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        setAmountOfMoney(amountOfMoney);
    }

    public String getBankName() {
        return _bankName;
    }

    public void setBankName(String bankName) {
        _bankName = bankName;
    }

    public String getAccountType() {
        return _accountType;
    }

    public void setAccountType(String accountType) {
        _accountType = accountType;
    }

    public float getAmountOfMoney() {
        return _amountOfMoney;
    }

    public void setAmountOfMoney(float amountOfMoney) {
        _amountOfMoney = amountOfMoney;
    }
}
