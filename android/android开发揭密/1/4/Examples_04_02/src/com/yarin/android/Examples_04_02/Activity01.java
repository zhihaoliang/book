package com.yarin.android.Examples_04_02;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class Activity01 extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		//���ﹹ��KeyEvent�����书��Ϊ���ؼ��Ĺ���
		//������ǰ����������ִ�з��ؼ�����
		KeyEvent key = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK);

		//���ﴫ��Ĳ������������Լ�������KeyEvent����key
		return super.onKeyDown(key.getKeyCode(), key);
	}
}
