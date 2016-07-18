package com.apitest.command.imp;

/**
 * Created by Administrator on 2016/6/15.
 */
public interface Control
{
    void buttonOn(int slot);
    void buttonOff(int slot);
    void undo();
    void batchExecuteOn();
}
