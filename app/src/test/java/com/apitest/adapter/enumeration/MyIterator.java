package com.apitest.adapter.enumeration;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/6/8.
 */
public class MyIterator<T> implements Iterator<T>
{
    public MyIterator(Enumeration<T> enumeration)
    {
        mEnumeration = enumeration;
    }

    Enumeration<T> mEnumeration;

    @Override
    public boolean hasNext()
    {
        return mEnumeration.hasMoreElements();
    }

    @Override
    public T next()
    {
        return mEnumeration.nextElement();
    }

    @Override
    public void remove()
    {
        try
        {
            throw new Exception("can not adapt");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
