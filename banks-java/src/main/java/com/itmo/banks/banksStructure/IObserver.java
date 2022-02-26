package com.itmo.banks.banksStructure;

public interface IObserver {
    void subscribe(IHandler handler);

    void unsubscribe(String objectName);

    String showNotification();
}
