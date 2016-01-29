package com.yarin.android.Examples_09_05;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;

public class Activity01 extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//Ҫ��ӵĿ�ݷ�ʽ��Intent
		Intent addShortcut; 
		//�ж��Ƿ�Ҫ��ӿ�ݷ�ʽ
		if (getIntent().getAction().equals(Intent.ACTION_CREATE_SHORTCUT))
		{
			addShortcut = new Intent(); 
			//���ÿ�ݷ�ʽ������
			addShortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "�����ʼ�");
			//������ݷ�ʽ��ר�ŵ�ͼ��
			Parcelable icon = Intent.ShortcutIconResource.fromContext(this,R.drawable.mail_edit);  
			//��ӿ�ݷ�ʽͼ��
			addShortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,icon);
			//������ݷ�ʽִ�е�Intent
			Intent mailto = new  Intent(Intent.ACTION_SENDTO, Uri.parse( "mailto:xxx@xxx.com" )); 
			//��ӿ�ݷ�ʽIntent
			addShortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, mailto);  
			//����
			setResult(RESULT_OK,addShortcut);  
		}
		else
		{
			//ȡ��
			setResult(RESULT_CANCELED);  
		}
		//�ر�
		finish();  
	}
}
