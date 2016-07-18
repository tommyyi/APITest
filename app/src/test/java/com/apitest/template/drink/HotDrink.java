package com.apitest.template.drink;

/**
 * Created by Administrator on 2016/6/13.
 */
public abstract class HotDrink
{
    public HotDrinkImp mHotDrinkImp;

    public void setHotDrinkImp(HotDrinkImp hotDrinkImp)
    {
        mHotDrinkImp = hotDrinkImp;
    }

    public final void prepareRecipe()
    {
        boil();
        mHotDrinkImp.brew();
        pourInCup();
        if (want2AddCondiment())
        {
            mHotDrinkImp.addCondiment();
        }
    }

    public final void pourInCup()
    {
        System.out.print("pour in cup\r\n");
    }

    public final void boil()
    {
        System.out.print("boil\r\n");
    }

    public boolean want2AddCondiment()
    {
        return true;
    }
}
