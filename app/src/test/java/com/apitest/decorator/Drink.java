package com.apitest.decorator;

/**
 * Created by Administrator on 2016/6/14.
 */
public abstract class Drink
{
    public String mDescription = "";
    private float mPrice = 0f;

    public Drink(String description, float price)
    {
        mDescription = description;
        mPrice = price;
    }

    public String getDescription()
    {
        return mDescription;
    }

    public void setDescription(String description)
    {
        mDescription = description;
    }

    public float getPrice()
    {
        return mPrice;
    }

    public void setPrice(float price)
    {
        mPrice = price;
    }
}
