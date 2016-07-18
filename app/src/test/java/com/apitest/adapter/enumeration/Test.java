package com.apitest.adapter.enumeration;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class Test
{
    @org.junit.Test
    public void test() throws Exception
    {
        List<String> list=new ArrayList<String>();
        list.add("first\r\n");
        list.add("second\r\n");
        list.add("third\r\n");

        Enumeration<String> enumeration = new MyEnumeration<String>(list);
        Iterator<String> iterator=new MyIterator<String>(enumeration);

        while (iterator.hasNext())
            System.out.print(iterator.next());
    }
}
