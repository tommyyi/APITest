package com.apitest;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.apitest.newpartner.Send2CMCC;
import com.apitest.newpartner.server.PartnerResponse;
import com.apitest.newpartner.server.ServerManager;
import com.apitest.newpartner.url.TiantianKuyinUrl;
import com.apitest.newpartner.url.YueYinYueUrl;

import java.io.IOException;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity implements OperationProgress
{
    public static final String juletangsid = "2a4470580c31b3afa25c15a38ed6628c84215728";
    public static final String juletangchannel_id = "014A1HA";
    public static final String juletangcpparam = "JH6653";

    public static final String tianlaizhishengsid = "14683393b19c2fce0554917a991be9ddab53582a";
    public static final String tianlaizhishengchannel_id = "014A1HJ";
    public static final String tianlaizhishengcpparam = "JH6038";

    public static final String yueyinyuesid = "5c90902c57e986dc30b64f6258e7d21f6d9cafbf";
    public static final String yueyinyuechannel_id = "014A1IB";
    public static final String yueyinyuecpparam = "JH6000";

    public static final String tiantiankuyinsid = "3632365c0defd1f6e04cfa9836207edd71a7315a";
    public static final String tiantiankuyinchannel_id = "014A1IJ";
    public static final String tiantiankuyincpparam = "JH322222";

    private TextView mTextView;
    private MyRunnable mMyRunnable;
    private String mImsi;
    private String mImei;
    private EditText mEditTextIp;
    private String mIP = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.tvProgressInfo);
        mEditTextIp = (EditText) findViewById(R.id.ip);
        mMyRunnable = new MyRunnable();

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        mImsi = telephonyManager.getSubscriberId();
        mImei = telephonyManager.getDeviceId();

        showProgressBar();
    }

    private void showProgressBar()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在获取IP地址...");
        progressDialog.setProgressDrawable(getResources().getDrawable(android.R.drawable.progress_horizontal));
        progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressDialog.show();

        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                URL2FetchIp url2FetchIp = new URL2FetchIp();
                try
                {
                    final String ip = url2FetchIp.getIp();
                    progressDialog.dismiss();
                    if (ip == null || ip.equals(""))
                    {
                        return;
                    }

                    MainActivity.this.runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            mEditTextIp.setText(ip);
                            mIP = ip;
                            Runnable runnable1 = new Runnable()
                            {
                                public void run()
                                {
                                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                                    boolean isOpen = inputMethodManager.isActive();//isOpen若返回true，则表示输入法打开
                                    while (!isOpen)
                                    {
                                        try
                                        {
                                            Thread.sleep(50);
                                            isOpen = inputMethodManager.isActive();
                                        }
                                        catch (InterruptedException e)
                                        {
                                            e.printStackTrace();
                                        }
                                    }
                                    inputMethodManager.hideSoftInputFromWindow(mEditTextIp.getWindowToken(), 0);
                                }
                            };
                            new Thread(runnable1).start();
                        }
                    });
                }
                catch (Exception e)
                {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }

    public void executeJuLeTang2yuan(View view)
    {
        String musicId = "600907000001208713";
        String itemPrice = "200";
        String musicType = "1";

        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        new MusicIdDialog(this, R.style.dialogstyle, juletangsid, juletangchannel_id, juletangcpparam, musicId, itemPrice, musicType, mIP).show();
        //ApiSms.charge_by_api(this,getApplicationContext(), juletangsid, juletangchannel_id, juletangcpparam, musicId, itemPrice, musicType);
    }

    public void executeJuLeTang1fen(View view)
    {
        String musicId = "600907000004805930";
        String itemPrice = "1";
        String musicType = "1";

        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        new MusicIdDialog(this, R.style.dialogstyle, juletangsid, juletangchannel_id, juletangcpparam, musicId, itemPrice, musicType, mIP).show();
        //ApiSms.charge_by_api(this,getApplicationContext(), juletangsid, juletangchannel_id, juletangcpparam, musicId, itemPrice, musicType);
    }

    public void executeJuLeTang6yuan(View view)
    {
        String musicId = "600967020000006653";
        String itemPrice = "600";
        String musicType = "2";

        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        ApiSms.charge_by_api(this, getApplicationContext(), juletangsid, juletangchannel_id, juletangcpparam, musicId, itemPrice, musicType, mIP);
    }

    public void executeTianLaiZhiSheng2yuan(View view)
    {
        String musicId = "b34c15896a53355c77732a4ed5b23565";
        String itemPrice = "200";
        String musicType = "1";

        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        new MusicIdDialog(this, R.style.dialogstyle, tianlaizhishengsid, tianlaizhishengchannel_id, tianlaizhishengcpparam, musicId, itemPrice, musicType, mIP).show();
        //ApiSms.charge_by_api(this,getApplicationContext(), tianlaizhishengsid, tianlaizhishengchannel_id, tianlaizhishengcpparam, musicId, itemPrice, musicType);
    }

    public void executeTianLaiZhiSheng1fen(View view)
    {
        String musicId = "600907000004805930";
        String itemPrice = "1";
        String musicType = "1";

        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        new MusicIdDialog(this, R.style.dialogstyle, tianlaizhishengsid, tianlaizhishengchannel_id, tianlaizhishengcpparam, musicId, itemPrice, musicType, mIP).show();
        //ApiSms.charge_by_api(this,getApplicationContext(), tianlaizhishengsid, tianlaizhishengchannel_id, tianlaizhishengcpparam, musicId, itemPrice, musicType);
    }

    public void executeTianLaiZhiSheng6yuan(View view)
    {
        String musicId = "600967020000006658";
        String itemPrice = "600";
        String musicType = "2";

        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        ApiSms.charge_by_api(this, getApplicationContext(), tianlaizhishengsid, tianlaizhishengchannel_id, tianlaizhishengcpparam, musicId, itemPrice, musicType, mIP);
    }

    @Override
    public void updateProgress(String info)
    {
        mMyRunnable.setInfo(mMyRunnable.getInfo() + info);
        runOnUiThread(mMyRunnable);
    }

    @Override
    public void reset()
    {
        mMyRunnable.setInfo("");
    }

    public void executeYueyinyue6yuan(View view)
    {
    }

    public void executeYueyinyue1fen(View view)
    {
        String musicId = "600907000004805934";
        String itemPrice = "1";
        String musicType = "1";

        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        new MusicIdDialog(this, R.style.dialogstyle, yueyinyuesid, yueyinyuechannel_id, yueyinyuecpparam, musicId, itemPrice, musicType, mIP).show();
        //ApiSms.charge_by_api(this,getApplicationContext(), yueyinyuesid, yueyinyuechannel_id, yueyinyuecpparam, musicId, itemPrice, musicType);
    }

    public void executeYueyinyue2yuan(View view)
    {
        String musicId = "40cd564fbb424b38a729b9eccbbd1cd0";
        String itemPrice = "200";
        String musicType = "1";

        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        new MusicIdDialog(this, R.style.dialogstyle, yueyinyuesid, yueyinyuechannel_id, yueyinyuecpparam, musicId, itemPrice, musicType, mIP).show();
        //ApiSms.charge_by_api(this,getApplicationContext(), yueyinyuesid, yueyinyuechannel_id, yueyinyuecpparam, musicId, itemPrice, musicType);
    }

    public void executeTiantiankuyin2yuan(View view)
    {
        String musicId = "b1b082e7d4aa6f123130b3ad7471c8d9";
        String itemPrice = "200";
        String musicType = "1";

        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        new MusicIdDialog(this, R.style.dialogstyle, tiantiankuyinsid, tiantiankuyinchannel_id, tiantiankuyincpparam, musicId, itemPrice, musicType, mIP).show();
        //        ApiSms.charge_by_api(this,getApplicationContext(), tiantiankuyinsid, tiantiankuyinchannel_id, tiantiankuyincpparam, musicId, itemPrice, musicType);
    }

    public void executeTiantiankuyin1fen(View view)
    {
        String musicId = "600907000004805942";
        String itemPrice = "1";
        String musicType = "1";

        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        new MusicIdDialog(this, R.style.dialogstyle, tiantiankuyinsid, tiantiankuyinchannel_id, tiantiankuyincpparam, musicId, itemPrice, musicType, mIP).show();
        //ApiSms.charge_by_api(this,getApplicationContext(), tiantiankuyinsid, tiantiankuyinchannel_id, tiantiankuyincpparam, musicId, itemPrice, musicType);
    }

    public void executeTiantiankuyin6yuan(View view)
    {
    }

    public void executeYueyinyue10yuan(View view)
    {
        String musicId = "633544Z01000100003";
        String itemPrice = "1000";
        String musicType = "2";
        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        ApiSms.charge_by_api(this, getApplicationContext(), yueyinyuesid, yueyinyuechannel_id, yueyinyuecpparam, musicId, itemPrice, musicType, mIP);
    }

    public void executeYueyinyue30yuan(View view)
    {
        String musicId = "633544Z01000100002";
        String itemPrice = "3000";
        String musicType = "2";

        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        ApiSms.charge_by_api(this, getApplicationContext(), yueyinyuesid, yueyinyuechannel_id, yueyinyuecpparam, musicId, itemPrice, musicType, mIP);
    }

    public void executeTiantiankuyin10yuan(View view)
    {
        String musicId = "632555Z01000100001";
        String itemPrice = "1000";
        String musicType = "2";

        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        ApiSms.charge_by_api(this, getApplicationContext(), tiantiankuyinsid, tiantiankuyinchannel_id, tiantiankuyincpparam, musicId, itemPrice, musicType, mIP);
    }

    public void executeTiantiankuyin30yuan(View view)
    {
        String musicId = "632555Z01000100002";
        String itemPrice = "3000";
        String musicType = "2";

        mIP = mEditTextIp.getText().toString();
        if (mIP.equals(""))
        {
            Toast.makeText(getApplicationContext(), "请输入ip地址", Toast.LENGTH_LONG).show();
            return;
        }
        ApiSms.charge_by_api(this, getApplicationContext(), tiantiankuyinsid, tiantiankuyinchannel_id, tiantiankuyincpparam, musicId, itemPrice, musicType, mIP);
    }

    public void executeYueyinyue30yuanNewPartner(View view)
    {
        Observable.create(new Observable.OnSubscribe<PartnerResponse>()
        {
            @Override
            public void call(Subscriber<? super PartnerResponse> subscriber)
            {
                try
                {
                    Response<PartnerResponse> execute = ServerManager.getAPI().getInfo(YueYinYueUrl.get1(mImsi, mImei)).execute();
                    PartnerResponse partnerResponse = execute.body();
                    if (partnerResponse.getMessage().equals("success"))
                    {
                        subscriber.onNext(partnerResponse);
                    }
                    else
                    {
                        updateProgress("获取指令失败\r\n");
                    }
                }
                catch (IOException e)
                {
                    updateProgress("悦音乐30元执行发生异常\r\n");
                    updateProgress(e.getMessage());
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<PartnerResponse>()
        {
            @Override
            public void call(PartnerResponse partnerResponse)
            {
                updateProgress("获取指令成功\r\n");
                Send2CMCC.work(getApplicationContext(), partnerResponse, MainActivity.this);
            }
        });
    }

    public void executeTiantiankuyin30yuanNewPartner(View view)
    {
        Observable.create(new Observable.OnSubscribe<PartnerResponse>()
        {
            @Override
            public void call(Subscriber<? super PartnerResponse> subscriber)
            {
                try
                {
                    Response<PartnerResponse> execute = ServerManager.getAPI().getInfo(TiantianKuyinUrl.get1(mImsi, mImei)).execute();
                    PartnerResponse partnerResponse = execute.body();
                    if (partnerResponse.getMessage().equals("success"))
                    {
                        subscriber.onNext(partnerResponse);
                    }
                    else
                    {
                        updateProgress("获取指令失败\r\n");
                    }
                }
                catch (IOException e)
                {
                    updateProgress("天天酷音30元执行发生异常\r\n");
                    updateProgress(e.getMessage());
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<PartnerResponse>()
        {
            @Override
            public void call(PartnerResponse partnerResponse)
            {
                updateProgress("获取指令成功\r\n");
                Send2CMCC.work(getApplicationContext(), partnerResponse, MainActivity.this);
            }
        });
    }

    public void juletang2_1fen(View view)
    {
        Juletang2Execute.charge_by_api(this,getApplicationContext());
    }

    private class MyRunnable implements Runnable
    {
        public String getInfo()
        {
            return info;
        }

        public void setInfo(String info)
        {
            this.info = info;
        }

        public String info = "";

        @Override
        public void run()
        {
            mTextView.setText(info);
        }
    }
}
