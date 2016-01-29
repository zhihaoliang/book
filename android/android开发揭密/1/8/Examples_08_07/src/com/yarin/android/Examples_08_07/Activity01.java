package com.yarin.android.Examples_08_07;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Activity01 extends Activity
{
	private WebView mWebView;
	private PersonalData mPersonalData;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mPersonalData = new PersonalData();
		mWebView = (WebView)this.findViewById(R.id.WebView01);
		//����֧��JavaScript
		mWebView.getSettings().setJavaScriptEnabled(true);
		//�ѱ����һ��ʵ����ӵ�js��ȫ�ֶ���window�У�
        //�����Ϳ���ʹ��window.PersonalData���������ķ���
		mWebView.addJavascriptInterface(this, "PersonalData");
		//������ҳ
		mWebView.loadUrl("file:///android_asset/PersonalData.html");
	}

	//��js�ű��е��õõ�PersonalData����
	public PersonalData getPersonalData()
	{
		return mPersonalData;
	}
	
	//js�ű��е�����ʾ������
	class PersonalData
	{
		String  mID;
		String  mName;
		String  mAge;
		String  mBlog;	
		public PersonalData()
		{
			this.mID="123456789";
			this.mName="Android";
			this.mAge="100";
			this.mBlog="http://yarin.javaeye.com";
		}
		public String getID()
		{
			return mID;
		}
		public String getName()
		{
			return mName;
		}
		public String getAge()
		{
			return mAge;
		}
		public String getBlog()
		{
			return mBlog;
		}
	}
}



