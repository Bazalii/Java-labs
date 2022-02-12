package com.itmo.banks.BanksStructure;

public interface IMyObserver {
    void Subscribe(IHandler handler);

    void Unsubscribe(String objectName);

    String Notify();
}
