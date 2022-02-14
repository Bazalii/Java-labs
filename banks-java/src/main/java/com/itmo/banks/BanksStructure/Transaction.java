package com.itmo.banks.BanksStructure;

public abstract class Transaction {
    protected int id;

    protected float amountOfMoney;

    public int getId() {
        return id;
    }

    public float getAmountOfMoney() {
        return amountOfMoney;
    }
}
