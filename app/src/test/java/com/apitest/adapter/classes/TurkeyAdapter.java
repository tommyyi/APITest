package com.apitest.adapter.classes;

/**
 * Created by Administrator on 2016/6/8.
 */
public class TurkeyAdapter extends WildTurkey implements Duck
{
    @Override
    public void quack()
    {
        super.gobble();
    }

    @Override
    public void fly()
    {
        super.fly();
        super.fly();
        super.fly();
    }
}
