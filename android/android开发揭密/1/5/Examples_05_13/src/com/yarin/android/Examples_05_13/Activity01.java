package com.yarin.android.Examples_05_13;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Activity01 extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		/* ����Ϊ�ޱ����� */
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		
		/* ����Ϊȫ��ģʽ */ 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		
		/* ����Ϊ���� */
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); 

		setContentView(R.layout.main);
	}
}
