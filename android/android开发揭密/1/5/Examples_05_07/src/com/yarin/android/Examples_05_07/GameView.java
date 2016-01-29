package com.yarin.android.Examples_05_07;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements Runnable
{
	/* ����Paint���� */
	private Paint	mPaint		= null;

	/* ��������ͼƬ���� */
	Bitmap			mBitQQ		= null;
	Bitmap			mBitDestTop	= null;

	int				miDTX		= 0;

	public GameView(Context context)
	{
		super(context);
		
		mPaint = new Paint();
		
		miDTX		= 0;
		
		/* ����Դ�ļ���װ��ͼƬ */
		//getResources()->�õ�Resources
		//getDrawable()->�õ���Դ�е�Drawable���󣬲���Ϊ��Դ����ID
		//getBitmap()->�õ�Bitmap
		mBitQQ = ((BitmapDrawable) getResources().getDrawable(R.drawable.qq)).getBitmap();
		
		mBitDestTop = ((BitmapDrawable) getResources().getDrawable(R.drawable.desktop)).getBitmap();
		
		new Thread(this).start();
	}
	
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		/* ����Ч�� */
		canvas.drawColor(Color.GRAY);
		
		/* ����Ļ(0,0)������ͼƬmBitQQ */
		GameView.drawImage(canvas, mBitQQ, 0, 0);
		
		/* ���ƶ�λ�ð�ָ���ü����������л��� */
		//getWidth()->�õ�ͼƬ�Ŀ��
		//getHeight()->�õ�ͼƬ�ĸ߶�
		GameView.drawImage(canvas, mBitDestTop, miDTX, mBitQQ.getHeight(), mBitDestTop.getWidth(), mBitDestTop.getHeight()/2, 0, 0);
		
	}
	
	// �����¼�
	public boolean onTouchEvent(MotionEvent event)
	{
		return true;
	}


	// ���������¼�
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		//�����
		if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT)
		{
			if (miDTX > 0)
			{
				miDTX--;
			}
		}
		//�ҷ����
		else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)
		{
			if (miDTX+mBitDestTop.getWidth() < 320)
			{
				miDTX++;
			}
		}
		return true;
	}


	// ���������¼�
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		return false;
	}


	public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event)
	{
		return true;
	}
	
	
	/**
	 * �̴߳���
	 */
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
			postInvalidate();
		}
	}
	
	/*------------------------------------
	 * ����ͼƬ
	 *
	 * @param		x ��Ļ�ϵ�x����	
	 * @param		y ��Ļ�ϵ�y����
	 * @param		w Ҫ���Ƶ�ͼƬ�Ŀ��	
	 * @param		h Ҫ���Ƶ�ͼƬ�ĸ߶�
	 * @param		bxͼƬ�ϵ�x����
	 * @param		byͼƬ�ϵ�y����
	 *
	 * @return		null
	 ------------------------------------*/
	public static void drawImage(Canvas canvas, Bitmap blt, int x, int y, int w, int h, int bx, int by)
	{
		Rect src = new Rect();// ͼƬ
		Rect dst = new Rect();// ��Ļ
		
		src.left = bx;
		src.top = by;
		src.right = bx + w;
		src.bottom = by + h;
		
		dst.left = x;
		dst.top = y;
		dst.right = x + w;
		dst.bottom = y + h;
		canvas.drawBitmap(blt, src, dst, null);
		
		src = null;
		dst = null;
	}

	/**
	 * ����һ��Bitmap
	 * @param canvas	����
	 * @param bitmap	ͼƬ
	 * @param x			��Ļ�ϵ�x����
	 * @param y			��Ļ�ϵ�y����
	 */
	public static void drawImage(Canvas canvas, Bitmap bitmap, int x, int y)
	{
		/* ����ͼ�� */
		canvas.drawBitmap(bitmap, x, y, null);
	}
}

