package com.itmo.banks.BanksStructure;

public interface IPercentCalculator extends IMyObservable {
    float calculateDepositPercent(float amountOfMoney);

    float calculateDebitPercent(float amountOfMoney);

    float calculateCreditCommission(float amountOfMoney);
}