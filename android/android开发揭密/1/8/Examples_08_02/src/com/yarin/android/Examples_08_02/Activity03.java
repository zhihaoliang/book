package com.yarin.android.Examples_08_02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity03 extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.http);
		TextView mTextView = (TextView) this.findViewById(R.id.TextView_HTTP);
		// http��ַ
		String httpUrl = "http://192.168.1.110:8080/httpget.jsp";
		//HttpPost���Ӷ���
		HttpPost httpRequest = new HttpPost(httpUrl);
		//ʹ��NameValuePair������Ҫ���ݵ�Post����
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//���Ҫ���ݵĲ���
		params.add(new BasicNameValuePair("par", "HttpClient_android_Post"));
		try
		{
			//�����ַ���
			HttpEntity httpentity = new UrlEncodedFormEntity(params, "gb2312");
			//����httpRequest
			httpRequest.setEntity(httpentity);
			//ȡ��Ĭ�ϵ�HttpClient
			HttpClient httpclient = new DefaultHttpClient();
			//ȡ��HttpResponse
			HttpResponse httpResponse = httpclient.execute(httpRequest);
			//HttpStatus.SC_OK��ʾ���ӳɹ�
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
				intent.setClass(Activity03.this, Activity01.class);
				/* ����һ���µ�Activity */
				startActivity(intent);
				/* �رյ�ǰ��Activity */
				Activity03.this.finish();
			}
		});
	}
}