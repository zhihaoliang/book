package com.yarin.android.Examples_04_12;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity01 extends Activity
{
	//������ť
	Button m_Button1,m_Button2;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//ȡ��Button����
		m_Button1=(Button)findViewById(R.id.Button1);
		m_Button2=(Button)findViewById(R.id.Button2);
		
		//���ð�ť����ʾ������
		m_Button1.setText("��ʼ");
		m_Button2.setText("�˳�");
		
		//���ð�ť�Ŀ��
		m_Button1.setWidth(150);
		m_Button2.setWidth(100);
		
		//�������ֵ���ɫ
		m_Button1.setTextColor(Color.GREEN);
		m_Button2.setTextColor(Color.RED);
		
		//�������ֵĴ�С�ߴ�
		m_Button1.setTextSize(30);
		m_Button2.setTextSize(20);
		
		//m_Button1.setBackgroundColor(Color.BLUE);
		
		//���ð�ť���¼�����
		m_Button1.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v)
			{
				//����ť�¼�
				//����һ��Toast
				//m_Button1.getText()�õ���ť��ʾ������
				Toast toast = Toast.makeText(Activity01.this, "�����ˡ�"+m_Button1.getText()+"����ť��", Toast.LENGTH_LONG);
				//����toast��ʾ��λ��
				toast.setGravity(Gravity.TOP, 0, 150);
				//��ʾ��Toast
				toast.show();
			}
		});
		
		m_Button2.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v)
			{
				//����ť�¼�
				Activity01.this.finish();
			}
		});
	}
}
