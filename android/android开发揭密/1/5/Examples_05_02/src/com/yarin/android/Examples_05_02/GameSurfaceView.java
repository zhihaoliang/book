package com.yarin.android.Examples_05_02;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView 
							 implements SurfaceHolder.Callback,Runnable
{
	//����ѭ��
	boolean			mbLoop			= false;
	
	//����SurfaceHolder����
	SurfaceHolder	mSurfaceHolder	= null;
	int				miCount			= 0;
	int				y				= 50;


	public GameSurfaceView(Context context)
	{
		super(context);

		// ʵ����SurfaceHolder
		mSurfaceHolder = this.getHolder();

		// ��ӻص�
		mSurfaceHolder.addCallback(this);
		this.setFocusable(true);

		mbLoop = true;
	}


	// ��surface�Ĵ�С�����ı�ʱ����
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
	{

	}

	// ��surface����ʱ����
	public void surfaceCreated(SurfaceHolder holder)
	{
		//������ͼ�߳�
		new Thread(this).start();
	}

	// ��surface����ʱ����
	public void surfaceDestroyed(SurfaceHolder holder)
	{
		// ֹͣѭ��
		mbLoop = false;
	}

	// ��ͼѭ��
	public void run()
	{
		while (mbLoop)
		{
			try
			{
				Thread.sleep(200);
			}
			catch (Exception e)
			{
				
			}
			synchronized( mSurfaceHolder )
			{
				Draw();
			}
			
		}
	}

	// ��ͼ����
	public void Draw()
	{
		//�����������õ�canvas
		Canvas canvas= mSurfaceHolder.lockCanvas();

		if (mSurfaceHolder==null || canvas == null )
		{
			return;
		}
		
		if (miCount < 100)
		{
			miCount++;
		}
		else
		{
			miCount = 0;
		}
		// ��ͼ
		Paint mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLACK);
		//���ƾ���--��������
		canvas.drawRect(0, 0, 320, 480, mPaint);
		switch (miCount % 4)
		{
		case 0:
			mPaint.setColor(Color.BLUE);
			break;
		case 1:
			mPaint.setColor(Color.GREEN);
			break;
		case 2:
			mPaint.setColor(Color.RED);
			break;
		case 3:
			mPaint.setColor(Color.YELLOW);
			break;
		default:
			mPaint.setColor(Color.WHITE);
			break;
		}
		// ���ƾ���--�������ǽ���ϸ����
		canvas.drawCircle((320 - 25) / 2, y, 50, mPaint);
		// ���ƺ���������ƺ�������������ʾ
		mSurfaceHolder.unlockCanvasAndPost(canvas);
	}
}

