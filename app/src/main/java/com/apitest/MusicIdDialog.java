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

    String mSid;
    String mChannel_id;
    String mCpparam;
    String mMusicId;
    String mItemPrice;
    String mMusicType;

    public MusicIdDialog(Context context)
    {
        super(context);
        setTitle(getContext().getResources().getString(R.string.promptinputmusicid));
    }

    public MusicIdDialog(Context context, int theme)
    {
        super(context, theme);
    }

    public MusicIdDialog(MainActivity context, String sid, String channel_id, String cpparam, String musicId, String itemPrice, String musicType)
    {
        super(context);
        this.mMainActivity=context;
        this.mSid=sid;
        this.mChannel_id=channel_id;
        this.mCpparam=cpparam;
        this.mMusicId=musicId;
        this.mItemPrice=itemPrice;
        this.mMusicType=musicType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        mCancel = (Button)findViewById(R.id.cancel);
        mConfirm = (Button)findViewById(R.id.confirm);
        mMusicidedittext = (EditText)findViewById(R.id.musicid);

        mConfirm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mMusicId=mMusicidedittext.getText().toString();
                ApiSms.charge_by_api(mMainActivity,mMainActivity, mSid, mChannel_id, mCpparam, mMusicId, mItemPrice, mMusicType);
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
