package com.apitest;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.telephony.TelephonyManager;
import android.test.ApplicationTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest
{

    private Context mTargetContext = InstrumentationRegistry.getTargetContext();

    @Before
    public void setUp() throws Exception
    {
    }

    @Test
    public void testImsi() throws Exception
    {
        TelephonyManager telephoneService = (TelephonyManager) mTargetContext.getSystemService(Context.TELEPHONY_SERVICE);

        String imsi = telephoneService.getSubscriberId();
        String iccid = telephoneService.getSimSerialNumber();
        String imei = telephoneService.getDeviceId();
    }
}