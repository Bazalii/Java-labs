import com.itmo.banks.BanksStructure.IPercentCalculator;
import com.itmo.banks.BanksStructure.Implementations.*;
import com.itmo.banks.Tools.CannotWithdrawMoneyException;
import com.itmo.banks.Tools.DoubtfulAccountException;
import com.itmo.banks.Tools.NotEnoughMoneyToWithdrawException;
import com.itmo.banks.Tools.TheSameAccountsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BanksTest {
    private static CentralBank _centralBank;

    private static Client _firstClient = new Client("Peter", "Pavlov", "Nevskiy pr., 31, 25", "65478953");

    private static Client _secondClient = new Client("Paul", "Jonson", "Ligovskii pr., 110, 37", "65895231");

    private static final Client _doubtfulClient = new Client("Alex", "Jonson", null, null);

    @BeforeEach
    void SetUp() {
        _centralBank = new CentralBank();
        IPercentCalculator _percentCalculator = new CommonPercentCalculator(
                new ArrayList<>() {{
                    add(new DepositSumWithPercent(10000, 2F));
                    add(new DepositSumWithPercent(50000, 4F));
                    add(new DepositSumWithPercent(100000, 6F));
                }},
                2F,
                30F);
        _centralBank.registerBank(new Bank("Sber", _percentCalculator, 180, 10000F));
        _centralBank.registerBank(new Bank("Spb", _percentCalculator, 365, 5000F));
        _firstClient = new Client("Peter", "Pavlov", "Nevskiy pr., 31, 25", "65478953");
        _centralBank.getBankByName("Sber").registerClient(_firstClient);
        _centralBank.getBankByName("Sber").createDebitAccount(_firstClient, 100000F);
        _secondClient = new Client("Paul", "Jonson", "Ligovskii pr., 110, 37", "65895231");
        _centralBank.getBankByName("Spb").registerClient(_secondClient);
        _centralBank.getBankByName("Spb").createCreditAccount(_secondClient, 50000F);
    }

    @Test
    public void TransferMoney_FirstClientTransfersMoneyToTheSecondClientFromAnotherBank_FromFirstAccountMoneyAreWithdrawnTheSecondAccountIsReplenished()
            throws TheSameAccountsException, NotEnoughMoneyToWithdrawException, DoubtfulAccountException, CannotWithdrawMoneyException {
        _centralBank.transferMoney(_firstClient.accounts.get(0), _secondClient.accounts.get(0), 30000F);
        assertEquals(70000F, _firstClient.accounts.get(0).getAmountOfMoney());
        assertEquals(80000F, _secondClient.accounts.get(0).getAmountOfMoney());
    }

    @Test
    public void TransferMoney_ClientWithDoubtfulAccountTransfersMoreMoneyThanAllowed_CatchException() {
        _centralBank.getBankByName("Sber").registerClient(_doubtfulClient);
        _centralBank.getBankByName("Sber").createCreditAccount(_doubtfulClient, 15000F);
        assertThrows(DoubtfulAccountException.class, () -> _centralBank.transferMoney(_doubtfulClient.accounts.get(0), _firstClient.accounts.get(0), 15000F));
    }

    @Test
    public void CancelTransaction_CentralBankCancelsDoubtfulTransferTransaction_FirstAccountIsReplenishedFromSecondAccountMoneyAreWithdrawn()
            throws TheSameAccountsException, NotEnoughMoneyToWithdrawException, DoubtfulAccountException, CannotWithdrawMoneyException {
        _centralBank.transferMoney(_firstClient.accounts.get(0), _secondClient.accounts.get(0), 30000F);
        _centralBank.cancelTransaction(_centralBank.getTransaction(1));
        assertEquals(100000F, _firstClient.accounts.get(0).getAmountOfMoney());
        assertEquals(50000F, _secondClient.accounts.get(0).getAmountOfMoney());
    }

    @Test
    public void ScrollDays_ClientChecksWhatWillHappenWithTheirAccountInSeveralDays_DebitAndDepositAccountsHaveMoreMoney() {
        _centralBank.getBankByName("Sber").createDepositAccount(_firstClient, 110000F);
        _centralBank.scrollDays(30);
        assertEquals(100164.38F, _firstClient.accounts.get(0).getAmountOfMoney());
        assertEquals(110542.47F, _firstClient.accounts.get(1).getAmountOfMoney());
        assertEquals(50000F, _secondClient.accounts.get(0).getAmountOfMoney());
    }
}

