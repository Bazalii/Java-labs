package com.itmo.banks.BanksStructure;

import com.itmo.banks.Tools.CannotWithdrawMoneyException;
import com.itmo.banks.Tools.NotEnoughMoneyToWithdrawException;

public abstract class Account {

    protected String id;

    protected int term;

    protected int daysLeft;

    protected float amountOfMoney;

    protected Boolean isDoubtful;

    protected float limitIfIsDoubtful;

    public abstract void addDailyIncome();

    public abstract void withdrawMoney(float amountOfMoney)
            throws NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException;

    public abstract void reduceDaysLeft();

    public void addMoney(float amountOfMoney) {
        this.amountOfMoney += amountOfMoney;
    }

    public String getId() {
        return id;
    }

    public String getBankId() {
        return id.substring(0, id.indexOf("_"));
    }

    public int getTerm() {
        return term;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public int getTermAndDaysLeftDiff() {
        return term - daysLeft;
    }

    public float getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setDoubtfulness(Boolean doubtfulness) {
        isDoubtful = doubtfulness;
    }

    public Boolean getDoubtfulness() {
        return isDoubtful;
    }

    public float getLimitIfIsDoubtful() {
        return limitIfIsDoubtful;
    }
}
