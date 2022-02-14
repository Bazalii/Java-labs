package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.Account;

public class CreditAccount extends Account {
    private final float _commission;

    public CreditAccount(String id, int term, float commission, float amountOfMoney, Boolean doubtfulness, float limitIfIsDoubtful) {
        if (id == null)
            throw new IllegalArgumentException("Id cannot be null!");
        this.id = id;

        if (term <= 0)
            throw new IllegalArgumentException("Term of account should be a positive integer!");
        this.term = term;

        if (commission <= 0)
            throw new IllegalArgumentException("Commission should be a positive float!");
        _commission = commission;

        if (amountOfMoney <= 0)
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        this.amountOfMoney = amountOfMoney;

        isDoubtful = doubtfulness;

        if (limitIfIsDoubtful <= 0)
            throw new IllegalArgumentException("Limit for account should be a positive float!");
        this.limitIfIsDoubtful = limitIfIsDoubtful;
    }

    @Override
    public void addDailyIncome() {
        if (amountOfMoney < 0) {
            amountOfMoney -= _commission;
        }
    }

    @Override
    public void withdrawMoney(float amountOfMoney) {
        this.amountOfMoney -= amountOfMoney;
    }

    @Override
    public void reduceDaysLeft() {
        daysLeft -= 1;
        if (daysLeft == 0) {
            daysLeft += 180;
        }
    }
}
