package com.yarin.android.Examples_03_01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Activity03 extends Activity
{
//	private TextView Long;
//	private EditText Edit;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		/* ������ʾmain3.xml���� */
		setContentView(R.layout.main3);
		/* findViewById(R.id.button3)ȡ�ò���main.xml�е�button3 */
		Button button = (Button) findViewById(R.id.button3);
		
//		Long = (TextView) findViewById(R.id.Long);
//	     Edit = (EditText) findViewById(R.id.Edit);
		
		/* ����button���¼���Ϣ */
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				/* �½�һ��Intent���� */
				Intent intent = new Intent();
				/* ָ��intentҪ�������� */
				intent.setClass(Activity03.this, Activity01.class);
				/* ����һ���µ�Activity */
				startActivity(intent);
				/* �رյ�ǰ��Activity */
				Activity03.this.finish();
			//Long.setText("ccccccccccc");
				
			}
		});
	}
}

