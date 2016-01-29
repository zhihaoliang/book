package com.yarin.android.Examples_04_29;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class Activity01 extends TabActivity
{
	//����TabHost����
	TabHost mTabHost;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//ȡ��TabHost����
		mTabHost = getTabHost();
		
//		LayoutInflater.from(this).inflate(R.layout.main, mTabHost.getTabContentView(), true);
	    
		/* ΪTabHost��ӱ�ǩ */
		//�½�һ��newTabSpec(newTabSpec)
		//�������ǩ��ͼ��(setIndicator)
		//��������(setContent)
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test1") 			
	    		.setIndicator("TAB 1",getResources().getDrawable(R.drawable.img1))
	    		.setContent(R.id.textview1));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test2")
	    		.setIndicator("TAB 2",getResources().getDrawable(R.drawable.img2))
	    		.setContent(R.id.textview2));
	    mTabHost.addTab(mTabHost.newTabSpec("tab_test3")
	    		.setIndicator("TAB 3",getResources().getDrawable(R.drawable.img3))
	    		.setContent(R.id.textview3));
	    
	    //����TabHost�ı�����ɫ
	    mTabHost.setBackgroundColor(Color.argb(150, 22, 70, 150));
	    //����TabHost�ı���ͼƬ��Դ
//	    mTabHost.setBackgroundResource(R.drawable.bg0);
	    
	    //���õ�ǰ��ʾ��һ����ǩ
	    mTabHost.setCurrentTab(0);
	    
	    //��ǩ�л��¼�����setOnTabChangedListener 
	    mTabHost.setOnTabChangedListener(new OnTabChangeListener()
	    {
	    	// TODO Auto-generated method stub
            @Override
            public void onTabChanged(String tabId) 
            {
  	    	  	Dialog dialog = new AlertDialog.Builder(Activity01.this)
  	    	  			.setTitle("��ʾ")
  	    	  			.setMessage("��ǰѡ�У�"+tabId+"��ǩ")
  	    	  			.setPositiveButton("ȷ��",
  	    	  			new DialogInterface.OnClickListener() 
  	    	  			{
  	    	  				public void onClick(DialogInterface dialog, int whichButton)
  	    	  				{
  	    	  					dialog.cancel();
  	    	  				}
  	    	  			}).create();//������ť
	    	  
  	    	  	dialog.show();
            }            
        });
	}
}
