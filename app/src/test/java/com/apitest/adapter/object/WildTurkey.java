package com.apitest.adapter.object;

/**
 * Created by Administrator on 2016/6/8.
 */
public class WildTurkey implements Turkey
{
    @Override
    public void gobble()
    {
        System.out.print("WildTurkey gobble\r\n");
    }

    @Override
    public void fly()
    {
        System.out.print("WildTurkey fly\r\n");
    }
}
