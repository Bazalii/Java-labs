package com.itmo.banks.BanksStructure.Implementations;

import com.itmo.banks.BanksStructure.IMyDisposable;
import com.itmo.banks.BanksStructure.IMyObserver;

import java.util.ArrayList;

public class Unsubscriber implements IMyDisposable {
    private final String _name;

    private final ArrayList<IMyObserver> _observers;

    private final IMyObserver _observer;

    public Unsubscriber(String name, ArrayList<IMyObserver> observers, IMyObserver observer) {
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
