package com.itmo.banks.BanksStructure;

import java.util.ArrayList;

public interface IHandler {
    ArrayList<String> sendNotifications();

    IMyDisposable subscribe(IMyObserver observer);
}
