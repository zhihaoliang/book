package com.yarin.android.Examples_06_05;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

	/* ���ݿ���� */
	private SQLiteDatabase		mSQLiteDatabase	= null;

	/* ���ݿ��� */
	private final static String	DATABASE_NAME	= "Examples_06_05.db";
	/* ���� */
	private final static String	TABLE_NAME		= "table1";

	/* ���е��ֶ� */
	private final static String	TABLE_ID		= "_id";
	private final static String	TABLE_NUM		= "num";
	private final static String	TABLE_DATA		= "data";

	/* �������sql��� */
	private final static String	CREATE_TABLE	= "CREATE TABLE " + TABLE_NAME + " (" + TABLE_ID + " INTEGER PRIMARY KEY," + TABLE_NUM + " INTERGER,"+ TABLE_DATA + " TEXT)";

	/* ���Բ��� */
	LinearLayout				m_LinearLayout	= null;
	/* �б���ͼ-��ʾ���ݿ��е����� */
	ListView					m_ListView		= null;


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

		// ���Ѿ����ڵ����ݿ�
		mSQLiteDatabase = this.openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

		// ��ȡ���ݿ�Phones��Cursor
		try
		{
			/* �����ݿ�mSQLiteDatabase�д���һ���� */
			mSQLiteDatabase.execSQL(CREATE_TABLE);
		}
		catch (Exception e)
		{
			UpdataAdapter();
		}
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
			case KeyEvent.KEYCODE_2:
				DeleteTable();
				break;
			case KeyEvent.KEYCODE_3:
				DeleteDataBase();
				break;
		}
		return true;
	}


	/* ɾ�����ݿ� */
	public void DeleteDataBase()
	{
		this.deleteDatabase(DATABASE_NAME);
		this.finish();
	}


	/* ɾ��һ���� */
	public void DeleteTable()
	{
		mSQLiteDatabase.execSQL("DROP TABLE " + TABLE_NAME);
		this.finish();
	}


	/* ����һ������ */
	public void UpData()
	{
		ContentValues cv = new ContentValues();
		cv.put(TABLE_NUM, miCount);
		cv.put(TABLE_DATA, "�޸ĺ������" + miCount);

		/* �������� */
		mSQLiteDatabase.update(TABLE_NAME, cv, TABLE_NUM + "=" + Integer.toString(miCount - 1), null);

		UpdataAdapter();
	}


	/* ��������һ������ */
	public void AddData()
	{
		ContentValues cv = new ContentValues();
		cv.put(TABLE_NUM, miCount);
		cv.put(TABLE_DATA, "�������ݿ�����" + miCount);
		/* �������� */
		mSQLiteDatabase.insert(TABLE_NAME, null, cv);
		miCount++;
		UpdataAdapter();
	}


	/* �ӱ���ɾ��ָ����һ������ */
	public void DeleteData()
	{

		/* ɾ������ */
		mSQLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME + " WHERE _id=" + Integer.toString(miCount));

		miCount--;
		if (miCount < 0)
		{
			miCount = 0;
		}
		UpdataAdapter();
	}


	/* ������ͼ��ʾ */
	public void UpdataAdapter()
	{
		// ��ȡ���ݿ�Phones��Cursor
		Cursor cur = mSQLiteDatabase.query(TABLE_NAME, new String[] { TABLE_ID, TABLE_NUM, TABLE_DATA }, null, null, null, null, null);

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
				new String[] { TABLE_NUM, TABLE_DATA },
				// ��NAME��NUMBER��Ӧ��Views
				new int[] { android.R.id.text1, android.R.id.text2 });

			/* ��adapter��ӵ�m_ListView�� */
			m_ListView.setAdapter(adapter);
		}
	}


	/* �����¼����� */
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			/* �˳�ʱ����Ҫ���ǹر� */
			mSQLiteDatabase.close();
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
