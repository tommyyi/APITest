package com.apitest.factory;

/**
 * Created by Administrator on 2016/6/8.
 */
public class ChineseFactory extends AbstractFactory
{
    @Override
    public Pizza getPizza(String type)
    {
        Pizza pizza=null;
        if(type.equals("cheese"))
        {
            pizza=new CheesePizza();
        }
        return pizza;
    }
}
