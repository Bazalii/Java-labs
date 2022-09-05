package com.itmo.banks.banksStructure.implementations;

import com.itmo.banks.banksStructure.IDisposable;
import com.itmo.banks.banksStructure.IHandler;
import com.itmo.banks.banksStructure.IObserver;

import java.util.ArrayList;
import java.util.List;

public class PercentChangesHandler implements IHandler {

    private final String _bankName;
    private final List<IObserver> _observers = new ArrayList<>();

    public PercentChangesHandler(String bankName) {
        _bankName = bankName;
    }

    public List<String> sendNotifications() {
        List<String> output = new ArrayList<>();

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
