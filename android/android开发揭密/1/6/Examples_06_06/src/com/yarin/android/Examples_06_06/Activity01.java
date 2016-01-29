package com.yarin.android.Examples_06_06;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Activity01 extends Activity
{
	private static int			miCount			= 0;
	
	/* ���Բ��� */
	LinearLayout				m_LinearLayout	= null;
	/* �б���ͼ-��ʾ���ݿ��е����� */
	ListView					m_ListView		= null;
	
	MyDataBaseAdapter m_MyDataBaseAdapter;
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
		
		/* ����MyDataBaseAdapter���� */
		m_MyDataBaseAdapter = new MyDataBaseAdapter(this);
		
		/* ȡ�����ݿ���� */
		m_MyDataBaseAdapter.open();
		
		UpdataAdapter();
	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			case KeyEvent.KEYCODE_DPAD_LEFT:
				AddData();
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				DeleteData();
				break;
			case KeyEvent.KEYCODE_1:
				UpData();
				break;
		}
		return true;
	}
	
	/* ����һ������ */
	public void UpData()
	{	
		m_MyDataBaseAdapter.updateData(miCount - 1, miCount, "�޸ĺ������" + miCount);

		UpdataAdapter();
	}

	/* ��������һ������ */
	public void AddData()
	{
		m_MyDataBaseAdapter.insertData(miCount, "�������ݿ�����" + miCount);
		miCount++;
		UpdataAdapter();
	}

	/* �ӱ���ɾ��ָ����һ������ */
	public void DeleteData()
	{

		/* ɾ������ */
		m_MyDataBaseAdapter.deleteData(miCount);
		miCount--;
		if (miCount < 0)
		{
			miCount = 0;
		}
		UpdataAdapter();
	}
	
	/* �����¼����� */
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			/* �˳�ʱ����Ҫ���ǹر� */
			m_MyDataBaseAdapter.close();
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/* ������ͼ��ʾ */
	public void UpdataAdapter()
	{
		// ��ȡ���ݿ�Phones��Cursor
		Cursor cur = m_MyDataBaseAdapter.fetchAllData();

		miCount = cur.getCount();
		if (cur != null && cur.getCount() >= 0)
		{
			// ListAdapter��ListView�ͺ�̨���ݵ�����
			ListAdapter adapter = new SimpleCursorAdapter(this,
			// ����List��ÿһ�е���ʾģ��
				// ��ʾÿһ�а�������������
				android.R.layout.simple_list_item_2,
				// ���ݿ��Cursor����
				cur,
				// �����ݿ��TABLE_NUM��TABLE_DATA������ȡ����
				new String[] {MyDataBaseAdapter.KEY_NUM, MyDataBaseAdapter.KEY_DATA },
				// ��NAME��NUMBER��Ӧ��Views
				new int[] { android.R.id.text1, android.R.id.text2 });

			/* ��adapter��ӵ�m_ListView�� */
			m_ListView.setAdapter(adapter);
		}
	}
}
