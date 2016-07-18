package com.apitest.decorator;

import com.apitest.decorator.Drink;

/**
 * Created by Administrator on 2016/6/14.
 */
public class Decorator extends Drink
{
    public Decorator(Drink drink,String description, float price)
    {
        super(description+"+"+drink.getDescription(),price+drink.getPrice());
    }
}
