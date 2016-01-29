package com.yarin.android.Examples_03_01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Activity03 extends Activity
{
//	private TextView Long;
//	private EditText Edit;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		/* 设置显示main3.xml布局 */
		setContentView(R.layout.main3);
		/* findViewById(R.id.button3)取得布局main.xml中的button3 */
		Button button = (Button) findViewById(R.id.button3);
		
//		Long = (TextView) findViewById(R.id.Long);
//	     Edit = (EditText) findViewById(R.id.Edit);
		
		/* 监听button的事件信息 */
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				/* 新建一个Intent对象 */
				Intent intent = new Intent();
				/* 指定intent要启动的类 */
				intent.setClass(Activity03.this, Activity01.class);
				/* 启动一个新的Activity */
				startActivity(intent);
				/* 关闭当前的Activity */
				Activity03.this.finish();
			//Long.setText("ccccccccccc");
				
			}
		});
	}
}

