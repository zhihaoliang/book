package com.yarin.android.Examples_03_01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

/**
 * 在Examples_02_01工程中一共使用2两个Activity,
 * 前面我们知道没使用一个Activity都必须在“AndroidManifest.xml”中
 * 进行声明。
 */
public class Activity01 extends Activity
{
	protected static final String getNameEditText = null;
	private TextView Long;
	private EditText Edit;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		/* 设置显示main.xml布局 */
		setContentView(R.layout.main);
		/* findViewById(R.id.button1)取得布局main.xml中的button1 */
		Button button1 = (Button) findViewById(R.id.button1);
	     Long = (TextView) findViewById(R.id.Long);
	     Edit = (EditText) findViewById(R.id.Edit);
		/* 监听button的事件信息 */
		button1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				/* 新建一个Intent对象 */
				Intent intent = new Intent();
				/* 指定intent要启动的类 */
				intent.setClass(Activity01.this, Activity02.class);
				/* 启动一个新的Activity */
				startActivity(intent);
				/* 关闭当前的Activity */
				Activity01.this.finish();
				Long.setText("TheSend");
			}
		});
		
		Button button4 = (Button) findViewById(R.id.button4);
		/*监听button的事件信息*/
		button4.setOnClickListener( new Button.OnClickListener()
		{
		public void onClick(View v)
		{
			Activity01.this.finish();
		}
		});
	}
}
