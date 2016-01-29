package com.yarin.android.Examples_08_02;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity02 extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http);
		TextView mTextView = (TextView) this.findViewById(R.id.TextView_HTTP);
		// http��ַ
		String httpUrl = "http://192.168.1.110:8080/httpget.jsp?par=HttpClient_android_Get";
		//HttpGet���Ӷ���
		HttpGet httpRequest = new HttpGet(httpUrl);
		try
		{
			//ȡ��HttpClient����
			HttpClient httpclient = new DefaultHttpClient();
			//����HttpClient��ȡ��HttpResponse
			HttpResponse httpResponse = httpclient.execute(httpRequest);
			//����ɹ�
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
			{
				//ȡ�÷��ص��ַ���
				String strResult = EntityUtils.toString(httpResponse.getEntity());
				mTextView.setText(strResult);
			}
			else
			{
				mTextView.setText("�������!");
			}
		}
		catch (ClientProtocolException e)
		{
			mTextView.setText(e.getMessage().toString());
		}
		catch (IOException e)
		{
			mTextView.setText(e.getMessage().toString());
		}
		catch (Exception e)
		{
			mTextView.setText(e.getMessage().toString());
		}  
	
		//���ð����¼�����
		Button button_Back = (Button) findViewById(R.id.Button_Back);
		/* ����button���¼���Ϣ */
		button_Back.setOnClickListener(new Button.OnClickListener() 
		{
			public void onClick(View v)
			{
				/* �½�һ��Intent���� */
				Intent intent = new Intent();
				/* ָ��intentҪ�������� */
				intent.setClass(Activity02.this, Activity01.class);
				/* ����һ���µ�Activity */
				startActivity(intent);
				/* �رյ�ǰ��Activity */
				Activity02.this.finish();
			}
		});
	}
}