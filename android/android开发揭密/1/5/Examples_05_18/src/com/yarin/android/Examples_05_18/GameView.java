package com.yarin.android.Examples_05_18;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

public class GameView extends View
{
	/* ����AnimationDrawable�������� */
	private AnimationDrawable	frameAnimation	= null;
	Context						mContext		= null;
	public GameView(Context context)
	{
		super(context);
		
		mContext = context;
		
		/* ����һ��ImageView������ʾ���� */
		ImageView img = new ImageView(mContext);
		
		/* װ�ض��������ļ� */
		img.setBackgroundResource(R.anim.frameanimation);		
		
		/* �������� */
		frameAnimation = (AnimationDrawable) img.getBackground();
		
		/* �����Ƿ�ѭ�� */
		frameAnimation.setOneShot( false );  
		
		/* ���ø�����ʾ�Ķ��� */
		this.setBackgroundDrawable(frameAnimation);
	}
	
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		switch ( keyCode )
		{
		case KeyEvent.KEYCODE_DPAD_UP:	
			/* ��ʼ���Ŷ��� */
			frameAnimation.start();
			break;
		}
		return true;
	}
}

