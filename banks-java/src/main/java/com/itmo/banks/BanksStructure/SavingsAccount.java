package com.itmo.banks.BanksStructure;

public abstract class SavingsAccount extends Account {
    protected float MonthlyIncome;
    protected float Percent;
    protected float DailyIncome;

    protected SavingsAccount(String id, int term, float percent, float amountOfMoney, Boolean doubtfulness, float limitIfIsDoubtful) {
        if (id == null)
            throw new IllegalArgumentException("Id cannot be null!");
        Id = id;

        if (term <= 0)
            throw new IllegalArgumentException("Term of account should be a positive integer!");

        Term = term;
        DaysLeft = term;
        if (percent <= 0)
            throw new IllegalArgumentException("Percent should be a positive float!");

        Percent = percent;
        if (amountOfMoney <= 0)
            throw new IllegalArgumentException("Amount of money should be a positive float!");

        AmountOfMoney = amountOfMoney;
        IsDoubtful = doubtfulness;
        LimitIfIsDoubtful = limitIfIsDoubtful;
        if (limitIfIsDoubtful <= 0)
            throw new IllegalArgumentException("Limit for account should be a positive float!");

        DailyIncome = Percent * AmountOfMoney / 36500;
    }

    @Override
    public void AddDailyIncome() {
        MonthlyIncome += DailyIncome;
    }

    public void AddMonthlyIncome() {
        AmountOfMoney += MonthlyIncome;
    }
}
