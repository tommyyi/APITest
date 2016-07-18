package com.apitest.factory;

/**
 * Created by Administrator on 2016/6/8.
 */
public class ChineseOrder extends AbstractOrder
{
    @Override
    public void order(AbstractFactory abstractFactory)
    {
        String type = getType();
        Pizza pizza= abstractFactory.getPizza(type);

        if(pizza!=null)
        {
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.bake();
        }
    }
}
