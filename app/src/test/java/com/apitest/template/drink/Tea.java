package com.apitest.template.drink;

/**
 * Created by Administrator on 2016/6/13.
 */
public class Tea extends HotDrink implements HotDrinkImp
{
    public Tea()
    {
        super();
        super.setHotDrinkImp(this);
    }

    @Override
    public void brew()
    {
        System.out.print("brew tea\r\n");
    }

    @Override
    public void addCondiment()
    {
        System.out.print("add condiment to tea\r\n\r\n");
    }
}
