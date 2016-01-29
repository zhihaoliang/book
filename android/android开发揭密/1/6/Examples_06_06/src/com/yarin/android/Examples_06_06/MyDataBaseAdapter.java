package com.yarin.android.Examples_06_06;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseAdapter
{
	// ���ڴ�ӡlog
	private static final String	TAG				= "MyDataBaseAdapter";

	// ����һ�����ݵ�����
	public static final String	KEY_ID		= "_id";												

	// ����һ�����ݵ�����
	public static final String	KEY_NUM		= "num";												

	// ����һ�����ݵ�id
	public static final String	KEY_DATA		= "data";

	// ���ݿ�����Ϊdata
	private static final String	DB_NAME			= "Examples_06_06.db";
	
	// ���ݿ����
	private static final String	DB_TABLE		= "table1";
	
	// ���ݿ�汾
	private static final int	DB_VERSION		= 1;

	// ����Context����
	private Context				mContext		= null;
	
	//����һ����
	private static final String	DB_CREATE		= "CREATE TABLE " + DB_TABLE + " (" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NUM + " INTERGER,"+ KEY_DATA + " TEXT)";

	// ִ��open���������ݿ�ʱ�����淵�ص����ݿ����
	private SQLiteDatabase		mSQLiteDatabase	= null;

	// ��SQLiteOpenHelper�̳й���
	private DatabaseHelper		mDatabaseHelper	= null;
	
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		/* ���캯��-����һ�����ݿ� */
		DatabaseHelper(Context context)
		{
			//������getWritableDatabase() 
			//�� getReadableDatabase()����ʱ
			//�򴴽�һ�����ݿ�
			super(context, DB_NAME, null, DB_VERSION);
			
			
		}

		/* ����һ���� */
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			// ���ݿ�û�б�ʱ����һ��
			db.execSQL(DB_CREATE);
		}

		/* �������ݿ� */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			db.execSQL("DROP TABLE IF EXISTS notes");
			onCreate(db);
		}
	}
	
	/* ���캯��-ȡ��Context */
	public MyDataBaseAdapter(Context context)
	{
		mContext = context;
	}


	// �����ݿ⣬�������ݿ����
	public void open() throws SQLException
	{
		mDatabaseHelper = new DatabaseHelper(mContext);
		mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
	}


	// �ر����ݿ�
	public void close()
	{
		mDatabaseHelper.close();
	}

	/* ����һ������ */
	public long insertData(int num, String data)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NUM, num);
		initialValues.put(KEY_DATA, data);

		return mSQLiteDatabase.insert(DB_TABLE, KEY_ID, initialValues);
	}

	/* ɾ��һ������ */
	public boolean deleteData(long rowId)
	{
		return mSQLiteDatabase.delete(DB_TABLE, KEY_ID + "=" + rowId, null) > 0;
	}

	/* ͨ��Cursor��ѯ�������� */
	public Cursor fetchAllData()
	{
		return mSQLiteDatabase.query(DB_TABLE, new String[] { KEY_ID, KEY_NUM, KEY_DATA }, null, null, null, null, null);
	}

	/* ��ѯָ������ */
	public Cursor fetchData(long rowId) throws SQLException
	{

		Cursor mCursor =

		mSQLiteDatabase.query(true, DB_TABLE, new String[] { KEY_ID, KEY_NUM, KEY_DATA }, KEY_ID + "=" + rowId, null, null, null, null, null);

		if (mCursor != null)
		{
			mCursor.moveToFirst();
		}
		return mCursor;

	}

	/* ����һ������ */
	public boolean updateData(long rowId, int num, String data)
	{
		ContentValues args = new ContentValues();
		args.put(KEY_NUM, num);
		args.put(KEY_DATA, data);

		return mSQLiteDatabase.update(DB_TABLE, args, KEY_ID + "=" + rowId, null) > 0;
	}
	
}

