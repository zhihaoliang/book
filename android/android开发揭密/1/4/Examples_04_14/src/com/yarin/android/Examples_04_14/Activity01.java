package com.yarin.android.Examples_04_14;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class Activity01 extends Activity 
{
	ProgressDialog m_Dialog;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Dialog dialog = new AlertDialog.Builder(Activity01.this)
			.setTitle("��½��ʾ")//���ñ���
			.setMessage("������Ҫ��¼��")//��������
			.setPositiveButton("ȷ��",//����ȷ����ť
			new DialogInterface.OnClickListener() 
			{
				public void onClick(DialogInterface dialog, int whichButton)
				{
					//�����ȷ����ת���½��
					
					LayoutInflater factory = LayoutInflater.from(Activity01.this);
					//�õ��Զ���Ի���
	                final View DialogView = factory.inflate(R.layout.dialog, null);
	                //�����Ի���
	                AlertDialog dlg = new AlertDialog.Builder(Activity01.this)
	                .setTitle("��¼��")
	                .setView(DialogView)//�����Զ���Ի������ʽ
	                .setPositiveButton("ȷ��", //����"ȷ��"��ť
	                new DialogInterface.OnClickListener() //�����¼�����
	                {
	                    public void onClick(DialogInterface dialog, int whichButton) 
	                    {
	                    	//������ɺ󣬵����ȷ������ʼ��½
	                    	m_Dialog = ProgressDialog.show
	                                   (
	                                	 Activity01.this,
	                                     "��ȴ�...",
	                                     "����Ϊ���¼...", 
	                                     true
	                                   );
	                        
	                        new Thread()
	                        { 
	                          public void run()
	                          { 
	                            try
	                            { 
	                              sleep(3000);
	                            }
	                            catch (Exception e)
	                            {
	                              e.printStackTrace();
	                            }
	                            finally
	                            {
	                            	//��¼������ȡ��m_Dialog�Ի���
	                            	m_Dialog.dismiss();
	                            }
	                          }
	                        }.start(); 
	                    }
	                })
	                .setNegativeButton("ȡ��", //���á�ȡ������ť
	                new DialogInterface.OnClickListener() 
	                {
	                    public void onClick(DialogInterface dialog, int whichButton)
	                    {
	                    	//���"ȡ��"��ť֮���˳�����
	                    	Activity01.this.finish();
	                    }
	                })
	                .create();//����
	                dlg.show();//��ʾ
				}
			}).setNeutralButton("�˳�", 
			new DialogInterface.OnClickListener() 
			{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				//���"�˳�"��ť֮���Ƴ�����
				Activity01.this.finish();
			}
		}).create();//������ť

		// ��ʾ�Ի���
		dialog.show();
	}
}
