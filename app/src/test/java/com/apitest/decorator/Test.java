package com.apitest.decorator;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2016/6/14.
 */
public class Test
{
    @org.junit.Test
    public void test() throws Exception
    {
        Drink order;

        order=new Coffee("Espresso",5.0f);
        order=new Decorator(order,"Milk",3.0f);
        order=new Decorator(order,"Milk",3.0f);
        order=new Decorator(order,"Milk",3.0f);

        System.out.print(order.getDescription()+"\r\n");

        AtomicReference<InputStream> inputStream = new AtomicReference<InputStream>();
        FileInputStream fileInputStream;
        FilterInputStream filterInputStream;
        DataInputStream dataInputStream;
    }
}
