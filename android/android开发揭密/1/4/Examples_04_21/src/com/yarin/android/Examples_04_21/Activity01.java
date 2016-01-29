package com.yarin.android.Examples_04_21;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

public class Activity01 extends Activity
{
	//����ProgressBar����
	private ProgressBar m_ProgressBar;
	private ProgressBar m_ProgressBar2;
	private Button mButton01;
	protected static final int GUI_STOP_NOTIFIER = 0x108;
	protected static final int GUI_THREADING_NOTIFIER = 0x109;
	public int intCounter=0;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//���ô���ģʽ������Ϊ��Ҫ��ʾ�������ڱ�����
		requestWindowFeature(Window.FEATURE_PROGRESS);
		setProgressBarVisibility(true);
		setContentView(R.layout.main);
		
		//ȡ��ProgressBar
		m_ProgressBar = (ProgressBar) findViewById(R.id.ProgressBar01);
		m_ProgressBar2= (ProgressBar) findViewById(R.id.ProgressBar02);
		mButton01 = (Button)findViewById(R.id.Button01); 
		
		m_ProgressBar.setIndeterminate(false);
		m_ProgressBar2.setIndeterminate(false);
		
		//����ť����ʱ��ʼִ��,
	    mButton01.setOnClickListener(new Button.OnClickListener()
	    {
	      @Override
	      public void onClick(View v)
	      {
	        // TODO Auto-generated method stub
	    	  
	    	  //����ProgressBarΪ�ɼ�״̬
	    	  m_ProgressBar.setVisibility(View.VISIBLE);
	    	  m_ProgressBar2.setVisibility(View.VISIBLE);
	    	  //����ProgressBar�����ֵ
	    	  m_ProgressBar.setMax(100);
	    	  //����ProgressBar��ǰֵ
	    	  m_ProgressBar.setProgress(0);
	    	  m_ProgressBar2.setProgress(0);

	    	  //ͨ���߳����ı�ProgressBar��ֵ
				new Thread(new Runnable() {
					public void run()
					{
						for (int i = 0; i < 10; i++)
						{
							try
							{
								intCounter = (i + 1) * 20;
								Thread.sleep(1000);

								if (i == 4)
								{
									Message m = new Message();

									m.what = Activity01.GUI_STOP_NOTIFIER;
									Activity01.this.myMessageHandler.sendMessage(m);
									break;
								}
								else
								{
									Message m = new Message();
									m.what = Activity01.GUI_THREADING_NOTIFIER;
									Activity01.this.myMessageHandler.sendMessage(m);
								}
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		});
	}

	  Handler myMessageHandler = new Handler()
	  {
	    // @Override 
		  public void handleMessage(Message msg)
		  {
			  switch (msg.what)
			  {
			  //ProgressBar�Ѿ��ǶԴ�ֵ
			  case Activity01.GUI_STOP_NOTIFIER:
				  m_ProgressBar.setVisibility(View.GONE);
				  m_ProgressBar2.setVisibility(View.GONE);
				  Thread.currentThread().interrupt();
				  break;
			  case Activity01.GUI_THREADING_NOTIFIER:
				  if (!Thread.currentThread().isInterrupted())
				  {
					  // �ı�ProgressBar�ĵ�ǰֵ
					  m_ProgressBar.setProgress(intCounter);
					  m_ProgressBar2.setProgress(intCounter);
					  
					  // ���ñ�������ǰ����һ������������ֵ
					  setProgress(intCounter*100);
					  // ���ñ������к����һ������������ֵ
					  setSecondaryProgress(intCounter*100);//
				  }
				  break;
			  }
			  super.handleMessage(msg);
		 }
	  };
}
