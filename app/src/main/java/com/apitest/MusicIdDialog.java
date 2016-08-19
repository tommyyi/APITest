package com.apitest;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/6/6.
 */
public class MusicIdDialog extends Dialog
{
    private MainActivity mMainActivity;
    private Button mCancel;
    private Button mConfirm;
    private EditText mMusicidedittext;
    private EditText mMusicPriceEdittext;

    String mSid;
    String mChannel_id;
    String mCpparam;
    String mMusicId;
    String mItemPrice;
    String mMusicType;
    private String mIp;

    public MusicIdDialog(Context context)
    {
        super(context);
    }

    public MusicIdDialog(Context context, int theme)
    {
        super(context, theme);
    }

    public MusicIdDialog(MainActivity context, int theme, String sid, String channel_id, String cpparam, String musicId, String itemPrice, String musicType,String ip)
    {
        super(context,theme);
        this.mMainActivity=context;
        this.mSid=sid;
        this.mChannel_id=channel_id;
        this.mCpparam=cpparam;
        this.mMusicId=musicId;
        this.mItemPrice=itemPrice;
        this.mMusicType=musicType;
        mIp = ip;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        setTitle(getContext().getResources().getString(R.string.promptinputmusicid));

        mCancel = (Button)findViewById(R.id.cancel);
        mConfirm = (Button)findViewById(R.id.confirm);
        mMusicidedittext = (EditText)findViewById(R.id.musicid);
        mMusicPriceEdittext = (EditText)findViewById(R.id.musicprice);

        String defaultIDHint = mMusicidedittext.getHint().toString().replace("默认值", mMusicId);
        String defaultPriceHint = mMusicPriceEdittext.getHint().toString().replace("默认值", mItemPrice);
        mMusicidedittext.setHint(defaultIDHint);
        mMusicPriceEdittext.setHint(defaultPriceHint);

        mConfirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String musicId = mMusicidedittext.getText().toString();
                String musicPrice = mMusicPriceEdittext.getText().toString();
                if (!musicId.equals(""))
                {
                    mMusicId= musicId;
                }
                if (!musicPrice.equals(""))
                {
                    mItemPrice= musicPrice;
                }
                ApiSms.charge_by_api(mMainActivity,mMainActivity, mSid, mChannel_id, mCpparam, mMusicId, mItemPrice, mMusicType,mIp);
                dismiss();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dismiss();
            }
        });
    }
}
