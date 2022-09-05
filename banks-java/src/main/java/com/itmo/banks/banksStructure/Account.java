package com.itmo.banks.banksStructure;

public abstract class Account {

    private String id;
    private int term;
    private int daysLeft;
    private float amountOfMoney;
    private Boolean isDoubtful;
    private float limitIfIsDoubtful;

    public abstract void addDailyIncome();

    public abstract void withdrawMoney(float amountOfMoney);

    public abstract void reduceDaysLeft();

    public void addMoney(float amountOfMoney) {
        this.setAmountOfMoney(this.getAmountOfMoney() + amountOfMoney);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankId() {
        return getId().substring(0, getId().indexOf("_"));
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public int getTermAndDaysLeftDiff() {
        return getTerm() - getDaysLeft();
    }

    public float getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(float amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Boolean getDoubtfulness() {
        return getDoubtful();
    }

    public void setDoubtfulness(Boolean doubtfulness) {
        setDoubtful(doubtfulness);
    }

    public float getLimitIfIsDoubtful() {
        return limitIfIsDoubtful;
    }

    public void setLimitIfIsDoubtful(float limitIfIsDoubtful) {
        this.limitIfIsDoubtful = limitIfIsDoubtful;
    }

    public Boolean getDoubtful() {
        return isDoubtful;
    }

    public void setDoubtful(Boolean doubtful) {
        isDoubtful = doubtful;
    }
}
