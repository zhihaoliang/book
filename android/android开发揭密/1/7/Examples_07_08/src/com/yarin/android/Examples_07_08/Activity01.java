package com.yarin.android.Examples_07_08;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class Activity01 extends Activity
{
	/* 3����ť */
	private Button mButtonRingtone;
	private Button mButtonAlarm;
	private Button mButtonNotification;

	/* �Զ�������� */
	public static final int ButtonRingtone			= 0;
	public static final int ButtonAlarm				= 1;
	public static final int ButtonNotification		= 2;
	/* �����ļ��� */
	private String strRingtoneFolder = "/sdcard/music/ringtones";
	private String strAlarmFolder = "/sdcard/music/alarms";
	private String strNotificationFolder = "/sdcard/music/notifications";


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	
		mButtonRingtone = (Button) findViewById(R.id.ButtonRingtone);
		mButtonAlarm = (Button) findViewById(R.id.ButtonAlarm);
		mButtonNotification = (Button) findViewById(R.id.ButtonNotification);
		/* ������������ */
		mButtonRingtone.setOnClickListener(new Button.OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				if (bFolder(strRingtoneFolder))
				{
					//��ϵͳ��������
					Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
					//����Ϊ����RINGTONE
					intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_RINGTONE);
					//������ʾ��title
					intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "������������");
					//���������֮�󷵻ص���ǰ��Activity
					startActivityForResult(intent, ButtonRingtone);
				}
			}
		});
		/* ������������ */
		mButtonAlarm.setOnClickListener(new Button.OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				if (bFolder(strAlarmFolder))
				{
					//��ϵͳ��������
					Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
					//�����������ͺ�title
					intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
					intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "������������");
					//���������֮�󷵻ص���ǰ��Activity
					startActivityForResult(intent, ButtonAlarm);
				}
			}
		});
		/* ����֪ͨ���� */
		mButtonNotification.setOnClickListener(new Button.OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				if (bFolder(strNotificationFolder))
				{
					//��ϵͳ��������
					Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
					//�����������ͺ�title
					intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
					intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "����֪ͨ����");
					//���������֮�󷵻ص���ǰ��Activity
					startActivityForResult(intent, ButtonNotification);
				}
			}
		});
	}
	/* ����������֮��Ļص����� */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// TODO Auto-generated method stub
		if (resultCode != RESULT_OK)
		{
			return;
		}
		switch (requestCode)
		{
			case ButtonRingtone:
				try
				{
					//�õ�����ѡ�������
					Uri pickedUri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
					//������ѡ����������ó�ΪĬ��
					if (pickedUri != null)
					{
						RingtoneManager.setActualDefaultRingtoneUri(Activity01.this, RingtoneManager.TYPE_RINGTONE, pickedUri);
					}
				}
				catch (Exception e)
				{
				}
				break;
			case ButtonAlarm:
				try
				{
					//�õ�����ѡ�������
					Uri pickedUri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
					//������ѡ����������ó�ΪĬ��
					if (pickedUri != null)
					{
						RingtoneManager.setActualDefaultRingtoneUri(Activity01.this, RingtoneManager.TYPE_ALARM, pickedUri);
					}
				}
				catch (Exception e)
				{
				}
				break;
			case ButtonNotification:
				try
				{
					//�õ�����ѡ�������
					Uri pickedUri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
					//������ѡ����������ó�ΪĬ��
					if (pickedUri != null)
					{
						RingtoneManager.setActualDefaultRingtoneUri(Activity01.this, RingtoneManager.TYPE_NOTIFICATION, pickedUri);
					}
				}
				catch (Exception e)
				{
				}
				break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	//����Ƿ����ָ�����ļ��� 
	//����������򴴽�
	private boolean bFolder(String strFolder)
	{
		boolean btmp = false;
		File f = new File(strFolder);
		if (!f.exists())
		{
			if (f.mkdirs())
			{
				btmp = true;
			}
			else
			{
				btmp = false;
			}
		}
		else
		{
			btmp = true;
		}
		return btmp;
	}
}
