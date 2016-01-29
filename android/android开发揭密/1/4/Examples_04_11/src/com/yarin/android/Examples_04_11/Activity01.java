package com.yarin.android.Examples_04_11;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class Activity01 extends Activity
{
	TextView	m_TextView;
	//����DatePicker����
	DatePicker	m_DatePicker;
	//����TimePicker����
	TimePicker	m_TimePicker;
	Button 		m_dpButton;
	Button 		m_tpButton;
	//java�е�������
	Calendar 	c;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		c=Calendar.getInstance();
		
		m_TextView= (TextView) findViewById(R.id.TextView01);
		m_dpButton = (Button)findViewById(R.id.button01);
		m_tpButton = (Button)findViewById(R.id.button02);
		
		//��ȡDatePicker����
		m_DatePicker = (DatePicker) findViewById(R.id.DatePicker01);
		//��������ʼ��Ϊ��ǰϵͳʱ�䣬���������¼�����
		m_DatePicker.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
			{
				//�����ڸ���ʱ�������ﴦ��
				//c.set(year, monthOfYear, dayOfMonth);
			}
		});

		//��ȡTimePicker����
		m_TimePicker = (TimePicker) findViewById(R.id.TimePicker01);
		//����Ϊ24Сʱ����ʾ
		m_TimePicker.setIs24HourView(true);

		//����ʱ��ı�
		m_TimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute)
			{
				//ʱ��ı�ʱ����
				//c.set(year, month, day, hourOfDay, minute, second);
			}
		});
		
		
		m_dpButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v)
			{
				new DatePickerDialog(Activity01.this,
					new DatePickerDialog.OnDateSetListener()
					{
						public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
						{
							//��������
						}
					},c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		
		m_tpButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v)
			{
				new TimePickerDialog(Activity01.this,
					new TimePickerDialog.OnTimeSetListener()
					{
						public void onTimeSet(TimePicker view, int hourOfDay,int minute)
						{
							//����ʱ��
						}
					},c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
			}
		});
	}
}
