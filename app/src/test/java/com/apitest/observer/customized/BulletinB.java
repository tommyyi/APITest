package com.apitest.observer.customized;

/**
 * Created by Administrator on 2016/6/8.
 */
public class BulletinB implements Observer
{
    float mTemperature;
    float mHumidity;
    float mPressure;
    @Override
    public void update(float temperature, float humidity, float pressure)
    {
        mTemperature=temperature;
        mHumidity=humidity;
        mPressure=pressure;

        show();
    }

    private void show()
    {
        System.out.print("Bulletin B\r\ntemperature="+mTemperature+"humidity="+mHumidity+"pressure="+mPressure+"\r\n");
    }
}
