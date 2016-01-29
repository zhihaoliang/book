package com.yarin.android.Examples_07_06;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

public class Activity01 extends Activity
{
	private Preview	mPreview;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Create our Preview view and set it as the content of our activity.
		mPreview = new Preview(this);
		setContentView(mPreview);
	}


	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
			case KeyEvent.KEYCODE_DPAD_CENTER:
				mPreview.takePicture();
				break;
		}
		return true;
	}
}

/* Preview-��ʾPreview */
class Preview extends SurfaceView implements SurfaceHolder.Callback 
{
    SurfaceHolder mHolder;
    Camera mCamera;
    Bitmap CameraBitmap;
    
    Preview(Context context) 
    {
        super(context);
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) 
    {
    	/* ����Camera */
        mCamera = Camera.open();
        try 
        {
           mCamera.setPreviewDisplay(holder);
        } 
        catch (IOException exception) 
        {
        	/* �ͷ�mCamera */
            mCamera.release();
            mCamera = null;
            // TODO: add more exception handling logic here
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) 
    {
    	/* ֹͣԤ�� */
        mCamera.stopPreview();
        mCamera = null;
    }    
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) 
    {
    	/* ����Camera.Parameters������Ĳ����������� */
        Camera.Parameters parameters = mCamera.getParameters();
        /* �������յ�ͼƬ��ʽ */
        parameters.setPictureFormat(PixelFormat.JPEG);
        /* ����Preview�ĳߴ� */
        parameters.setPreviewSize(320, 480);
        /* ����ͼ��ֱ��� */
        //parameters.setPictureSize(320, 480);
        /* �����������parameters */
        mCamera.setParameters(parameters);
        /* ��ʼԤ�� */
        mCamera.startPreview();
    }
    /* ����Ƭ */
    public void takePicture() 
    {
      if (mCamera != null) 
      {
    	  mCamera.takePicture(null, null, jpegCallback);
      }
    }
    /* ���պ����ͼƬ */
    private PictureCallback jpegCallback = new PictureCallback() 
    {
      public void onPictureTaken(byte[] _data, Camera _camera)
      {
        // TODO Handle JPEG image data
    	CameraBitmap = BitmapFactory.decodeByteArray(_data, 0, _data.length); 
        File myCaptureFile = new File("/sdcard/camera1.jpg");
        try
        {
          BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
          CameraBitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
          bos.flush();
          bos.close();
          /* ���ĵ���ͼƬ���Ƴ��� */
          Canvas canvas= mHolder.lockCanvas();
          canvas.drawBitmap(CameraBitmap, 0, 0, null);
          mHolder.unlockCanvasAndPost(canvas);
          
        }
        catch (Exception e)
        {
        	e.getMessage();
        }
      }
    };
}