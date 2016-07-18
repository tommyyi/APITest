package com.apitest.template.drink;

/**
 * Created by Administrator on 2016/6/13.
 */
public class Test
{
    @org.junit.Test
    public void test() throws Exception
    {
        Coffee coffee=new Coffee();
        Tea tea=new Tea();
        coffee.prepareRecipe();
        tea.prepareRecipe();
    }
}
