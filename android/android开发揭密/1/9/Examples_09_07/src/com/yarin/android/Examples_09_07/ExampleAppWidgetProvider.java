package com.yarin.android.Examples_09_07;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.RemoteViews;

public class ExampleAppWidgetProvider extends AppWidgetProvider
{
	//���ڸ���ʱ����
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
	{
		final int N = appWidgetIds.length;
		for (int i = 0; i < N; i++)
		{
			int appWidgetId = appWidgetIds[i];
			String titlePrefix = Activity01.loadTitlePref(context, appWidgetId);
			updateAppWidget(context, appWidgetManager, appWidgetId, titlePrefix);
		}
	}

	//�����沿��ɾ��ʱ����
	public void onDeleted(Context context, int[] appWidgetIds)
	{
		//ɾ��appWidget
		final int N = appWidgetIds.length;
		for (int i = 0; i < N; i++)
		{
			Activity01.deleteTitlePref(context, appWidgetIds[i]);
		}
	}

	//��AppWidgetProvider�ṩ�ĵ�һ������������ʱ����
	public void onEnabled(Context context)
	{
		PackageManager pm = context.getPackageManager();
		pm.setComponentEnabledSetting(new ComponentName("com.yarin.android.Examples_09_07", ".ExampleBroadcastReceiver"),
			PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
	}

	//��AppWidgetProvider�ṩ�����һ��������ɾ��ʱ����
	public void onDisabled(Context context)
	{
		PackageManager pm = context.getPackageManager();
		pm.setComponentEnabledSetting(new ComponentName("com.yarin.android.Examples_09_07", ".ExampleBroadcastReceiver"),
			PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
	}

	//����
	static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId, String titlePrefix)
	{
		//����RemoteViews�����������沿�����и���
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_provider);
		//�����ı����ݣ�ָ�����ֵ����
		views.setTextViewText(R.id.appwidget_text, titlePrefix);
		//��RemoteViews�ĸ��´���AppWidget���и���
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}
}
