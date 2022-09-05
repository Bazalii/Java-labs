package com.itmo.banks.banksStructure;

import java.util.List;

public interface IHandler {
    List<String> sendNotifications();
    IDisposable subscribe(IObserver observer);
}
