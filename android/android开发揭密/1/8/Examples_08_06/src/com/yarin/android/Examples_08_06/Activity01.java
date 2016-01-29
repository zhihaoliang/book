package com.yarin.android.Examples_08_06;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity01 extends Activity
{
	private final String DEBUG_TAG	= "Activity01";
	private Button		mButton;
	private EditText	mEditText;
	private WebView		mWebView;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mButton = (Button) findViewById(R.id.Button01);
		mEditText = (EditText) findViewById(R.id.EditText01);
		mWebView = (WebView) findViewById(R.id.WebView01);
		//����֧��JavaScript�ű�
		WebSettings webSettings = mWebView.getSettings();  
		webSettings.setJavaScriptEnabled(true);
		//���ÿ��Է����ļ�
		webSettings.setAllowFileAccess(true);
		//����֧������
		webSettings.setBuiltInZoomControls(true);
		//����WebViewClient
		mWebView.setWebViewClient(new WebViewClient()
		{   
		    public boolean shouldOverrideUrlLoading(WebView view, String url) 
		    {   
		        view.loadUrl(url);   
		        return true;   
		    }  
			@Override
			public void onPageFinished(WebView view, String url) 
			{
				super.onPageFinished(view, url);
			}
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) 
			{
				super.onPageStarted(view, url, favicon);
			}
		});
		//����WebChromeClient
		mWebView.setWebChromeClient(new WebChromeClient(){
			@Override
			//����javascript�е�alert
			public boolean onJsAlert(WebView view, String url, String message,
					final JsResult result) 
			{
				//����һ��Builder����ʾ��ҳ�еĶԻ���
				Builder builder = new Builder(Activity01.this);
				builder.setTitle("��ʾ�Ի���");
				builder.setMessage(message);
				builder.setPositiveButton(android.R.string.ok,
						new AlertDialog.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								//���ȷ����ť֮��,����ִ����ҳ�еĲ���
								result.confirm();
							}
						});
				builder.setCancelable(false);
				builder.create();
				builder.show();
				return true;
			};
			@Override
			//����javascript�е�confirm
			public boolean onJsConfirm(WebView view, String url, String message,
					final JsResult result) 
			{
				Builder builder = new Builder(Activity01.this);
				builder.setTitle("��ѡ��ĶԻ���");
				builder.setMessage(message);
				builder.setPositiveButton(android.R.string.ok,
						new AlertDialog.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								result.confirm();
							}
						});
				builder.setNegativeButton(android.R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								result.cancel();
							}
						});
				builder.setCancelable(false);
				builder.create();
				builder.show();
				return true;
			};
			@Override
			//����javascript�е�prompt
			//messageΪ��ҳ�жԻ������ʾ����
			//defaultValue��û������ʱ��Ĭ����ʾ������
			public boolean onJsPrompt(WebView view, String url, String message,
					String defaultValue, final JsPromptResult result) {
				//�Զ���һ��������ĶԻ�����TextView��EditText����
				final LayoutInflater factory = LayoutInflater.from(Activity01.this);
				final View dialogview = factory.inflate(R.layout.prom_dialog, null);
				//����TextView��Ӧ��ҳ�е���ʾ��Ϣ
				((TextView) dialogview.findViewById(R.id.TextView_PROM)).setText(message);
				//����EditText��Ӧ��ҳ�е������
				((EditText) dialogview.findViewById(R.id.EditText_PROM)).setText(defaultValue);
				
				Builder builder = new Builder(Activity01.this);
				builder.setTitle("������ĶԻ���");
				builder.setView(dialogview);
				builder.setPositiveButton(android.R.string.ok,
						new AlertDialog.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								//���ȷ��֮��ȡ�������ֵ��������ҳ����
								String value = ((EditText) dialogview.findViewById(R.id.EditText_PROM)).getText().toString();
								result.confirm(value);
							}
						});
				builder.setNegativeButton(android.R.string.cancel,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								result.cancel();
							}
						});
				builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
							public void onCancel(DialogInterface dialog) {
								result.cancel();
							}
						});
				builder.show();
				return true;
			};
			@Override
			//������ҳ���صĽ�����
			public void onProgressChanged(WebView view, int newProgress) 
			{
				Activity01.this.getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress * 100);
				super.onProgressChanged(view, newProgress);
			}

			@Override
			//����Ӧ�ó���ı���title
			public void onReceivedTitle(WebView view, String title) 
			{
				Activity01.this.setTitle(title);
				super.onReceivedTitle(view, title);
			}
		});
		//���Ӱ�ť�¼�����
		mButton.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				try 
				{
					//ȡ�ñ༭�����������������
			    	String url = mEditText.getText().toString();
			    	//�ж�����������ǲ�����ַ
			    	if ( URLUtil.isNetworkUrl(url) )
					{  
			    		//װ����ַ
			    		mWebView.loadUrl(url);
					}
			    	else
			    	{
			    		mEditText.setText("������ַ��������������");
					}
				}
				catch (Exception e) 
				{
					Log.e(DEBUG_TAG, e.toString());
				}
			}
		});
	}
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK) && 
			 mWebView.canGoBack())
		{
			//����ǰһ��ҳ��
			mWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}  
}
