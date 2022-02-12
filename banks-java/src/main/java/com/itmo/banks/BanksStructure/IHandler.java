package com.itmo.banks.BanksStructure;

import java.util.ArrayList;

public interface IHandler {
    ArrayList<String> Notify();

    IMyDisposable Subscribe(IMyObserver observer);
}
