package com.yarin.android.Examples_09_06;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.LiveFolders;

public class Activity01 extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		// �ж��Ƿ񴴽�ʵʱ�ļ���
		if (getIntent().getAction().equals(LiveFolders.ACTION_CREATE_LIVE_FOLDER))
		{
			Intent intent = new Intent();
			// �������ݵ�ַ
			intent.setData(Uri.parse("content://contacts/live_folders/people"));
			// ���õ����ǵ���֮����¼������ﵥ��һ����ϵ�˺󣬺���
			intent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_BASE_INTENT, new Intent(Intent.ACTION_CALL, ContactsContract.Contacts.CONTENT_URI));
			// ����ʵʱ�ļ��е�����
			intent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_NAME, "�绰��");
			// ����ʵʩ�ļ��е�ͼ��
			intent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_ICON, Intent.ShortcutIconResource.fromContext(this, R.drawable.contacts));
			// ������ʾģʽΪ�б�
			intent.putExtra(LiveFolders.EXTRA_LIVE_FOLDER_DISPLAY_MODE, LiveFolders.DISPLAY_MODE_LIST);
			// ���
			setResult(RESULT_OK, intent);
		}
		else
		{
			setResult(RESULT_CANCELED);
		}
		finish();
	}
}
