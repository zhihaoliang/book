package com.yarin.android.Examples_06_04;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.apache.http.util.ByteArrayBuffer;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity01 extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);

		TextView tv = new TextView(this);
		
		String myString = null;
		
		try
		{
			/* ��������Ҫ���ʵĵ�ַurl */
			URL uri = new URL("http://192.168.1.110:8080/android.txt");
			
			/* �����url���� */
			URLConnection ucon = uri.openConnection();
			
			/* �������������ȡ��InputStream */
			InputStream is = ucon.getInputStream();
			
			BufferedInputStream bis = new BufferedInputStream(is);
			ByteArrayBuffer baf = new ByteArrayBuffer(100);
			int current = 0;
			/* һֱ�����ļ����� */
			while ((current = bis.read()) != -1)
			{
				baf.append((byte) current);
			}
			myString = new String(baf.toByteArray());
		}
		catch (Exception e)
		{

			myString = e.getMessage();
		}
		/* ����Ϣ���õ�TextView */
		tv.setText(myString);
		
		/* ��TextView��ʾ����Ļ�� */
		this.setContentView(tv);

	}
}
