package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.IHandler;
import com.itmo.banks.BanksStructure.IMyDisposable;
import com.itmo.banks.BanksStructure.IMyObserver;

import java.util.ArrayList;

public class PercentChangesHandler implements IHandler {
    private final String _bankName;

    private final ArrayList<IMyObserver> _observers = new ArrayList<>();

    public PercentChangesHandler(String bankName) {
        _bankName = bankName;
    }

    public ArrayList<String> Notify() {
        ArrayList<String> output = new ArrayList<>();
        for (IMyObserver observer : _observers) {
            output.add(observer.Notify());
        }
        return output;
    }

    public IMyDisposable Subscribe(IMyObserver observer) {
        if (!_observers.contains(observer)) {
            _observers.add(observer);
        }

        return new Unsubscriber(_bankName, _observers, observer);
    }

    public String GetBankName() {
        return _bankName;
    }
}
