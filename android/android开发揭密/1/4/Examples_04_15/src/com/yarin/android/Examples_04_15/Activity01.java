package com.yarin.android.Examples_04_15;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity01 extends Activity
{
	//����ImageView����
	ImageView	imageview;
	TextView	textview;
	//ImageView��alphaֵ��
	int			image_alpha	= 255;

	Handler		mHandler	= new Handler();
	//�ؼ��߳�
	boolean		isrung		= false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		isrung		= true;
		
		//���ImageView�Ķ���
		imageview = (ImageView) this.findViewById(R.id.ImageView01);
		textview = (TextView) this.findViewById(R.id.TextView01);
		
		//����imageview��ͼƬ��Դ��ͬ��������xml����������������д
		//android:src="@drawable/logo"
		imageview.setImageResource(R.drawable.logo);
		
		//����imageview��Alphaֵ
		imageview.setAlpha(image_alpha);

		//����һ���߳�����Alphaֵ�ݼ�
		new Thread(new Runnable() {
			public void run()
			{
				while (isrung)
				{
					try
					{

						Thread.sleep(200);
						//����Alphaֵ
						updateAlpha();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}

			}
		}).start();

		//������Ϣ֮�����imageview��ͼ
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg)
			{
				super.handleMessage(msg);
				imageview.setAlpha(image_alpha);
				textview.setText("����alphaֵ�ǣ�"+Integer.toString(image_alpha));
				//����
				imageview.invalidate();
			}
		};
	}
	
	public void updateAlpha()
	{
		if (image_alpha - 7 >= 0)
		{
			image_alpha -= 7;
		}
		else
		{
			image_alpha = 0;
			isrung = false;
		}
		//������Ҫ����imageview��ͼ����Ϣ
		mHandler.sendMessage(mHandler.obtainMessage());
	}
}
