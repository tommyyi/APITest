package com.apitest.observer.customized;

/**
 * Created by Administrator on 2016/6/8.
 */
public interface Subject
{
    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObserver();
}
