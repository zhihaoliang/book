package com.yarin.android.Examples_06_03;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;

public class Activity01 extends Activity
{
	private int  miCount = 0;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);	
	
		miCount=1000;
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			//�˳�Ӧ�ó���ʱ��������
			/* �����ʼ��ĵ�ַ */
			Uri uri = Uri.parse("mailto:fengsheng.studio@hotmail.com");  
			
			/* ����Intent */
			Intent it = new Intent(Intent.ACTION_SENDTO, uri);  
			
			/* �����ʼ������� */
			it .putExtra(android.content.Intent.EXTRA_SUBJECT, "���ݱ���");
			
			/* �����ʼ������� */
			it .putExtra(android.content.Intent.EXTRA_TEXT, "���μ�����"+miCount);
			
			/* ���� */
			startActivity(it);
			
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
