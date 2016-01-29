package com.yarin.android.Examples_04_05;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity01 extends Activity {
	
	private TextView textview;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textview = (TextView)this.findViewById(R.id.tv1);
		
		String string = "Toastʾ�������յ�����ʱ�����ǻ���ʾ����ӭʹ�ã�";
		textview.setTextSize(30);
		textview.setText(string);
		
		Button button = (Button) findViewById(R.id.button1);
		/* ����button���¼���Ϣ */
		button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				DisplayToast("����������������ʾ");
			}
		});
    }
    
	/* ��ʾToast  */
	public void DisplayToast(String str)
	{
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}
}