package com.itmo.banks.BanksStructure;

public interface IPercentCalculator extends IObservable {
    float calculateDepositPercent(float amountOfMoney);

    float calculateDebitPercent(float amountOfMoney);

    float calculateCreditCommission(float amountOfMoney);
}
