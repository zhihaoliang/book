package com.yarin.android.Examples_04_24;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity01 extends Activity
{
	private Button mButton01,mButton02;
	
	int m_count = 0;
	//�����������Ի���
	ProgressDialog m_pDialog;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//�õ���ť����
		mButton01 = (Button)findViewById(R.id.Button01);
		mButton02 = (Button)findViewById(R.id.Button02);
		
		//����mButton01���¼�����
	    mButton01.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
				//����ProgressDialog����
				m_pDialog = new ProgressDialog(Activity01.this);

				// ���ý�������񣬷��ΪԲ�Σ���ת��
				m_pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

				// ����ProgressDialog ����
				m_pDialog.setTitle("��ʾ");
				
				// ����ProgressDialog ��ʾ��Ϣ
				m_pDialog.setMessage("����һ��Բ�ν������Ի���");

				// ����ProgressDialog ����ͼ��
				m_pDialog.setIcon(R.drawable.img1);

				// ����ProgressDialog �Ľ������Ƿ���ȷ
				m_pDialog.setIndeterminate(false);
				
				// ����ProgressDialog �Ƿ���԰��˻ذ���ȡ��
				m_pDialog.setCancelable(true);
				
				// ����ProgressDialog ��һ��Button
				m_pDialog.setButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int i)
					{
						//�����ȷ����ť��ȡ���Ի���
						dialog.cancel();
					}
				});

				// ��ProgressDialog��ʾ
				m_pDialog.show();
			}
		});
	    
	  //����mButton02���¼�����
	    mButton02.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				
				m_count = 0;
				
				// ����ProgressDialog����
				m_pDialog = new ProgressDialog(Activity01.this);
				
				// ���ý�������񣬷��Ϊ����
				m_pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				
				// ����ProgressDialog ����
				m_pDialog.setTitle("��ʾ");
				
				// ����ProgressDialog ��ʾ��Ϣ
				m_pDialog.setMessage("����һ�����ζԻ��������");
				
				// ����ProgressDialog ����ͼ��
				m_pDialog.setIcon(R.drawable.img2);
				
				// ����ProgressDialog ����������
				m_pDialog.setProgress(100);
				
				// ����ProgressDialog �Ľ������Ƿ���ȷ
				m_pDialog.setIndeterminate(false);
				
				// ����ProgressDialog �Ƿ���԰��˻ذ���ȡ��
				m_pDialog.setCancelable(true);
				
				// ��ProgressDialog��ʾ
				m_pDialog.show();
				
				new Thread() 
				{
					public void run()
					{
						try
						{
							while (m_count <= 100)
							{ 
								// ���߳������ƽ��ȡ�
								m_pDialog.setProgress(m_count++);
								Thread.sleep(100); 
							}
							m_pDialog.cancel();
						}
						catch (InterruptedException e)
						{
							m_pDialog.cancel();
						}
					}
				}.start();
				
			}
		});
	}
}
