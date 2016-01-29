package com.yarin.android.Examples_09_02;

import java.util.ArrayList;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Activity01 extends Activity
{
    private static final int VOICE_RECOGNITION_REQUEST_CODE = 4321;
    
    private ListView mList;
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		mList = (ListView) findViewById(R.id.ListView01);
		
		Button button = (Button) findViewById(R.id.Button01); 
		button.setOnClickListener(new View.OnClickListener() 
		{ 	            
			@Override          
			public void onClick(View v)
			{
				try
				{
					//ͨ��Intent��������ʶ���ģʽ,��������
					Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
					//����ģʽ��������ʽ������ʶ��
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
					//��ʾ������ʼ
					intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"��ʼ����");
					//��ʼִ�����ǵ�Intent������ʶ��
					startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
				}
				catch (ActivityNotFoundException e)
				{
					//�Ҳ��������豸װ��
					Toast.makeText(Activity01.this,"ActivityNotFoundException", Toast.LENGTH_LONG).show(); 
				}
			}      
		}); 
	}
	
	//����������ʱ�Ļص�����onActivityResult
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// �ж��Ƿ�������ִ�е�����ʶ��
		if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK)
		{
			// ȡ���������ַ�
			ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			//������ͼ����
			//mList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,results));
			String resultsString = ""; 
			for (int i = 0; i < results.size(); i++)
			{
				resultsString += results.get(i);
			} 
			Toast.makeText(this, resultsString, Toast.LENGTH_LONG).show(); 
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

}
