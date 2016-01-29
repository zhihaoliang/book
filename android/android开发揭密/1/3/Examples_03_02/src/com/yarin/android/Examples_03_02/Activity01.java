package com.yarin.android.Examples_03_02;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.widget.TextView;

public class Activity01 extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		TextView tv = new TextView(this);
		String string = "";		
		super.onCreate(savedInstanceState);	
		//�õ�ContentResolver����
        ContentResolver cr = getContentResolver();  
        //ȡ�õ绰���п�ʼһ��Ĺ��
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        //�����ƶ�һ�¹��
        while(cursor.moveToNext()) 
        { 
        	//ȡ����ϵ������
        	int nameFieldColumnIndex = cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME);     
        	String contact = cursor.getString(nameFieldColumnIndex); 
        	//ȡ�õ绰����
        	int numberFieldColumnIndex = cursor.getColumnIndex(PhoneLookup.NUMBER);    
        	String number = cursor.getString(numberFieldColumnIndex);
        	
        	string += (contact+":"+number+"\n");
        }
        cursor.close();
		//����TextView��ʾ������
		tv.setText(string);
		//��ʾ����Ļ
		setContentView(tv);
	}
}
