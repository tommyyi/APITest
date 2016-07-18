package com.apitest.factory;

/**
 * Created by Administrator on 2016/6/8.
 */
public abstract class AbstractOrder
{
    public abstract void order(AbstractFactory abstractFactory);
    public String getType()
    {
        /*String str="";
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            str = br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }*/
        return "cheese";
    }
}
