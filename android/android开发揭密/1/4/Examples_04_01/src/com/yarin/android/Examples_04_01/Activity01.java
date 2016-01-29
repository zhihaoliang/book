package com.yarin.android.Examples_04_01;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/**
 * 
 * �ؼ��¼�ͨ��������ؼ��ļ������������������¼�
 * ���������¼���ͨ����дonKeyDown����
 * ���������¼���ͨ����дonKeyUp����
 * ���ʵ���¼���ͨ��ʵ��onTouchEvent����
 * ʾ����ʹ����Toast�ؼ���
 * Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
 * ��ʾ��ʾ��Ϣ
 *
 */

public class Activity01 extends Activity
{

	public void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		//���Button����
		Button button_ok = (Button) findViewById(R.id.ok);
		//����Button�ؼ�������
		button_ok.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				//���ﴦ���¼�
				DisplayToast("�����OK��ť");
			}
		});

	}

	/* �����������������¼� */
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			case KeyEvent.KEYCODE_DPAD_CENTER:
				DisplayToast("���£��м�");
				break;
			case KeyEvent.KEYCODE_DPAD_UP:
				DisplayToast("���£��Ϸ����");
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				DisplayToast("���£��·����");
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				DisplayToast("���£������");
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				DisplayToast("���£��ҷ����");
				break;
			case KeyEvent.KEYCODE_0:
				DisplayToast("���£�0");
				break;
		}
		return super.onKeyDown(keyCode, event);
	}
	/* �����������������¼� */
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			case KeyEvent.KEYCODE_DPAD_CENTER:
				DisplayToast("�����м�");
				break;
			case KeyEvent.KEYCODE_DPAD_UP:
				DisplayToast("�����Ϸ����");
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				DisplayToast("�����·����");
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				DisplayToast("���������");
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				DisplayToast("�����ҷ����");
				break;
			case KeyEvent.KEYCODE_0:
				DisplayToast("����0");
				break;
			
		}
		
		return super.onKeyUp(keyCode, event);
	}
	
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event)
	{
		
		return super.onKeyMultiple(keyCode, repeatCount, event);
	}
	
	/* �����¼� */
    public boolean onTouchEvent(MotionEvent event)
    {
    	int iAction = event.getAction();
		if (iAction == MotionEvent.ACTION_CANCEL || 
			iAction == MotionEvent.ACTION_DOWN || 
			iAction == MotionEvent.ACTION_MOVE) 
		{
			return false;
		}
		//�õ����ʵ����λ��
		int x = (int) event.getX();
		int y = (int) event.getY();
		
		DisplayToast("���ʵ�����꣺("+Integer.toString(x)+","+Integer.toString(y)+")");
		
		return super.onTouchEvent(event);
	}
	
	/* ��ʾToast  */
	public void DisplayToast(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
}
