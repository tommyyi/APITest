package com.apitest.status.state;

import com.apitest.status.CandyMachine;
import com.apitest.status.imp.State;

/**
 * Created by Administrator on 2016/6/15.
 */
public class HasCoinState implements State
{
    CandyMachine mCandyMachine;

    public HasCoinState(CandyMachine candyMachine)
    {
        mCandyMachine = candyMachine;
    }

    @Override
    public void insertCoin()
    {
        System.out.print("coin has already inserted, please turn crank\r\n");
    }

    @Override
    public void returnCoin()
    {
        System.out.print("coin returned\r\n");
        mCandyMachine.setCurrentState(mCandyMachine.getOnReadyState());
    }

    @Override
    public void turnCrank()
    {
        mCandyMachine.releaseCandy();
        if(mCandyMachine.getCount()>0)
            mCandyMachine.setCurrentState(mCandyMachine.getOnReadyState());
        else
            mCandyMachine.setCurrentState(mCandyMachine.getSoldOutState());
    }

    @Override
    public void printState()
    {
        System.out.print("state: has coin state\r\n\n");
    }
}
