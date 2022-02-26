package com.itmo.banks.banksStructure;

public abstract class SavingsAccount extends Account {
    private float monthlyIncome;

    private float percent;

    private float dailyIncome;

    protected SavingsAccount(String id, int term, float percent, float amountOfMoney, Boolean doubtfulness, float limitIfIsDoubtful) {
        if (id == null)
            throw new IllegalArgumentException("Id cannot be null!");
        this.setId(id);

        if (term <= 0)
            throw new IllegalArgumentException("Term of account should be a positive integer!");
        this.setTerm(term);

        setDaysLeft(term);
        if (percent <= 0)
            throw new IllegalArgumentException("Percent should be a positive float!");
        this.setPercent(percent);

        if (amountOfMoney <= 0)
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        this.setAmountOfMoney(amountOfMoney);

        setDoubtful(doubtfulness);

        if (limitIfIsDoubtful <= 0)
            throw new IllegalArgumentException("Limit for account should be a positive float!");
        this.setLimitIfIsDoubtful(limitIfIsDoubtful);

        setDailyIncome(this.getPercent() * this.getAmountOfMoney() / 36500);
    }

    @Override
    public void addDailyIncome() {
        setMonthlyIncome(getMonthlyIncome() + getDailyIncome());
    }

    public void addMonthlyIncome() {
        setAmountOfMoney(getAmountOfMoney() + getMonthlyIncome());
    }

    public float getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(float monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public float getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(float dailyIncome) {
        this.dailyIncome = dailyIncome;
    }
}
