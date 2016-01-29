package com.yarin.android.Examples_04_22;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class Activity01 extends Activity 
						implements SeekBar.OnSeekBarChangeListener
{
	//����SeekBar����
    SeekBar		mSeekBar;
	TextView	mProgressText;
	TextView	mTrackingText;


	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		//ȡ��SeekBar����
		mSeekBar = (SeekBar) findViewById(R.id.seek);
		mSeekBar.setOnSeekBarChangeListener(this);
		mProgressText = (TextView) findViewById(R.id.progress);
		mTrackingText = (TextView) findViewById(R.id.tracking);
	}

	//���϶���--��ֵ�ڸı�
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch)
	{
		mProgressText.setText("��ǰֵ��"+progress);
	}
	public void onStartTrackingTouch(SeekBar seekBar)
	{
		mTrackingText.setText("���ڵ���");
	}
	//ֹͣ�϶�
	public void onStopTrackingTouch(SeekBar seekBar)
	{
		mTrackingText.setText("ֹͣ����");
	}
}
