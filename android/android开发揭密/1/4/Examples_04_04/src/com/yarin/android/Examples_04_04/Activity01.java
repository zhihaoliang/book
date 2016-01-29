package com.yarin.android.Examples_04_04;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Activity01 extends Activity
{
	LinearLayout	m_LinearLayout;
	ListView		m_ListView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		/* ����LinearLayout���ֶ��� */
		m_LinearLayout = new LinearLayout(this);
		/* ���ò���LinearLayout������ */
		m_LinearLayout.setOrientation(LinearLayout.VERTICAL);
		m_LinearLayout.setBackgroundColor(android.graphics.Color.BLACK);

		/* ����ListView���� */
		m_ListView = new ListView(this);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		m_ListView.setBackgroundColor(Color.BLACK);

		/* ���m_ListView��m_LinearLayout���� */
		m_LinearLayout.addView(m_ListView, param);

		/* ������ʾm_LinearLayout���� */
		setContentView(m_LinearLayout);

		// ��ȡ���ݿ�Phones��Cursor
		Cursor cur = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		startManagingCursor(cur);

		// ListAdapter��ListView�ͺ�̨���ݵ�����
		ListAdapter adapter = new SimpleCursorAdapter(this,
			// ����List��ÿһ�е���ʾģ��
			// ��ʾÿһ�а�������������
			android.R.layout.simple_list_item_2,
			// ���ݿ��Cursor����
			cur,
			// �����ݿ��NAME��NUMBER������ȡ����
			new String[] { PhoneLookup.DISPLAY_NAME, PhoneLookup.NUMBER },
			// ��NAME��NUMBER��Ӧ��Views
			new int[] { android.R.id.text1, android.R.id.text2 });
		
		/* ��adapter��ӵ�m_ListView�� */
		m_ListView.setAdapter(adapter);

		/* Ϊm_ListView��ͼ���setOnItemSelectedListener���� */
		m_ListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				
				DisplayToast("��������"+Long.toString(arg0.getSelectedItemId())+"��");
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0)
			{
				//û��ѡ��
			}
		});

		/* Ϊm_ListView��ͼ���setOnItemClickListener���� */
		m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				//�ڶ�ѡ�е�����д���
				DisplayToast("ѡ���˵�"+Integer.toString(arg2+1)+"��");
			}

		});
		
	}
	/* ��ʾToast  */
	public void DisplayToast(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
}
