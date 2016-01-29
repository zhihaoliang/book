package com.yarin.android.Examples_09_01;

import java.util.List;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Activity01 extends Activity implements SensorEventListener
{
	private boolean			mRegisteredSensor;
	//����SensorManager
	private SensorManager		mSensorManager;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mRegisteredSensor = false;
		//ȡ��SensorManagerʵ��
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	}
	@Override
	protected void onResume()
	{
		super.onResume();

		//����SensorManager��һ���б�(Listener)
		//��������ָ������ΪTYPE_ORIENTATION(�����Ӧ��)
		List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ORIENTATION);

		if (sensors.size() > 0)
		{
			Sensor sensor = sensors.get(0);
			//ע��SensorManager
			//this->����sensor��ʵ��
			//���մ��������͵��б�
			//���ܵ�Ƶ��
			mRegisteredSensor = mSensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
		}
	}
	@Override
	protected void onPause()
	{
		if (mRegisteredSensor)
		{
			//���������registerListener
			//����������ҪunregisterListener��ж��\ȡ��ע��
			mSensorManager.unregisterListener(this);
			mRegisteredSensor = false;
		}
		super.onPause();
	}
	//����׼�ȷ����ı�ʱ
	//sensor->������
	//accuracy->��׼��
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		
	}
	// ���������ڱ��ı�ʱ����
	@Override
	public void onSensorChanged(SensorEvent event)
	{
		// ���ܷ����Ӧ��������
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION)
		{
			//�������ǿ��Եõ����ݣ�Ȼ�������Ҫ������
			//����ģ���������޷�����Ч�������������ʱ����������
			float x = event.values[SensorManager.DATA_X];
			float y = event.values[SensorManager.DATA_Y];
			float z = event.values[SensorManager.DATA_Z];
		}
	}
}
