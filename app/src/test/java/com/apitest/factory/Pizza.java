package com.apitest.factory;

/**
 * Created by Administrator on 2016/6/8.
 */
public abstract class Pizza
{
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    private String name;

    public abstract void prepare();

    public void bake()
    {
        System.out.print("bake "+name+"\r\n");
    }

    public void cut()
    {
        System.out.print("cut "+name+"\r\n");
    }

    public void box()
    {
        System.out.print("box "+name+"\r\n");
    }
}
