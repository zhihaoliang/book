package com.yarin.android.Examples_05_14;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class Activity01 extends Activity
{
	TextView	mTextView	= null;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/* ����DisplayMetrics���� */
		DisplayMetrics dm = new DisplayMetrics();
		
		/* ȡ�ô������� */
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		/* ���ڵĿ�� */
		int screenWidth = dm.widthPixels;
		
		/* ���ڵĸ߶� */
		int screenHeight = dm.heightPixels;

		mTextView = (TextView) findViewById(R.id.TextView01);

		mTextView.setText("��Ļ��ȣ�" + screenWidth + "\n��Ļ�߶ȣ�" + screenHeight);

	}
}
