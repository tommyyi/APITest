package com.apitest;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.util.Xml;

public class XmlParse
{
	public List<SMSEntity> mSMSEntityList=new ArrayList<SMSEntity>();

	public XmlParse(Context context,String XmlData) throws Exception
	{
		XmlPullParser parser = Xml.newPullParser();
		if(XmlData==null)
		{
		}
		else if(XmlData.equals(""))
		{
			
		}
		else if(XmlData!=null)
		{
			ByteArrayInputStream stream = new ByteArrayInputStream(XmlData.getBytes());
			parser.setInput(stream, null);
			Parser(parser);
		}
	}

	private void Parser(XmlPullParser parser) throws Exception
	{
		int count=-1;
		int eventType = parser.getEventType();
		while (eventType != XmlPullParser.END_DOCUMENT)
		{
			switch (eventType)
			{
				case XmlPullParser.START_DOCUMENT:
					break;
	
				case XmlPullParser.START_TAG:
					if(parser.getName().equals("response"))
					{
						parser.next();
					}
					else if(parser.getName().equals("content_sid"))
					{
						parser.next();
					}
					else if(parser.getName().equals("migu"))
					{
						parser.next();
					}
					else if(parser.getName().equals("music"))
					{
						parser.next();
					}
					else	if(parser.getName().equals("sms_num"))
					{
						SMSEntity mSMSEntity=new SMSEntity();
						parser.next();
						mSMSEntity.number=parser.getText();
						mSMSEntityList.add(mSMSEntity);
						count++;
						parser.next();
					}
					else	if(parser.getName().equals("sms"))
					{
						parser.next();
						mSMSEntityList.get(count).message=parser.getText();
						parser.next();
					}
					break;

				case XmlPullParser.END_TAG:
					if(parser.getName().equals("response"))
					{
						parser.next();
					}
					else if(parser.getName().equals("content_sid"))
					{
						parser.next();
					}
					else if(parser.getName().equals("migu"))
					{
						parser.next();
					}
					else if(parser.getName().equals("music"))
					{
						parser.next();
					}
					else	if(parser.getName().equals("sms_num"))
					{
						parser.next();
					}
					else	if(parser.getName().equals("sms"))
					{
						parser.next();
					}
					break;
	
				case XmlPullParser.END_DOCUMENT:
					return;
			}
			eventType = parser.next();
		}
	}
}