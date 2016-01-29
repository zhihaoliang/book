package com.yarin.android.Examples_03_04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Activity01 extends Activity
{
	private static final String	TAG	= "Activity01";

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.v(TAG, "onCreate");
		
		Button button1 = (Button) findViewById(R.id.button1);
		/* ����button���¼���Ϣ */
		button1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				/* �½�һ��Intent���� */
				Intent intent = new Intent();
				/* ָ��intentҪ�������� */
				intent.setClass(Activity01.this, Activity02.class);
				/* ����һ���µ�Activity */
				startActivity(intent);
				/* �رյ�ǰ��Activity */
				Activity01.this.finish();
			}
		});
		/******************************/
		Button button3 = (Button) findViewById(R.id.button3);
		/* ����button���¼���Ϣ */
		button3.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				/* �رյ�ǰ��Activity */
				Activity01.this.finish();
			}
		});
	}

	public void onStart()
	{
		super.onStart();
		Log.v(TAG, "onStart");
	}
	
	public void onResume()
	{
		super.onResume();
		Log.v(TAG, "onResume");
	}
	
	public void onPause()
	{
		super.onPause();
		Log.v(TAG, "onPause");
	}
	
	public void onStop()
	{
		super.onStop();
		Log.v(TAG, "onStop");
	}

	public void onDestroy()
	{
		super.onDestroy();
		Log.v(TAG, "onDestroy");
	}

	public void onRestart()
	{
		super.onRestart();
		Log.v(TAG, "onReStart");
	}
	
}
