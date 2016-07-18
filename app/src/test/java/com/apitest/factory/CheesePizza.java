package com.apitest.factory;

/**
 * Created by Administrator on 2016/6/8.
 */
public class CheesePizza extends Pizza
{
    public CheesePizza()
    {
        super.setName("CheesePizza");
    }

    @Override
    public void prepare()
    {
        System.out.print("prepare CheesePizza\r\n");
    }
}
