package com.yarin.android.Examples_03_01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Activity02 extends Activity
{
	//private EditText Edit;
	private TextView Long2;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		/* ������ʾmain2.xml���� */
		setContentView(R.layout.main2);
		/* findViewById(R.id.button2)ȡ�ò���main.xml�е�button2 */
		Button button = (Button) findViewById(R.id.button2);
	//	Edit = (EditText) findViewById(R.id.Edit);
		Long2 = (TextView) findViewById(R.id.Long2);
		/* ����button���¼���Ϣ */
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				/* �½�һ��Intent���� */
				Intent intent = new Intent();
				/* ָ��intentҪ�������� */
				intent.setClass(Activity02.this, Activity03.class);
				/* ����һ���µ�Activity */
				startActivity(intent);
				/* �رյ�ǰ��Activity */
				Activity02.this.finish();
				
			//	Long2.setText("aaaaaa");
			}
		});
	}
}

