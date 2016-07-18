package com.apitest.observer;

import com.apitest.observer.standard.BulletinA;
import com.apitest.observer.standard.BulletinB;
import com.apitest.observer.standard.WeatherData;

/**
 * Created by Administrator on 2016/6/8.
 */
public class Test
{
    @org.junit.Test
    public void weatherCenter() throws Exception
    {
        final BulletinA bulletinA = new BulletinA();
        final BulletinB bulletinB = new BulletinB();
        for(int index=0;index<100;index++)
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                WeatherData weatherData = WeatherData.getInstance();
                weatherData.addObserver(bulletinA);
                weatherData.addObserver(bulletinB);
                weatherData.dataChange(10, 20, 30);

                weatherData.deleteObserver(bulletinA);
                weatherData.dataChange(10, 20, 30);
            }
        }.start();
        Thread.sleep(100000);
    }
}
