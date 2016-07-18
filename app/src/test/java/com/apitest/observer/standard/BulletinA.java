package com.apitest.observer.standard;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 2016/6/8.
 */
public class BulletinA implements Observer
{
    float mTemperature;
    float mHumidity;
    float mPressure;

    private void show()
    {
        System.out.print("Bulletin A\ntemperature="+mTemperature+"humidity="+mHumidity+"pressure="+mPressure+"\r\n");
    }

    @Override
    public void update(Observable observable, Object object)
    {
        Weather weather=(Weather)object;
        mTemperature=weather.mTemperature;
        mHumidity=weather.mHumidity;
        mPressure=weather.mPressure;
        show();
    }
}
