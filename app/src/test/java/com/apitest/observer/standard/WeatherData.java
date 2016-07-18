package com.apitest.observer.standard;

import org.junit.Test;

import java.util.Enumeration;
import java.util.Observable;

/**
 * Created by Administrator on 2016/6/8.
 */
public class WeatherData extends Observable
{
    private static WeatherData weatherData;
    float mTemperature;
    float mHumidity;
    float mPressure;

    private WeatherData()
    {
        System.out.print(this.getClass()+"\r\n");
    }

    public static WeatherData getInstance()
    {
        if (weatherData == null)
        {
            synchronized (WeatherData.class)
            {
                if (weatherData == null)
                {
                    weatherData = new WeatherData();
                }
            }
        }
        return weatherData;
    }

    public void dataChange(float temperature, float humidity, float pressure)
    {
        mTemperature = temperature;
        mHumidity = humidity;
        mPressure = pressure;
        Weather weather = new Weather(mTemperature, mHumidity, mPressure);

        setChanged();
        notifyObservers(weather);
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

}
