package com.apitest.template.drink;

/**
 * Created by Administrator on 2016/6/13.
 */
public class Coffee extends HotDrink implements HotDrinkImp
{
    public Coffee()
    {
        super();
        super.setHotDrinkImp(this);
    }

    @Override
    public void brew()
    {
        System.out.print("brew coffee\r\n");
    }

    @Override
    public void addCondiment()
    {
        System.out.print("add condiment to coffee\r\n\r\n");
    }
}
