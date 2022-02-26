package com.itmo.banks.banksStructure.implementations;

import com.itmo.banks.banksStructure.Account;

public class CreditAccount extends Account {
    private final float _commission;

    public CreditAccount(String id, int term, float commission, float amountOfMoney, Boolean doubtfulness, float limitIfIsDoubtful) {
        if (id == null)
            throw new IllegalArgumentException("Id cannot be null!");
        this.setId(id);

        if (term <= 0)
            throw new IllegalArgumentException("Term of account should be a positive integer!");
        this.setTerm(term);

        if (commission <= 0)
            throw new IllegalArgumentException("Commission should be a positive float!");
        _commission = commission;

        if (amountOfMoney <= 0)
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        this.setAmountOfMoney(amountOfMoney);

        setDoubtful(doubtfulness);

        if (limitIfIsDoubtful <= 0)
            throw new IllegalArgumentException("Limit for account should be a positive float!");
        this.setLimitIfIsDoubtful(limitIfIsDoubtful);
    }

    @Override
    public void addDailyIncome() {
        if (getAmountOfMoney() < 0) {
            setAmountOfMoney(getAmountOfMoney() - _commission);
        }
    }

    @Override
    public void withdrawMoney(float amountOfMoney) {
        this.setAmountOfMoney(this.getAmountOfMoney() - amountOfMoney);
    }

    @Override
    public void reduceDaysLeft() {
        setDaysLeft(getDaysLeft() - 1);
        if (getDaysLeft() == 0) {
            setDaysLeft(getDaysLeft() + 180);
        }
    }
}
