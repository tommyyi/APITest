package com.apitest.template.sort;

/**
 * Created by Administrator on 2016/6/13.
 */
public class Duck implements Comparable
{
    public int weight=(int)(Math.random()*100);
    @Override
    public int compareTo(Object object)
    {
        Duck duck=(Duck)object;
        if(this.weight>duck.weight)
            return 1;
        else if(this.weight<duck.weight)
            return -1;
        else
            return 0;
    }
}
