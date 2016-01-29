package com.yarin.android.Examples_08_05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Activity01 extends Activity
{
	private final String		DEBUG_TAG	= "Activity01";
	//������IP���˿�
	private static final String SERVERIP = "192.168.1.110";
	private static final int SERVERPORT = 54321;
	private Thread mThread = null;
	private Socket				mSocket		= null;
	private Button				mButton_In	= null;
	private Button				mButton_Send= null;
	private EditText			mEditText01 	= null;
	private EditText			mEditText02 	= null;
	private BufferedReader		mBufferedReader	= null;
	private PrintWriter mPrintWriter = null;
	private  String mStrMSG = "";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mButton_In = (Button)findViewById(R.id.Button_In);
		mButton_Send = (Button)findViewById(R.id.Button_Send);
		mEditText01=(EditText)findViewById(R.id.EditText01);
		mEditText02=(EditText)findViewById(R.id.EditText02);
		
		//��½
		mButton_In.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				try 
				{
					//���ӷ�����
					mSocket = new Socket(SERVERIP, SERVERPORT);	
					//ȡ�����롢�����
					mBufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
					mPrintWriter=new PrintWriter(mSocket.getOutputStream(), true);   
				}
				catch (Exception e) 
				{
					// TODO: handle exception
					Log.e(DEBUG_TAG, e.toString());
				}
			}
		});
		//������Ϣ
		mButton_Send.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				try 
				{
					//ȡ�ñ༭�����������������
			    	String str = mEditText02.getText().toString() + "\n";
			    	//���͸�������
			    	mPrintWriter.print(str);
			    	mPrintWriter.flush();
				}
				catch (Exception e) 
				{
					// TODO: handle exception
					Log.e(DEBUG_TAG, e.toString());
				}
			}
		});
		
		mThread = new Thread(mRunnable);
		mThread.start();
	}
	
	//�߳�:������������������Ϣ
	private Runnable	mRunnable	= new Runnable() 
	{
		public void run()
		{
			while (true)
			{
				try
				{
					if ( (mStrMSG = mBufferedReader.readLine()) != null )
					{
						//��Ϣ����
						mStrMSG+="\n";
						mHandler.sendMessage(mHandler.obtainMessage());
					}
					// ������Ϣ
				}
				catch (Exception e)
				{
					Log.e(DEBUG_TAG, e.toString());
				}
			}
		}
	};
	
	Handler		mHandler	= new Handler() 
	{										
		  public void handleMessage(Message msg)										
		  {											
			  super.handleMessage(msg);		
			  // ˢ��											
			  try											
			  {	
				  //�������¼��ӽ���
				  mEditText01.append(mStrMSG);			
			  }											
			  catch (Exception e)											
			  {																					
				  Log.e(DEBUG_TAG, e.toString());											
			  }										
		  }									
	 };
} 

