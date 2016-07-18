package com.apitest.iterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/6/8.
 */
public class DinnerMenu extends MenuComponent implements Iterator
{
    private int mCurrentPosition=0;
    ArrayList<MenuComponent> mMenuItemsArrayList;

    public DinnerMenu()
    {
        mMenuItemsArrayList =new ArrayList<MenuComponent>();
        addItem("meat", "beef meat", false, 20);
        addItem("meat", "beef meat", false, 20);
        addItem("meat", "beef meat", false, 20);
        addItem("meat", "beef meat", false, 20);
        SubMenu menuComponent = new SubMenu();
        menuComponent.getMenuComponentArrayList().add(new SubMenu());
        addSubMenu(menuComponent);
    }

    private void addItem(String name, String des, boolean isveg, float price)
    {
        MenuItem menuItem = new MenuItem(name, des, isveg, price);
        mMenuItemsArrayList.add(menuItem);
    }

    @Override
    public boolean hasNext()
    {
        if(mCurrentPosition< mMenuItemsArrayList.size())
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public MenuComponent next()
    {
        int index=mCurrentPosition;
        mCurrentPosition++;
        if(index<mMenuItemsArrayList.size())
            return mMenuItemsArrayList.get(index);
        else
        {
            System.out.print("index=" + index + "\r\n");
            return null;
        }
    }

    @Override
    public void remove()
    {
        ArrayList<MenuComponent> array = new ArrayList<MenuComponent>();
        for(int index = 0; index< mMenuItemsArrayList.size(); index++)
        {
            if (index != mCurrentPosition)
            {
                array.add(mMenuItemsArrayList.get(index)) ;
            }
        }
        mMenuItemsArrayList =array;
    }

    private void addSubMenu(MenuComponent menuComponent)
    {
        try
        {
            mMenuItemsArrayList.add(menuComponent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void print()
    {
        System.out.print("this is dinner menu\r\n");
    }

    @Override
    public Iterator getIterator()
    {
        return this;
    }
}
