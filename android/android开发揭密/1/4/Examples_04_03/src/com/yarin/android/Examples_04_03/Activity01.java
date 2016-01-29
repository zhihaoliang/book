package com.yarin.android.Examples_04_03;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Activity01 extends Activity
{
	/* 声明TextView对象 */
	private TextView textview;
	private TextView textview2;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		/* 获得TextView对象 */
		textview = (TextView)this.findViewById(R.id.textview);
		textview2 = ( TextView ) findViewById( R.id.textview2 );
		
		String string = "TextView示例，欢迎使用！";
		String string2 = "TextView示例";
		
		/* 设置文本的颜色 */
		textview.setTextColor(Color.RED);
		/* 设置字体大小 */
		textview.setTextSize(20);
		/* 设置文字背景 */
		textview.setBackgroundColor(Color.BLUE);
		/* 设置TextView显示的文字 */
		textview.setText(string);
		
		textview2.setTextColor( Color.RED );
		textview2.setTextSize( 25 );
		textview2.setBackgroundColor(Color.YELLOW);
		textview2.setText(string2);
	}
}
