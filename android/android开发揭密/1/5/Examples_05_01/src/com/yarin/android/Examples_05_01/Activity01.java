package com.yarin.android.Examples_05_01;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class Activity01 extends Activity
{
	private static final int	REFRESH		= 0x000001;
	
	/* ����GameView����� */
	private GameView			mGameView	= null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		/* ʵ����GameView���� */
		this.mGameView = new GameView(this);

		// ������ʾΪ�����Զ����View(GameView)
		setContentView(mGameView);

		// �����߳�
		new Thread(new GameThread()).start();
	}

	Handler	myHandler	= new Handler() 
	{
		//���յ���Ϣ����
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case Activity01.REFRESH:
				mGameView.invalidate();
				break;
			}
			super.handleMessage(msg);
		}			
	};

	class GameThread implements Runnable
	{
		public void run()
		{
			while (!Thread.currentThread().isInterrupted())
			{
				Message message = new Message();
				message.what = Activity01.REFRESH;
				//������Ϣ
				Activity01.this.myHandler.sendMessage(message);
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					Thread.currentThread().interrupt();
				}
			}
		}
	}
	/**
	 * ��Ȼ���Խ�GameThread������д
	 * ͬ�����Ը��½��棬���Ҳ�����Ҫ
	 * Handler�ڽ�����Ϣ
	class GameThread implements Runnable
	{
		public void run()
		{
			while (!Thread.currentThread().isInterrupted())
			{
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
					Thread.currentThread().interrupt();
				}
				//ʹ��postInvalidate����ֱ�����߳��и��½���
				mGameView.postInvalidate();
			}
		}
	}
	*/
	
	//��ϸ�¼������������
	//��Ȼ��Щ�¼�Ҳ����д��GameView��
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
			mGameView.y-=3;
			break;
		//�·����
		case KeyEvent.KEYCODE_DPAD_DOWN:
			mGameView.y+=3;
			break;
		}
		return false;
	}
	
	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event)
	{
		return true;
	}
}
