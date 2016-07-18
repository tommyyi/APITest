package com.apitest.observer.standard;

/**
 * Created by Administrator on 2016/6/8.
 */
public class Weather
{
    public Weather(float temperature, float humidity, float pressure)
    {
        mTemperature = temperature;
        mHumidity = humidity;
        mPressure = pressure;
    }

    float mTemperature;
    float mHumidity;
    float mPressure;
}
