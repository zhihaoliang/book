package com.yarin.android.Examples_05_02;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class Activity01 extends Activity
{
	GameSurfaceView mGameSurfaceView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		/* ����GameSurfaceView���� */
		mGameSurfaceView = new GameSurfaceView(this);
		
		//������ʾGameSurfaceView��ͼ
		setContentView(mGameSurfaceView);
			
	}
	
	//�����¼�
	public boolean onTouchEvent(MotionEvent event)
	{
		return true;
	}
	
	//���������¼�
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
    	return true;
    }
    
	//���������¼�
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
		//�Ϸ����
		case KeyEvent.KEYCODE_DPAD_UP:
			mGameSurfaceView.y-=3;
			break;
		//�·����
		case KeyEvent.KEYCODE_DPAD_DOWN:
			mGameSurfaceView.y+=3;
			break;
		}
		return false;
	}
	
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event)
	{
		return true;
	}
}
