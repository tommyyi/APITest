package com.apitest.status.state;

import com.apitest.status.CandyMachine;
import com.apitest.status.imp.State;

/**
 * Created by Administrator on 2016/6/15.
 */
public class SoldOutState implements State
{
    CandyMachine mCandyMachine;

    public SoldOutState(CandyMachine candyMachine)
    {
        mCandyMachine = candyMachine;
    }

    @Override
    public void insertCoin()
    {
        System.out.print("no candy, can not insert coin\r\n");
    }

    @Override
    public void returnCoin()
    {
        System.out.print("you did not insert coin\r\n");
    }

    @Override
    public void turnCrank()
    {
        System.out.print("no candy\r\n");
    }

    @Override
    public void printState()
    {
        System.out.print("state: sold out state\r\n\n");
    }
}
