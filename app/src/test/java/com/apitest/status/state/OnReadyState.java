package com.apitest.status.state;

import com.apitest.status.CandyMachine;
import com.apitest.status.imp.State;

/**
 * Created by Administrator on 2016/6/15.
 */
public class OnReadyState implements State
{
    CandyMachine mCandyMachine;

    public OnReadyState(CandyMachine candyMachine)
    {
        mCandyMachine = candyMachine;
    }

    @Override
    public void insertCoin()
    {
        System.out.print("coin inserted\r\n");
        mCandyMachine.setCurrentState(mCandyMachine.getHasCoinState());
    }

    @Override
    public void returnCoin()
    {
        System.out.print("you did not insert coin\r\n");
    }

    @Override
    public void turnCrank()
    {
        System.out.print("please insert coin\r\n");
    }

    @Override
    public void printState()
    {
        System.out.print("state: on ready state\r\n\n");
    }
}
