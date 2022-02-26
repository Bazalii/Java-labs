package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.IHandler;
import com.itmo.banks.BanksStructure.IDisposable;
import com.itmo.banks.BanksStructure.IObserver;

import java.util.ArrayList;

public class PercentChangesHandler implements IHandler {
    private final String _bankName;

    private final ArrayList<IObserver> _observers = new ArrayList<>();

    public PercentChangesHandler(String bankName) {
        _bankName = bankName;
    }

    public ArrayList<String> sendNotifications() {
        ArrayList<String> output = new ArrayList<>();
        for (IObserver observer : _observers) {
            output.add(observer.showNotification());
        }
        return output;
    }

    public IDisposable subscribe(IObserver observer) {
        if (!_observers.contains(observer)) {
            _observers.add(observer);
        }

        return new Unsubscriber(_bankName, _observers, observer);
    }

    public String getBankName() {
        return _bankName;
    }
}
