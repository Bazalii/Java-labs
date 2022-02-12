package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.IPercentCalculator;

import java.util.ArrayList;

public class CommonPercentCalculator implements IPercentCalculator {
    private final ArrayList<DepositSumWithPercent> _depositSumsWithPercents;

    private final float _debitPercent;

    private final float _creditCommission;

    public CommonPercentCalculator(ArrayList<DepositSumWithPercent> depositSumsWithPercents, float debitPercent, float creditCommission) {
        if (depositSumsWithPercents.size() != 3) {
            throw new IllegalArgumentException("List should contain three pairs of sums with percents");
        }

        _depositSumsWithPercents = depositSumsWithPercents;

        if (debitPercent <= 0) {
            throw new IllegalArgumentException("Debit percent should be a positive float!");
        }

        _debitPercent = debitPercent;
        if (creditCommission <= 0) {
            throw new IllegalArgumentException("Credit commission should be a positive float!");
        }

        _creditCommission = creditCommission;
    }

    public float calculateDepositPercent(float amountOfMoney) {
        if (amountOfMoney < _depositSumsWithPercents.get(0).getSum()) {
            return _depositSumsWithPercents.get(0).getPercent();
        }

        if (_depositSumsWithPercents.get(0).getSum() <= amountOfMoney && amountOfMoney < _depositSumsWithPercents.get(1).getSum()) {
            return _depositSumsWithPercents.get(1).getPercent();
        }

        return _depositSumsWithPercents.get(2).getPercent();
    }

    public float calculateDebitPercent(float amountOfMoney) {
        return _debitPercent;
    }

    public float calculateCreditCommission(float amountOfMoney) {
        return _creditCommission;
    }
}
