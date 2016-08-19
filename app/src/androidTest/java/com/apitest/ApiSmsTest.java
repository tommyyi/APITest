package com.apitest;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/8/1.
 */
@RunWith(AndroidJUnit4.class)
public class ApiSmsTest
{
    @Test
    public void testPay() throws Exception
    {
        String musicId="600967020000006658";
        String itemPrice="600";
        String musicType="2";
        String ip="";
        ApiSms.charge_by_api(null, InstrumentationRegistry.getTargetContext(), MainActivity.tianlaizhishengsid, MainActivity.tianlaizhishengchannel_id, MainActivity.tianlaizhishengcpparam, musicId, itemPrice, musicType,ip);
        Thread.sleep(1000000);
    }
}