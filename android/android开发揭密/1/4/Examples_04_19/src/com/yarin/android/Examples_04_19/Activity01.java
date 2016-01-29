package com.yarin.android.Examples_04_19;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
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

		//ȡ��GridView����
		GridView gridview = (GridView) findViewById(R.id.gridview);
		//���Ԫ�ظ�gridview
		gridview.setAdapter(new ImageAdapter(this));

		// ����Gallery�ı���
		gridview.setBackgroundResource(R.drawable.bg0);

		//�¼�����
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				Toast.makeText(Activity01.this, "��ѡ����" + (position + 1) + " ��ͼƬ", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
