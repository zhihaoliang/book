package com.yarin.android.Examples_04_20;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class Activity01 extends Activity
{
    private LinearLayout mLayout;   
    private ScrollView 	mScrollView;   
    private final Handler mHandler = new Handler();  
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//����һ�����Բ���
        mLayout = (LinearLayout) findViewById(R.id.layout);   
        //����һ��ScrollView����
        mScrollView = (ScrollView) findViewById(R.id.ScrollView01);   
  
        Button button = (Button) findViewById(R.id.Button01); 
        
        button.setOnClickListener(mClickListener);   
        //�ı�Ĭ�Ͻ����л�   
        button.setOnKeyListener(mAddButtonKeyListener);
	}
	
	//Button�¼�����
	//�������ťʱ������һ��TextView��Button
	private Button.OnClickListener mClickListener = new Button.OnClickListener() 
	{   
        private int mIndex = 1;   
        @Override  
        public void onClick(View arg0) 
        {   
            // TODO Auto-generated method stub        
            TextView textView = new TextView(Activity01.this);   
            textView.setText("Text View " + mIndex);   
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(   
                    LinearLayout.LayoutParams.FILL_PARENT,   
                    LinearLayout.LayoutParams.WRAP_CONTENT   
            );   
            //����һ��TextView�����Բ�����
            mLayout.addView(textView, p);   
  
            Button buttonView = new Button(Activity01.this);   
            buttonView.setText("Button " + mIndex++);
            
          //����һ��Button�����Բ�����
            mLayout.addView(buttonView, p);   
            //�ı�Ĭ�Ͻ����л�  
            buttonView.setOnKeyListener(mNewButtonKeyListener); 
            //Ͷ��һ����Ϣ���й���   
            mHandler.post(mScrollToBottom);   
        }          
    };   
    
    
    private Runnable mScrollToBottom = new Runnable() 
    {   
        @Override  
        public void run()
        {   
            // TODO Auto-generated method stub   
            
            int off = mLayout.getMeasuredHeight() - mScrollView.getHeight();   
            if (off > 0) 
            {   
                mScrollView.scrollTo(0, off);   
            }                          
        }   
    };   
    
    //�¼�����
    private View.OnKeyListener mNewButtonKeyListener = new View.OnKeyListener() 
    {   
        public boolean onKey(View v, int keyCode, KeyEvent event) 
        {   
            if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN &&   
                    event.getAction() == KeyEvent.ACTION_DOWN &&   
                    v == mLayout.getChildAt(mLayout.getChildCount() - 1)) 
            {   
                findViewById(R.id.Button01).requestFocus();   
                return true;   
            }   
            return false;   
        }   
    };   
    
  //�¼�����
    private View.OnKeyListener mAddButtonKeyListener = new Button.OnKeyListener() 
    {   
        @Override  
        public boolean onKey(View v, int keyCode, KeyEvent event) 
        {   
            // TODO Auto-generated method stub                  
            View viewToFoucus = null;   
            if (event.getAction() == KeyEvent.ACTION_DOWN) 
            {   
            	int iCount = mLayout.getChildCount(); 
                switch (keyCode) 
                {   
                case KeyEvent.KEYCODE_DPAD_UP:   
                    if ( iCount > 0) 
                    {   
                        viewToFoucus = mLayout.getChildAt(iCount - 1);   
                    }   
                    break;   
                case KeyEvent.KEYCODE_DPAD_DOWN:   
                    if (iCount < mLayout.getWeightSum()) 
                    {   
                        viewToFoucus = mLayout.getChildAt(iCount + 1);   
                    }   
                    break;   
                default:   
                    break;   
                }   
            }     
            if (viewToFoucus != null) 
            {   
                viewToFoucus.requestFocus();   
                return true;   
            } 
            else 
            {   
                return false;   
            }   
        }   
    }; 
}
