package com.yarin.android.Examples_04_13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Activity02 extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		/* ������ʾmain2.xml���� */
		setContentView(R.layout.main2);
	}

	/*����menu*/
	public boolean onCreateOptionsMenu(Menu menu)
	{
		//Ϊmenu�������
		menu.add(0, 0, 0, R.string.ok);
		menu.add(0, 1, 0, R.string.back);
		return true;
	}

	/*����menu���¼�*/
	public boolean onOptionsItemSelected(MenuItem item)
	{
		//�õ���ǰѡ�е�MenuItem��ID,
		int item_id = item.getItemId();

		switch (item_id)
		{
			case 0:
			case 1:
				/* �½�һ��Intent���� */
				Intent intent = new Intent();
				/* ָ��intentҪ�������� */
				intent.setClass(Activity02.this, Activity01.class);
				/* ����һ���µ�Activity */
				startActivity(intent);
				/* �رյ�ǰ��Activity */
				Activity02.this.finish();
				break;
		}
		return true;
	}
}

