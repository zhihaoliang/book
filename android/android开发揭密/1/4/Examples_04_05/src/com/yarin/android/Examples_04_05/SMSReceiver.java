package com.yarin.android.Examples_04_05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver
{
	/*���յ�����ʱ���ͻᴥ���˷���*/
	public void onReceive(Context context, Intent intent)
	{
		Bundle bundle = intent.getExtras();
		Object messages[] = (Object[]) bundle.get("pdus");
		SmsMessage smsMessage[] = new SmsMessage[messages.length];
		for (int n = 0; n < messages.length; n++)
		{
			smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
		}
		//����һ��Toast
		Toast toast = Toast.makeText(context, "��������: " + smsMessage[0].getMessageBody(), Toast.LENGTH_LONG);
		//����toast��ʾ��λ��
		//toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 200);
		//��ʾ��Toast
		toast.show();
	}
}
