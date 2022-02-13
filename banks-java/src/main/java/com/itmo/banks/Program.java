package com.itmo.banks;

import com.itmo.banks.ConsoleInterface.Application;
import com.itmo.banks.Tools.*;

public class Program {
    public static void main(String[] args)
            throws ClientWithoutNecessaryField, NotEnoughMoneyToWithdrawException, NotFoundException, CannotWithdrawMoneyException, DoubtfulAccountException {
        Application application = new Application();
        application.process();
    }
}
