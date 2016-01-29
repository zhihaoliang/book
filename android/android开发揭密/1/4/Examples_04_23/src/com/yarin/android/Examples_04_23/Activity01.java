package com.yarin.android.Examples_04_23;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity01 extends Activity
{
	Button				m_Button1, m_Button2, m_Button3, m_Button4;

	//����֪ͨ����Ϣ��������
	NotificationManager	m_NotificationManager;
	Intent				m_Intent;
	PendingIntent		m_PendingIntent;
	//����Notification����
	Notification		m_Notification;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//��ʼ��NotificationManager����
		m_NotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		//��ȡ4����ť����
		m_Button1 = (Button) findViewById(R.id.Button01);
		m_Button2 = (Button) findViewById(R.id.Button02);
		m_Button3 = (Button) findViewById(R.id.Button03);
		m_Button4 = (Button) findViewById(R.id.Button04);

		//���֪ͨʱת������
		m_Intent = new Intent(Activity01.this, Activity02.class);
		//��Ҫ�����õ��֪ͨʱ��ʾ���ݵ���
		m_PendingIntent = PendingIntent.getActivity(Activity01.this, 0, m_Intent, 0);
		//����Notification����
		m_Notification = new Notification();

		m_Button1.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				//����֪ͨ��״̬����ʾ��ͼ��
				m_Notification.icon = R.drawable.img1;
				//�����ǵ��֪ͨʱ��ʾ������
				m_Notification.tickerText = "Button1֪ͨ����...........";
				//֪ͨʱ����Ĭ�ϵ�����
				m_Notification.defaults = Notification.DEFAULT_SOUND;
				//����֪ͨ��ʾ�Ĳ���
				m_Notification.setLatestEventInfo(Activity01.this, "Button1", "Button1֪ͨ", m_PendingIntent);
				//�������Ϊִ�����֪ͨ
				m_NotificationManager.notify(0, m_Notification);
			}
		});

		m_Button2.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{

				m_Notification.icon = R.drawable.img2;
				m_Notification.tickerText = "Button2֪ͨ����...........";
				//֪ͨʱ��
				m_Notification.defaults = Notification.DEFAULT_VIBRATE;
				m_Notification.setLatestEventInfo(Activity01.this, "Button2", "Button2֪ͨ", m_PendingIntent);
				m_NotificationManager.notify(0, m_Notification);
			}
		});

		m_Button3.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				m_Notification.icon = R.drawable.img3;
				m_Notification.tickerText = "Button3֪ͨ����...........";
				//֪ͨʱ��Ļ����
				m_Notification.defaults = Notification.DEFAULT_LIGHTS;
				m_Notification.setLatestEventInfo(Activity01.this, "Button3", "Button3֪ͨ", m_PendingIntent);
				m_NotificationManager.notify(0, m_Notification);
			}
		});

		m_Button4.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				m_Notification.icon = R.drawable.img4;
				m_Notification.tickerText = "Button4֪ͨ����..........";
				//֪ͨʱ��������Ļ��������Ĭ�ϵ�����
				m_Notification.defaults = Notification.DEFAULT_ALL;
				m_Notification.setLatestEventInfo(Activity01.this, "Button4", "Button4֪ͨ", m_PendingIntent);
				m_NotificationManager.notify(0, m_Notification);
			}
		});
	}
}
