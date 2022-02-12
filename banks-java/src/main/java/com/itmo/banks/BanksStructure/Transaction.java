package com.itmo.banks.BanksStructure;

public abstract class Transaction {
    protected int Id;

    protected float AmountOfMoney;

    public int GetId() {
        return Id;
    }

    public float GetAmountOfMoney() {
        return AmountOfMoney;
    }
}
