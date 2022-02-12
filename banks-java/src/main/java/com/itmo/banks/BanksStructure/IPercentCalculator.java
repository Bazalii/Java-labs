package com.itmo.banks.BanksStructure;

public interface IPercentCalculator extends IMyObservable {
    float CalculateDepositPercent(float amountOfMoney);

    float CalculateDebitPercent(float amountOfMoney);

    float CalculateCreditCommission(float amountOfMoney);
}
