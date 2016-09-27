package com.apitest;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPOutputStream;

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

    @Test
    public void testGzipStream() throws Exception
    {
        File fileIn = new File("");
        FileInputStream fileInputStream=new FileInputStream(fileIn);

        File fileOut = new File("");
        FileOutputStream fileOutputStream=new FileOutputStream(fileOut);
        GZIPOutputStream gzipOutputStream=new GZIPOutputStream(fileOutputStream);

        byte[] buffer=new byte[20480];

        int read;
        while ((read = fileInputStream.read(buffer))!=-1)
        {
            gzipOutputStream.write(buffer,0,read);
        }

        fileInputStream.close();
        gzipOutputStream.close();
        fileOutputStream.close();
    }
}