package com.apitest.factory;

/**
 * Created by Administrator on 2016/6/8.
 */
public class GreekPizza extends Pizza
{
    public GreekPizza()
    {
        super.setName("GreekPizza");
    }

    @Override
    public void prepare()
    {
        System.out.print("prepare GreekPizza");
    }
}
