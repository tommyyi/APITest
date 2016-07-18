package com.apitest.adapter.object;

/**
 * Created by Administrator on 2016/6/8.
 */
public class TurkeyAdapter implements Duck
{
    private Turkey mTurkey;

    public TurkeyAdapter(Turkey turkey)
    {
        mTurkey = turkey;
    }

    @Override
    public void quack()
    {
        mTurkey.gobble();
    }

    @Override
    public void fly()
    {
        mTurkey.fly();
    }
}
