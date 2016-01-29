package com.yarin.android.Examples_04_13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Activity01 extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	/*����menu*/
	public boolean onCreateOptionsMenu(Menu menu)
	{
		//�����õ���MenuInflater�࣬ʹ�ø����inflate��������ȡxml�ļ����ҽ����˵�
		MenuInflater inflater = getMenuInflater();
		//����menu����Ϊres/menu/menu.xml
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	/*����˵��¼�*/
	public boolean onOptionsItemSelected(MenuItem item)
	{
		//�õ���ǰѡ�е�MenuItem��ID,
		int item_id = item.getItemId();

		switch (item_id)
		{
			case R.id.about:
				/* �½�һ��Intent���� */
				Intent intent = new Intent();
				/* ָ��intentҪ�������� */
				intent.setClass(Activity01.this, Activity02.class);
				/* ����һ���µ�Activity */
				startActivity(intent);
				/* �رյ�ǰ��Activity */
				Activity01.this.finish();
				break;
			case R.id.exit:
				Activity01.this.finish();
				break;
		}
		return true;
	}
}
