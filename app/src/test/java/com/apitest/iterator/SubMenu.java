package com.apitest.iterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/6/13.
 */
public class SubMenu extends MenuComponent implements Iterator
{
    private int mCurrentIndex=0;

    public ArrayList<MenuComponent> getMenuComponentArrayList()
    {
        return mMenuComponentArrayList;
    }

    private ArrayList<MenuComponent> mMenuComponentArrayList;

    public SubMenu()
    {
        mMenuComponentArrayList = new ArrayList<MenuComponent>();
        addItem("sub menu meat","meat",false,1);
        addItem("sub menu meat","meat",false,1);
        addItem("sub menu meat","meat",false,1);
        addItem("sub menu meat","meat",false,1);
        addItem("sub menu meat","meat",false,1);
    }

    public void addItem(String name,String description,boolean isVegetable,float price)
    {
        MenuItem menuItem=new MenuItem(name,description,isVegetable,price);
        mMenuComponentArrayList.add(menuItem);
    }

    @Override
    public void print()
    {
        System.out.print("this is a sub menu\r\n");
    }

    @Override
    public boolean hasNext()
    {
        if(mCurrentIndex<mMenuComponentArrayList.size())
            return true;
        else
            return false;
    }

    @Override
    public MenuComponent next()
    {
        return mMenuComponentArrayList.get(mCurrentIndex++);
    }

    @Override
    public void remove()
    {
        mMenuComponentArrayList.remove(mCurrentIndex);
    }

    @Override
    public Iterator getIterator()
    {
        return this;
    }
}
