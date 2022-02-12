package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.SavingsAccount;
import com.itmo.banks.Tools.NotEnoughMoneyToWithdrawException;

import java.util.Formatter;

public class DebitAccount extends SavingsAccount {
    public DebitAccount(String id, int term, float percent, float amountOfMoney, Boolean doubtfulness, float limitIfIsDoubtful) {
        super(id, term, percent, amountOfMoney, doubtfulness, limitIfIsDoubtful);
    }

    @Override
    public void withdrawMoney(float amountOfMoney) throws NotEnoughMoneyToWithdrawException {
        if (amountOfMoney > AmountOfMoney) {
            Formatter f = new Formatter();
            f.format("This %s is about %n%S %c", "book", "java", '8');
            throw new NotEnoughMoneyToWithdrawException(String.format("You can withdraw only %f!", AmountOfMoney));
        }

        addMoney(-amountOfMoney);
    }

    @Override
    public void reduceDaysLeft() {
        DaysLeft -= 1;
    }
}
