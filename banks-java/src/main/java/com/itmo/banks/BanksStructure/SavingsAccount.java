package com.itmo.banks.BanksStructure;

public abstract class SavingsAccount extends Account {
    protected float monthlyIncome;

    protected float percent;

    protected float dailyIncome;

    protected SavingsAccount(String id, int term, float percent, float amountOfMoney, Boolean doubtfulness, float limitIfIsDoubtful) {
        if (id == null)
            throw new IllegalArgumentException("Id cannot be null!");
        this.id = id;

        if (term <= 0)
            throw new IllegalArgumentException("Term of account should be a positive integer!");
        this.term = term;

        daysLeft = term;
        if (percent <= 0)
            throw new IllegalArgumentException("Percent should be a positive float!");
        this.percent = percent;

        if (amountOfMoney <= 0)
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        this.amountOfMoney = amountOfMoney;

        isDoubtful = doubtfulness;

        if (limitIfIsDoubtful <= 0)
            throw new IllegalArgumentException("Limit for account should be a positive float!");
        this.limitIfIsDoubtful = limitIfIsDoubtful;

        dailyIncome = this.percent * this.amountOfMoney / 36500;
    }

    @Override
    public void addDailyIncome() {
        monthlyIncome += dailyIncome;
    }

    public void addMonthlyIncome() {
        amountOfMoney += monthlyIncome;
    }
}
