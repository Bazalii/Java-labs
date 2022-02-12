package com.itmo.banks.BanksStructure;

public interface IMyObserver {
    void subscribe(IHandler handler);

    void unsubscribe(String objectName);

    String showNotification();
}
