package com.apitest.template.sort;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/6/13.
 */
public class Test
{
    @org.junit.Test
    public void test() throws Exception
    {
        Duck duck[] = {new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck(), new Duck()};

        display(duck);
        Arrays.sort(duck);
        display(duck);
    }

    private void display(Duck[] duck)
    {
        int length=duck.length;
        for(int index=0;index<length;index++)
            System.out.print("index="+index+", weight="+duck[index].weight+"\r\n");
        System.out.print("\r\n");
    }
}
