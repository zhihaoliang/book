package aom.yarin.android.Examples_06_01;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class Activity01 extends Activity
{

	private MIDIPlayer	mMIDIPlayer	= null;
	private boolean		mbMusic		= false;
	private TextView	mTextView	= null;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mTextView = (TextView) this.findViewById(R.id.TextView01);

		mMIDIPlayer = new MIDIPlayer(this);

		/* װ������ */
		// ȡ�û��preferences����.
		SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);

		// ȡ��ֵ.
		mbMusic = settings.getBoolean("bmusic", false);

		if (mbMusic)
		{
			mTextView.setText("��ǰ����״̬����");
			mbMusic = true;
			mMIDIPlayer.PlayMusic();
		}
		else
		{
			mTextView.setText("��ǰ����״̬����");
		}

	}


	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			case KeyEvent.KEYCODE_DPAD_UP:
				mTextView.setText("��ǰ����״̬����");
				mbMusic = true;
				mMIDIPlayer.PlayMusic();
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				mTextView.setText("��ǰ����״̬����");
				mbMusic = false;
				mMIDIPlayer.FreeMusic();
				break;
		}
		return true;
	}


	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			/* �����������Ƴ�Ӧ�ó���ʱ�������� */
			// ȡ�û��preferences����.
			SharedPreferences uiState = getPreferences(0);

			// ȡ�ñ༭����
			SharedPreferences.Editor editor = uiState.edit();

			// ���ֵ
			editor.putBoolean("bmusic", mbMusic);
			
			// �ύ����
			editor.commit();
			if ( mbMusic )
			{
				mMIDIPlayer.FreeMusic();
			}
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
