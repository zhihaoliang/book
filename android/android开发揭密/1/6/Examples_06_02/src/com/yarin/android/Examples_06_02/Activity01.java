package com.yarin.android.Examples_06_02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class Activity01 extends Activity
{
	
	private MIDIPlayer	mMIDIPlayer	= null;
	private boolean		mbMusic		= false;
	private TextView	mTextView	= null;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mTextView = (TextView) this.findViewById(R.id.TextView01);

		mMIDIPlayer = new MIDIPlayer(this);

		/* ��ȡ�ļ�����  */
		load();

		if (mbMusic)
		{
			mTextView.setText("��ǰ����״̬����");
			mbMusic = true;
			mMIDIPlayer.PlayMusic();
		}
		else
		{
			mTextView.setText("��ǰ����״̬����");
		}

	}

	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			case KeyEvent.KEYCODE_DPAD_UP:
				mTextView.setText("��ǰ����״̬����");
				mbMusic = true;
				mMIDIPlayer.PlayMusic();
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				mTextView.setText("��ǰ����״̬����");
				mbMusic = false;
				mMIDIPlayer.FreeMusic();
				break;
		}
		return true;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			/* �˳�Ӧ�ó���ʱ�������� */
			save();
			
			if ( mbMusic )
			{
				mMIDIPlayer.FreeMusic();
			}
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/* װ�ء���ȡ���� */
	void load()
	{
		/* ����Properties�Զ��� */
		Properties properties = new Properties();
		try
		{
			/* �����ļ� */
			FileInputStream stream = this.openFileInput("music.cfg");
			/* ��ȡ�ļ����� */
			properties.load(stream);
		}
		catch (FileNotFoundException e)
		{
			return;
		}
		catch (IOException e)
		{
			return;
		}
		/* ȡ������ */
		mbMusic = Boolean.valueOf(properties.get("bmusic").toString());
	}
	
	/* �������� */
	boolean save()
	{
		Properties properties = new Properties();
		
		/* �����ݴ����Properties */
		properties.put("bmusic", String.valueOf(mbMusic));
		try
		{
			FileOutputStream stream = this.openFileOutput("music.cfg", Context.MODE_WORLD_WRITEABLE);
			
			/* ������õ�����д���ļ��� */
			properties.store(stream, "");
		}
		catch (FileNotFoundException e)
		{
			return false;
		}
		catch (IOException e)
		{
			return false;
		}

		return true;
	}
}
