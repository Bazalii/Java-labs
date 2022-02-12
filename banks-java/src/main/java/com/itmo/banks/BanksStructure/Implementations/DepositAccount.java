package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.SavingsAccount;
import com.itmo.banks.Tools.CannotWithdrawMoneyException;

public class DepositAccount extends SavingsAccount {
    public DepositAccount(String id, int term, float percent, float amountOfMoney, Boolean doubtfulness, float limitIfIsDoubtful) {
        super(id, term, percent, amountOfMoney, doubtfulness, limitIfIsDoubtful);
    }

    @Override
    public void WithdrawMoney(float amountOfMoney) throws CannotWithdrawMoneyException {
        throw new CannotWithdrawMoneyException("You cannot withdraw money from deposit account!");
    }

    @Override
    public void ReduceDaysLeft() {
        DaysLeft -= 1;
    }
}
