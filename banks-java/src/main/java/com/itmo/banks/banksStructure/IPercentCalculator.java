package com.itmo.banks.banksStructure;

public interface IPercentCalculator extends IObservable {
    float calculateDepositPercent(float amountOfMoney);
    float calculateDebitPercent(float amountOfMoney);
    float calculateCreditCommission(float amountOfMoney);
}
