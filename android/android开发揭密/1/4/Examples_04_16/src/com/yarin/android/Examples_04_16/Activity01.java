package com.yarin.android.Examples_04_16;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Activity01 extends Activity
{
	TextView	m_TextView;
	//����4��ImageButton����
	ImageButton	m_ImageButton1;
	ImageButton	m_ImageButton2;
	ImageButton	m_ImageButton3;
	ImageButton	m_ImageButton4;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
	    m_TextView = (TextView) findViewById(R.id.TextView01);
	    //�ֱ�ȡ��4��ImageButton����
	    m_ImageButton1 = (ImageButton) findViewById(R.id.ImageButton01);
	    m_ImageButton2 = (ImageButton) findViewById(R.id.ImageButton02);
	    m_ImageButton3 = (ImageButton) findViewById(R.id.ImageButton03);
	    m_ImageButton4 = (ImageButton) findViewById(R.id.ImageButton04);
	    
	    //�ֱ�������ʹ�õ�ͼ��
	    //m_ImageButton1����xml���������õģ��������ʱ��������
	    m_ImageButton2.setImageDrawable(getResources().getDrawable(R.drawable.button2));
	    m_ImageButton3.setImageDrawable(getResources().getDrawable(R.drawable.button3));
	    m_ImageButton4.setImageDrawable(getResources().getDrawable(android.R.drawable.sym_call_incoming));
	    
	    //���·ֱ�Ϊÿ����ť�����¼�����setOnClickListener
	    m_ImageButton1.setOnClickListener(new Button.OnClickListener()
	    {
	      public void onClick(View v)
	      {
	    	  //�Ի���
	    	  Dialog dialog = new AlertDialog.Builder(Activity01.this)
				.setTitle("��ʾ")
				.setMessage("����ImageButton1")
				.setPositiveButton("ȷ��",
				new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface dialog, int whichButton)
					{
						
					}
				}).create();//������ť
	    	  
	    	  dialog.show();
	      }
	    });
	    m_ImageButton2.setOnClickListener(new Button.OnClickListener()
	    {
	      public void onClick(View v)
	      {
	    	  Dialog dialog = new AlertDialog.Builder(Activity01.this)
				.setTitle("��ʾ")
				.setMessage("����ImageButton2����Ҫʹ��ImageButton3��ͼ��")
				.setPositiveButton("ȷ��",
				new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface dialog, int whichButton)
					{
						m_ImageButton2.setImageDrawable(getResources().getDrawable(R.drawable.button3));
					}
				}).create();//������ť
	    	  
	    	  dialog.show();
	      }
	    });
	    m_ImageButton3.setOnClickListener(new Button.OnClickListener()
	    {
	      public void onClick(View v)
	      {
	    	  Dialog dialog = new AlertDialog.Builder(Activity01.this)
				.setTitle("��ʾ")
				.setMessage("����ImageButton3����Ҫʹ��ϵͳ��绰ͼ��")
				.setPositiveButton("ȷ��",
				new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface dialog, int whichButton)
					{
						m_ImageButton3.setImageDrawable(getResources().getDrawable(android.R.drawable.sym_action_call));
					}
				}).create();//������ť
	    	  
	    	  dialog.show();
	      }
	    });
	    m_ImageButton4.setOnClickListener(new Button.OnClickListener()
	    {
	      public void onClick(View v)
	      {
	    	  Dialog dialog = new AlertDialog.Builder(Activity01.this)
				.setTitle("��ʾ")
				.setMessage("����ʹ�õ�ϵͳͼ�꣡")
				.setPositiveButton("ȷ��",
				new DialogInterface.OnClickListener() 
				{
					public void onClick(DialogInterface dialog, int whichButton)
					{
						
					}
				}).create();//������ť
	    	  
	    	  dialog.show();
	      }
	    });
	}
}
