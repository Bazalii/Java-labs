package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.Account;

public class CreditAccount extends Account {
    private final float _commission;

    public CreditAccount(String id, int term, float commission, float amountOfMoney, Boolean doubtfulness, float limitIfIsDoubtful) {
        if (id == null)
            throw new IllegalArgumentException("Id cannot be null!");
        Id = id;

        if (term <= 0) {
            throw new IllegalArgumentException("Term of account should be a positive integer!");
        }

        Term = term;
        if (commission <= 0) {
            throw new IllegalArgumentException("Commission should be a positive float!");
        }

        _commission = commission;
        if (amountOfMoney <= 0) {
            throw new IllegalArgumentException("Amount of money should be a positive float!");
        }

        AmountOfMoney = amountOfMoney;
        IsDoubtful = doubtfulness;
        if (limitIfIsDoubtful <= 0) {
            throw new IllegalArgumentException("Limit for account should be a positive float!");
        }

        LimitIfIsDoubtful = limitIfIsDoubtful;
    }

    @Override
    public void AddDailyIncome() {
        if (AmountOfMoney < 0) {
            AmountOfMoney -= _commission;
        }
    }

    @Override
    public void WithdrawMoney(float amountOfMoney) {
        AmountOfMoney -= amountOfMoney;
    }

    @Override
    public void ReduceDaysLeft() {
        DaysLeft -= 1;
        if (DaysLeft == 0) {
            DaysLeft += 180;
        }
    }
}
