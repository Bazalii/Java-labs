package com.itmo.banks.banksStructure.implementations;

public class DepositSumWithPercent {

    private float _sum;
    private float _percent;

    public DepositSumWithPercent(float sum, float percent) {
        if (sum <= 0)
            throw new IllegalArgumentException("Sum of an account should be a positive float!");
        setSum(sum);

        if (percent <= 0)
            throw new IllegalArgumentException("Percent of an account should be a positive float!");
        setPercent(percent);
    }

    public float getSum() {
        return _sum;
    }

    public void setSum(float sum) {
        _sum = sum;
    }

    public float getPercent() {
        return _percent;
    }

    public void setPercent(float percent) {
        _percent = percent;
    }
}
