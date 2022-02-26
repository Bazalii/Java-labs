package com.itmo.banks.BanksStructure;

import java.util.List;

public interface IHandler {
    List<String> sendNotifications();

    IDisposable subscribe(IObserver observer);
}
