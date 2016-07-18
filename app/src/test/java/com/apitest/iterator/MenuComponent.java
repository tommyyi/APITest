package com.apitest.iterator;

import java.util.Iterator;

/**
 * Created by Administrator on 2016/6/13.
 */
public abstract class MenuComponent
{
    public String getName()
    {
        return "";
    }

    public String getDescription()
    {
        return "";
    }

    public boolean isVegetable()
    {
        return false;
    }

    public float getPrice()
    {
        return 0;
    }

    public abstract void print();

    public Iterator getIterator()
    {
        return null;
    }
}
