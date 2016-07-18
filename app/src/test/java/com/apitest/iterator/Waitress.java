package com.apitest.iterator;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/8.
 */
public class Waitress
{
    private CakeHouseMenu mCakeHouseMenu;
    private DinnerMenu mDinnerMenu;
    ArrayList<MenuComponent> mIterators;
    public Waitress()
    {
        mCakeHouseMenu=new CakeHouseMenu();
        mDinnerMenu=new DinnerMenu();

        mIterators=new ArrayList<MenuComponent>();
        mIterators.add(mCakeHouseMenu);
        mIterators.add(mDinnerMenu);
    }

    public void printMenu()
    {
        for (int index=0;index<mIterators.size();index++)
        {
            printOneMenu(mIterators.get(index));
        }
    }

    private void printOneMenu(MenuComponent menuComponent)
    {
        if (menuComponent.getIterator()!=null)
        {
            while (menuComponent.getIterator().hasNext())
            {
                MenuComponent menuComponentTemp= (MenuComponent) menuComponent.getIterator().next();
                if(menuComponentTemp.getIterator()!=null)
                    printOneMenu(menuComponentTemp);
                else
                    menuComponentTemp.print();
            }
        }
        else
            menuComponent.print();
    }

    public void printBreakfastMenu()
    {

    }

    public void printLunchMenu()
    {

    }

    public void printVegMenu()
    {

    }
}
