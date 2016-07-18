package com.apitest.status;

import com.apitest.status.imp.State;
import com.apitest.status.state.HasCoinState;
import com.apitest.status.state.OnReadyState;
import com.apitest.status.state.SoldOutState;

/**
 * Created by Administrator on 2016/6/15.
 */
public class CandyMachine implements State
{
    State mSoldOutState;
    State mOnReadyState;
    State mHasCoinState;
    State mCurrentState;
    int mCount = 0;

    public CandyMachine(int count)
    {
        this.mCount = count;
        mSoldOutState = new SoldOutState(this);
        mOnReadyState = new OnReadyState(this);
        mHasCoinState = new HasCoinState(this);

        if (mCount > 0)
        {
            mCurrentState = mOnReadyState;
        }
        else
        {
            mCurrentState = mSoldOutState;
        }
    }

    public State getHasCoinState()
    {
        return mHasCoinState;
    }

    public State getOnReadyState()
    {
        return mOnReadyState;
    }

    public State getSoldOutState()
    {
        return mSoldOutState;
    }

    public void setCurrentState(State currentState)
    {
        mCurrentState = currentState;
    }

    public int getCount()
    {
        return mCount;
    }

    @Override
    public void insertCoin()
    {
        mCurrentState.insertCoin();
    }

    @Override
    public void returnCoin()
    {
        mCurrentState.returnCoin();
    }

    @Override
    public void turnCrank()
    {
        mCurrentState.turnCrank();
    }

    @Override
    public void printState()
    {
        mCurrentState.printState();
    }

    public void releaseCandy()
    {
        if (mCount > 0)
        {
            mCount--;
            System.out.print("a candy rolling out\r\n");
        }
        else
            System.out.print("can not return candy\r\n");
    }
}