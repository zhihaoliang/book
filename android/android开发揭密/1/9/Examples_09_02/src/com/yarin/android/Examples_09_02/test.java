//package com.example.android.apis.app;
//
//import com.example.android.apis.R;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
//import android.os.Bundle;
//import android.speech.RecognizerIntent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.ListView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class VoiceRecognition extends Activity implements OnClickListener {
//    
//    private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
//    
//    private ListView mList;
//
//    /**
//     * Called with the activity is first created.
//     */
//    @Override
//    public void onCreate(Bundle savedInstanceState) 
//    {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.voice_recognition);
//
//        Button speakButton = (Button) findViewById(R.id.btn_speak);
//        
//        mList = (ListView) findViewById(R.id.list);
//
//        // Check to see if a recognition activity is present
//        PackageManager pm = getPackageManager();
//        List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
//        if (activities.size() != 0)
//		{
//			speakButton.setOnClickListener(this);
//		}
//		else
//		{
//			speakButton.setEnabled(false);
//			speakButton.setText("Recognizer not present");
//		}
//    }
//
//
//    public void onClick(View v)
//	{
//		if (v.getId() == R.id.btn_speak)
//		{
//			startVoiceRecognitionActivity();
//		}
//	}
//
//
//    private void startVoiceRecognitionActivity()
//	{
//    	//ͨ��Intent��������ʶ���ģʽ
//		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//		//����ģʽ��������ʽ������ʶ��
//		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//		//��ʾ������ʼ
//		intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech recognition demo");
//		//��ʼִ�����ǵ�Intent������ʶ��
//		startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
//	}
//
//
//    //����������ʱ�Ļص�����onActivityResult
//    @Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data)
//	{
//		if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK)
//		{
//			// ȡ���������ַ�
//			ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//			mList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, matches));
//		}
//
//		super.onActivityResult(requestCode, resultCode, data);
//	}
//}