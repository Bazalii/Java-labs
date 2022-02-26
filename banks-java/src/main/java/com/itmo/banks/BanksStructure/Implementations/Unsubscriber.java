package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.IDisposable;
import com.itmo.banks.BanksStructure.IObserver;

import java.util.List;

public class Unsubscriber implements IDisposable {
    private final String _name;

    private final List<IObserver> _observers;

    private final IObserver _observer;

    public Unsubscriber(String name, List<IObserver> observers, IObserver observer) {
        _name = name;
        _observers = observers;
        _observer = observer;
    }

    public void dispose() {
        _observers.remove(_observer);
    }

    public String getName() {
        return _name;
    }
}
