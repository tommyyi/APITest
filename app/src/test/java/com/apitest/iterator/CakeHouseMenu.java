package com.apitest.iterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/6/8.
 */
public class CakeHouseMenu extends MenuComponent implements Iterator
{
    ArrayList<MenuComponent> mMenuItemArrayList;
    private int mCurrentPosition = 0;

    public CakeHouseMenu()
    {
        mMenuItemArrayList = new ArrayList<MenuComponent>();
        addItem("meat", "beef meat", false, 20);
        addItem("meat", "beef meat", false, 20);
        addItem("meat", "beef meat", false, 20);
        addItem("meat", "beef meat", false, 20);
        addItem("meat", "beef meat", false, 20);
    }

    private void addItem(String name, String des, boolean isveg, float price)
    {
        MenuItem menuItem = new MenuItem(name, des, isveg, price);
        mMenuItemArrayList.add(menuItem);
    }

    @Override
    public boolean hasNext()
    {
        return mCurrentPosition < mMenuItemArrayList.size();
    }

    @Override
    public Object next()
    {
        return mMenuItemArrayList.get(mCurrentPosition++);
    }

    @Override
    public void remove()
    {
        mMenuItemArrayList.remove(mCurrentPosition);
    }

    @Override
    public void print()
    {
        System.out.print("this is cake house menu\r\n");
    }

    public Iterator getIterator()
    {
        return this;
    }
}
