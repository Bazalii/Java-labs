package com.itmo.banks.BanksStructure.Implementations;

public class DepositSumWithPercent {
    public float Sum;
    public float Percent;

    public DepositSumWithPercent(float sum, float percent) {
        if (sum <= 0) {
            throw new IllegalArgumentException("Sum of an account should be a positive float!");
        }

        Sum = sum;
        if (percent <= 0) {
            throw new IllegalArgumentException("Percent of an account should be a positive float!");
        }

        Percent = percent;
    }
}
