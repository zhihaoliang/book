package com.yarin.android.Examples_04_17;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Activity01 extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		//���Gallery����
		Gallery g = (Gallery) findViewById(R.id.Gallery01);

		//���ImageAdapter��Gallery����
		g.setAdapter(new ImageAdapter(this));

		//����Gallery�ı���
		g.setBackgroundResource(R.drawable.bg0);
		
		//����Gallery���¼�����
		g.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
		        Toast.makeText(Activity01.this,"��ѡ����"+(position+1)+" ��ͼƬ", 
		            Toast.LENGTH_SHORT).show();
			}
		});
	}
}
