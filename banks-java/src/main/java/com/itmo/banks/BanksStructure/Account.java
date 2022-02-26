package com.itmo.banks.BanksStructure;

import com.itmo.banks.Tools.CannotWithdrawMoneyException;
import com.itmo.banks.Tools.NotEnoughMoneyToWithdrawException;

public abstract class Account {

    private String id;

    private int term;

    private int daysLeft;

    private float amountOfMoney;

    private Boolean isDoubtful;

    private float limitIfIsDoubtful;

    public abstract void addDailyIncome();

    public abstract void withdrawMoney(float amountOfMoney)
            throws NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException;

    public abstract void reduceDaysLeft();

    public void addMoney(float amountOfMoney) {
        this.setAmountOfMoney(this.getAmountOfMoney() + amountOfMoney);
    }

    public String getId() {
        return id;
    }

    public String getBankId() {
        return getId().substring(0, getId().indexOf("_"));
    }

    public int getTerm() {
        return term;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public int getTermAndDaysLeftDiff() {
        return getTerm() - getDaysLeft();
    }

    public float getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setDoubtfulness(Boolean doubtfulness) {
        setDoubtful(doubtfulness);
    }

    public Boolean getDoubtfulness() {
        return getDoubtful();
    }

    public float getLimitIfIsDoubtful() {
        return limitIfIsDoubtful;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public void setAmountOfMoney(float amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Boolean getDoubtful() {
        return isDoubtful;
    }

    public void setDoubtful(Boolean doubtful) {
        isDoubtful = doubtful;
    }

    public void setLimitIfIsDoubtful(float limitIfIsDoubtful) {
        this.limitIfIsDoubtful = limitIfIsDoubtful;
    }
}
