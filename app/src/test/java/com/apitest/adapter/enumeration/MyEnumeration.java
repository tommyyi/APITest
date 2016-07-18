package com.apitest.adapter.enumeration;

import java.util.Enumeration;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class MyEnumeration<T> implements Enumeration<T>
{
    List<T> mList;
    private int index;

    public MyEnumeration(List<T> list)
    {
        mList = list;
        index=0;
    }

    @Override
    public boolean hasMoreElements()
    {
        if(index< mList.size())
            return true;
        else
            return false;
    }

    @Override
    public T nextElement()
    {
        return mList.get(index++);
    }
}
