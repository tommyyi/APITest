package com.apitest.adapter.object;

/**
 * Created by Administrator on 2016/6/8.
 */
public class GreenHeadDuck implements Duck
{
    @Override
    public void quack()
    {
        System.out.print("GreenDuck quack\r\n");
    }

    @Override
    public void fly()
    {
        System.out.print("GreenDuck fly\r\n");
    }
}
