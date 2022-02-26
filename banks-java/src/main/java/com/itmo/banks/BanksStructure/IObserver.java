package com.itmo.banks.BanksStructure;

public interface IObserver {
    void subscribe(IHandler handler);

    void unsubscribe(String objectName);

    String showNotification();
}
