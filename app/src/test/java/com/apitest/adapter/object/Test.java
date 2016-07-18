package com.apitest.adapter.object;

/**
 * Created by Administrator on 2016/6/8.
 */
public class Test
{
    @org.junit.Test
    public void playDuck() throws Exception
    {
        Duck duck = new GreenHeadDuck();
        WildTurkey wildTurkey=new WildTurkey();
        Duck duck1=new TurkeyAdapter(wildTurkey);

        duck.quack();
        duck.fly();

        wildTurkey.gobble();
        wildTurkey.fly();

        duck1.quack();
        duck1.fly();
    }
}
