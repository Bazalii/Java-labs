package com.itmo.banks.BanksStructure;

import com.itmo.banks.Tools.CannotWithdrawMoneyException;
import com.itmo.banks.Tools.NotEnoughMoneyToWithdrawException;

public abstract class Account {

    protected String Id;

    protected int Term;

    protected int DaysLeft;

    protected float AmountOfMoney;

    protected Boolean IsDoubtful;

    protected float LimitIfIsDoubtful;

    public abstract void addDailyIncome();

    public abstract void withdrawMoney(float amountOfMoney)
            throws NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException;

    public abstract void reduceDaysLeft();

    public void addMoney(float amountOfMoney) {
        AmountOfMoney += amountOfMoney;
    }

    public String getId() {
        return Id;
    }

    public String getBankId() {
        return Id.substring(0, Id.indexOf("_"));
    }

    public int getTerm() {
        return Term;
    }

    public int getDaysLeft() {
        return DaysLeft;
    }

    public int getTermAndDaysLeftDiff() {
        return Term - DaysLeft;
    }

    public float getAmountOfMoney() {
        return AmountOfMoney;
    }

    public void setDoubtfulness(Boolean doubtfulness) {
        IsDoubtful = doubtfulness;
    }

    public Boolean getDoubtfulness() {
        return IsDoubtful;
    }

    public float getLimitIfIsDoubtful() {
        return LimitIfIsDoubtful;
    }
}
