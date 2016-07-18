package com.apitest.iterator;

/**
 * Created by Administrator on 2016/6/8.
 */
public class MenuItem extends MenuComponent
{
    private String mName;
    private String mDescription;
    private boolean mIsVegetable;
    private float mPrice;

    public MenuItem(String name, String description, boolean isVegetable, float price)
    {
        mName = name;
        mDescription = description;
        mIsVegetable = isVegetable;
        mPrice = price;
    }

    @Override
    public String getName()
    {
        return mName;
    }

    public void setName(String name)
    {
        mName = name;
    }

    @Override
    public String getDescription()
    {
        return mDescription;
    }

    public void setDescription(String description)
    {
        mDescription = description;
    }

    @Override
    public boolean isVegetable()
    {
        return mIsVegetable;
    }

    public void setVegetable(boolean vegetable)
    {
        mIsVegetable = vegetable;
    }

    @Override
    public float getPrice()
    {
        return mPrice;
    }

    @Override
    public void print()
    {
        System.out.print("name="+mName+"\r\n");
    }

    public void setPrice(float price)
    {
        mPrice = price;
    }
}
