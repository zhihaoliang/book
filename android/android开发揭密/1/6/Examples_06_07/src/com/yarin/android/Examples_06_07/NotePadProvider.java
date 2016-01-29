package com.yarin.android.Examples_06_07;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.yarin.android.Examples_06_07.NotePad.Notes;

public class NotePadProvider extends ContentProvider
{
	private static final String				TAG					= "NotePadProvider";
	// ���ݿ���
	private static final String				DATABASE_NAME		= "note_pad.db";
	private static final int				DATABASE_VERSION	= 2;
	// ����
	private static final String				NOTES_TABLE_NAME	= "notes";
	private static HashMap<String, String>	sNotesProjectionMap;
	private static final int				NOTES				= 1;
	private static final int				NOTE_ID				= 2;
	private static final UriMatcher			sUriMatcher;
	private DatabaseHelper	mOpenHelper;
	//������SQL���
	private static final String				CREATE_TABLE="CREATE TABLE " 
														+ NOTES_TABLE_NAME 
														+ " (" + Notes._ID 
														+ " INTEGER PRIMARY KEY," 
														+ Notes.TITLE 
														+ " TEXT," 
														+ Notes.NOTE 
														+ " TEXT,"
														+ Notes.CREATEDDATE 
														+ " INTEGER," 
														+ Notes.MODIFIEDDATE 
														+ " INTEGER" + ");";
	
	static
	{
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(NotePad.AUTHORITY, "notes", NOTES);
		sUriMatcher.addURI(NotePad.AUTHORITY, "notes/#", NOTE_ID);

		sNotesProjectionMap = new HashMap<String, String>();
		sNotesProjectionMap.put(Notes._ID, Notes._ID);
		sNotesProjectionMap.put(Notes.TITLE, Notes.TITLE);
		sNotesProjectionMap.put(Notes.NOTE, Notes.NOTE);
		sNotesProjectionMap.put(Notes.CREATEDDATE, Notes.CREATEDDATE);
		sNotesProjectionMap.put(Notes.MODIFIEDDATE, Notes.MODIFIEDDATE);
	}
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		//���캯��-�������ݿ�
		DatabaseHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		//������
		@Override
		public void onCreate(SQLiteDatabase db)
		{
			db.execSQL(CREATE_TABLE);
		}
		//�������ݿ�
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			db.execSQL("DROP TABLE IF EXISTS notes");
			onCreate(db);
		}
	}
	@Override
	public boolean onCreate()
	{
		mOpenHelper = new DatabaseHelper(getContext());
		return true;
	}
	@Override
	//��ѯ����
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
	{
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		switch (sUriMatcher.match(uri))
		{
			case NOTES:
				qb.setTables(NOTES_TABLE_NAME);
				qb.setProjectionMap(sNotesProjectionMap);
				break;

			case NOTE_ID:
				qb.setTables(NOTES_TABLE_NAME);
				qb.setProjectionMap(sNotesProjectionMap);
				qb.appendWhere(Notes._ID + "=" + uri.getPathSegments().get(1));
				break;

			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
		}
		String orderBy;
		if (TextUtils.isEmpty(sortOrder))
		{
			orderBy = NotePad.Notes.DEFAULT_SORT_ORDER;
		}
		else
		{
			orderBy = sortOrder;
		}
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}
	@Override
	// ������Զ������ͣ�����ʵ�ָ÷���
	public String getType(Uri uri)
	{
		switch (sUriMatcher.match(uri))
		{
			case NOTES:
				return Notes.CONTENT_TYPE;

			case NOTE_ID:
				return Notes.CONTENT_ITEM_TYPE;

			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
		}
	}
	@Override
	//�������ݿ�
	public Uri insert(Uri uri, ContentValues initialValues)
	{
		if (sUriMatcher.match(uri) != NOTES)
		{
			throw new IllegalArgumentException("Unknown URI " + uri);
		}
		ContentValues values;
		if (initialValues != null)
		{
			values = new ContentValues(initialValues);
		}
		else
		{
			values = new ContentValues();
		}
		Long now = Long.valueOf(System.currentTimeMillis());

		if (values.containsKey(NotePad.Notes.CREATEDDATE) == false)
		{
			values.put(NotePad.Notes.CREATEDDATE, now);
		}
		if (values.containsKey(NotePad.Notes.MODIFIEDDATE) == false)
		{
			values.put(NotePad.Notes.MODIFIEDDATE, now);
		}
		if (values.containsKey(NotePad.Notes.TITLE) == false)
		{
			Resources r = Resources.getSystem();
			values.put(NotePad.Notes.TITLE, r.getString(android.R.string.untitled));
		}
		if (values.containsKey(NotePad.Notes.NOTE) == false)
		{
			values.put(NotePad.Notes.NOTE, "");
		}
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long rowId = db.insert(NOTES_TABLE_NAME, Notes.NOTE, values);
		if (rowId > 0)
		{
			Uri noteUri = ContentUris.withAppendedId(NotePad.Notes.CONTENT_URI, rowId);
			getContext().getContentResolver().notifyChange(noteUri, null);
			return noteUri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}
	@Override
	//ɾ������
	public int delete(Uri uri, String where, String[] whereArgs)
	{
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count;
		switch (sUriMatcher.match(uri))
		{
			case NOTES:
				count = db.delete(NOTES_TABLE_NAME, where, whereArgs);
				break;

			case NOTE_ID:
				String noteId = uri.getPathSegments().get(1);
				count = db.delete(NOTES_TABLE_NAME, Notes._ID + "=" + noteId + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
				break;

			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
	@Override
	//��������
	public int update(Uri uri, ContentValues values, String where, String[] whereArgs)
	{
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		int count;
		switch (sUriMatcher.match(uri))
		{
			case NOTES:
				count = db.update(NOTES_TABLE_NAME, values, where, whereArgs);
				break;

			case NOTE_ID:
				String noteId = uri.getPathSegments().get(1);
				count = db.update(NOTES_TABLE_NAME, values, Notes._ID + "=" + noteId + (!TextUtils.isEmpty(where) ? " AND (" + where + ')' : ""), whereArgs);
				break;

			default:
				throw new IllegalArgumentException("Unknown URI " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}
}
