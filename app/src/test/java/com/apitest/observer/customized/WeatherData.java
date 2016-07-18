package com.apitest.observer.customized;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class WeatherData implements Subject
{
    float mTemperature;
    float mHumidity;
    float mPressure;
    List<Observer> mObserverList;

    public WeatherData()
    {
        mObserverList = new ArrayList<Observer>();
    }

    @Test
    public void weatherCenter() throws Exception
    {
        BulletinA bulletinA = new BulletinA();
        BulletinB bulletinB = new BulletinB();
        WeatherData weatherData = new WeatherDataBuilder().createWeatherData();

        weatherData.register(bulletinA);
        weatherData.register(bulletinB);

        weatherData.dataChange(10, 20, 30);

        weatherData.unregister(bulletinA);
        weatherData.dataChange(10, 20, 30);
    }

    public void dataChange(float temperature, float humidity, float pressure)
    {
        mTemperature = temperature;
        mHumidity = humidity;
        mPressure = pressure;
        notifyObserver();
    }

    public void setPressure(float pressure)
    {
        mPressure = pressure;
    }

    public void setTemperature(float temperature)
    {
        mTemperature = temperature;
    }

    public void setHumidity(float humidity)
    {
        mHumidity = humidity;
    }

    @Override
    public void register(Observer observer)
    {
        mObserverList.add(observer);
    }

    @Override
    public void unregister(Observer observer)
    {
        mObserverList.remove(observer);
    }

    @Override
    public void notifyObserver()
    {
        for (int index = 0; index < mObserverList.size(); index++)
        {
            mObserverList.get(index).update(mTemperature, mHumidity, mPressure);
            System.out.print("\r\n");
        }
    }
}
