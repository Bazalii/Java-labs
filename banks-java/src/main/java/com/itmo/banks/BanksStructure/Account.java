package com.itmo.banks.BanksStructure;

import com.itmo.banks.Tools.CannotWithdrawMoneyException;
import com.itmo.banks.Tools.NotEnoughMoneyToWithdrawException;

public abstract class Account {

    protected String Id;

    protected int Term;

    protected int DaysLeft;

    protected float AmountOfMoney;

    protected Boolean IsDoubtful;

    protected float LimitIfIsDoubtful;

    public abstract void AddDailyIncome();

    public abstract void WithdrawMoney(float amountOfMoney) throws NotEnoughMoneyToWithdrawException, CannotWithdrawMoneyException;

    public abstract void ReduceDaysLeft();

    public void AddMoney(float amountOfMoney) {
        AmountOfMoney += amountOfMoney;
    }

    public String GetId() {
        return Id;
    }

    public String GetBankId() {
        return Id.substring(0, Id.indexOf("_"));
    }

    public int GetTerm() {
        return Term;
    }

    public int GetDaysLeft() {
        return DaysLeft;
    }

    public int GetTermAndDaysLeftDiff() {
        return Term - DaysLeft;
    }

    public float GetAmountOfMoney() {
        return AmountOfMoney;
    }

    public void SetDoubtfulness(Boolean doubtfulness) {
        IsDoubtful = doubtfulness;
    }

    public Boolean GetDoubtfulness() {
        return IsDoubtful;
    }

    public float GetLimitIfIsDoubtful() {
        return LimitIfIsDoubtful;
    }
}
