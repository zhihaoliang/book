package com.yarin.android.Examples_07_04;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Activity01 extends Activity implements 
						OnBufferingUpdateListener, 
						OnCompletionListener, 
						MediaPlayer.OnPreparedListener, 
						SurfaceHolder.Callback
{
	private static final String	TAG	= "Activity01";
	private int					mVideoWidth;
	private int					mVideoHeight;
	private MediaPlayer			mMediaPlayer;
	private SurfaceView			mPreview;
	private SurfaceHolder		holder;
	private String				path;
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.main);
		mPreview = (SurfaceView) findViewById(R.id.surface);
		/* �õ�SurfaceHolder���� */
		holder = mPreview.getHolder();
		/* ���ûص����� */
		holder.addCallback(this);
		/* ���÷�� */
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	private void playVideo()
	{
		try
		{
			path = "/sdcard/test.mp4";
			/* ����MediaPlayer���� */
			mMediaPlayer = new MediaPlayer();
			/* ����ý���ļ�·�� */
			mMediaPlayer.setDataSource(path);
			/* ����ͨ��SurfaceView����ʾ���� */
			mMediaPlayer.setDisplay(holder);
			/* ׼�� */
			mMediaPlayer.prepare();
			/* �����¼����� */
			mMediaPlayer.setOnBufferingUpdateListener(this);
			mMediaPlayer.setOnCompletionListener(this);
			mMediaPlayer.setOnPreparedListener(this);
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		}
		catch (Exception e)
		{
			Log.e(TAG, "error: " + e.getMessage(), e);
		}
	}
	public void onBufferingUpdate(MediaPlayer arg0, int percent)
	{
		Log.d(TAG, "onBufferingUpdate percent:" + percent);
	}
	public void onCompletion(MediaPlayer arg0)
	{
		Log.d(TAG, "onCompletion called");
	}
	public void onPrepared(MediaPlayer mediaplayer)
	{
		Log.d(TAG, "onPrepared called");
		mVideoWidth = mMediaPlayer.getVideoWidth();
		mVideoHeight = mMediaPlayer.getVideoHeight();
		if (mVideoWidth != 0 && mVideoHeight != 0)
		{
			/* ������Ƶ�Ŀ�Ⱥ͸߶� */
			holder.setFixedSize(mVideoWidth, mVideoHeight);
			/* ��ʼ���� */
			mMediaPlayer.start();
		}

	}
	/* ����ʱ�������¼� */
	public void surfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
	{
		Log.d(TAG, "surfaceChanged called");

	}
	/* ���� */
	public void surfaceDestroyed(SurfaceHolder surfaceholder)
	{
		Log.d(TAG, "surfaceDestroyed called");
	}
	/* ��SurfaceHolder����ʱ���� */
	public void surfaceCreated(SurfaceHolder holder)
	{
		Log.d(TAG, "surfaceCreated called");
		playVideo();
	}
	/* ���� */
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		if (mMediaPlayer != null)
		{
			mMediaPlayer.release();
			mMediaPlayer = null;
		}

	}
}
