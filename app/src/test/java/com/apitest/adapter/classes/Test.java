package com.apitest.adapter.classes;

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
        Duck duck1=new TurkeyAdapter();
        Turkey turkey=new TurkeyAdapter();

        duck.quack();
        duck.fly();

        /*wildTurkey.gobble();
        wildTurkey.fly();*/

        duck1.quack();
        duck1.fly();

        /*turkey.gobble();
        turkey.fly();*/
    }
}
