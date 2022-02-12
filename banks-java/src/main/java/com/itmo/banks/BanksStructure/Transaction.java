package com.itmo.banks.BanksStructure;

public abstract class Transaction {
    protected int Id;

    protected float AmountOfMoney;

    public int getId() {
        return Id;
    }

    public float getAmountOfMoney() {
        return AmountOfMoney;
    }
}
