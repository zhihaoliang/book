package com.yarin.android.Examples_07_05;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Activity01 extends ListActivity
{
	/* ��ť */
	private Button			StartButton;
	private Button			StopButton;
	/* ¼�Ƶ���Ƶ�ļ� */
	private File			mRecAudioFile;
	private File			mRecAudioPath;
	/* MediaRecorder���� */
	private MediaRecorder	mMediaRecorder;
	/* ¼���ļ��б� */
	private List<String>	mMusicList	= new ArrayList<String>();
	/* ��ʱ�ļ���ǰ׺ */
	private String			strTempFile	= "recaudio_";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		/* ȡ�ð�ť */
		StartButton = (Button) findViewById(R.id.StartButton);
		StopButton = (Button) findViewById(R.id.StopButton);
		
		/* ����Ƿ����SD�� */
		if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
		{
			/* �õ�SD����·�� */
			mRecAudioPath = Environment.getExternalStorageDirectory();
			/* ��������¼���ļ���List�� */
			musicList();
		}
		else
		{
			Toast.makeText(Activity01.this, "û��SD��", Toast.LENGTH_LONG).show();
		}
		/* ��ʼ��ť�¼����� */
		StartButton.setOnClickListener(new Button.OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				try
				{
					/* ����¼���ļ� */
					mRecAudioFile = File.createTempFile(strTempFile, ".amr", mRecAudioPath);
					/* ʵ����MediaRecorder���� */
					mMediaRecorder = new MediaRecorder();
					/* ������˷� */
					mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					/* ��������ļ��ĸ�ʽ */
					mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
					/* ������Ƶ�ļ��ı��� */
					mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
					/* ��������ļ���·�� */
					mMediaRecorder.setOutputFile(mRecAudioFile.getAbsolutePath());
					/* ׼�� */
					mMediaRecorder.prepare();
					/* ��ʼ */
					mMediaRecorder.start();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});
		/* ֹͣ��ť�¼����� */
		StopButton.setOnClickListener(new Button.OnClickListener() 
		{
			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				if (mRecAudioFile != null)
				{
					/* ֹͣ¼�� */
					mMediaRecorder.stop();
					/* ��¼���ļ���ӵ�List�� */
					mMusicList.add(mRecAudioFile.getName());
					ArrayAdapter<String> musicList = new ArrayAdapter<String>(Activity01.this, R.layout.list, mMusicList);
					setListAdapter(musicList);
					/* �ͷ�MediaRecorder */
					mMediaRecorder.release();
					mMediaRecorder = null;
				}
			}
		});
	}
	/* ����¼���ļ� */
	private void playMusic(File file)
	{
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);
		/* �����ļ����� */
		intent.setDataAndType(Uri.fromFile(file), "audio");
		startActivity(intent);
	}
	@Override
	/* �����ǵ���б�ʱ�����ű���������� */
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		/* �õ���������ļ� */
		File playfile = new File(mRecAudioPath.getAbsolutePath() + File.separator + mMusicList.get(position));
		/* ���� */
		playMusic(playfile);
	}
	/* �����б� */
	public void musicList()
	{
		// ȡ��ָ��λ�õ��ļ�������ʾ�������б�
		File home = mRecAudioPath;
		if (home.listFiles(new MusicFilter()).length > 0)
		{
			for (File file : home.listFiles(new MusicFilter()))
			{
				mMusicList.add(file.getName());
			}
			ArrayAdapter<String> musicList = new ArrayAdapter<String>(Activity01.this, R.layout.list, mMusicList);
			setListAdapter(musicList);
		}
	}
}
/* �����ļ����� */
class MusicFilter implements FilenameFilter
{
	public boolean accept(File dir, String name)
	{
		return (name.endsWith(".amr"));
	}
}
