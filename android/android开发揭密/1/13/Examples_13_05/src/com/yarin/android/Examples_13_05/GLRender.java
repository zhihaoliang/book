package com.yarin.android.Examples_13_05;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLUtils;
import android.opengl.GLSurfaceView.Renderer;
import android.view.KeyEvent;

public class GLRender implements Renderer
{
	int one = 0x10000;
	float step = 0.4f;
	boolean key;
	boolean light = true;
	
	GL10 mGl10;
	
	float xrot, yrot;	//x,y����ת
	float xspeed, yspeed;//��ת���ٶ�
	float z = -5.0f;//������Ļ�ľ���
	
	int fogMode[]= { GL10.GL_EXP, GL10.GL_EXP2, GL10.GL_LINEAR };		// ������ģʽ
	int fogfilter= 0;					// ʹ����һ������
	float fogColor[]= {0.5f, 0.5f, 0.5f, 1.0f};		// �����ɫ��Ϊ��ɫ

	
	//���廷����(r,g,b,a)
	FloatBuffer lightAmbient = FloatBuffer.wrap(new float[]{0.5f,0.5f,0.5f,1.0f}); 
	//���������
	FloatBuffer lightDiffuse = FloatBuffer.wrap(new float[]{1.0f,1.0f,1.0f,1.0f});
	//��Դ��λ��
	FloatBuffer lightPosition = FloatBuffer.wrap(new float[]{0.0f,0.0f,2.0f,1.0f}); 
	
	//���˵�����
	int filter = 1;
	//����Ч��
	int [] texture;
	
	IntBuffer vertices = IntBuffer.wrap(new int[]{
			-one,-one,one,
			one,-one,one,
			one,one,one,
			-one,one,one,
			
			-one,-one,-one,
			-one,one,-one,
			one,one,-one,
			one,-one,-one,
			
			-one,one,-one,
			-one,one,one,
			one,one,one,
			one,one,-one,
			
			-one,-one,-one,
			one,-one,-one,
			one,-one,one,
			-one,-one,one,
			
			one,-one,-one,
			one,one,-one,
			one,one,one,
			one,-one,one,
			
			-one,-one,-one,
			-one,-one,one,
			-one,one,one,
			-one,one,-one,
			
	});
	
	IntBuffer normals = IntBuffer.wrap(new int[]{
			0,0,one,
			0,0,one,
			0,0,one,
			0,0,one,
			
			0,0,one,
			0,0,one,
			0,0,one,
			0,0,one,
			
			0,one,0,
			0,one,0,
			0,one,0,
			0,one,0,
			
			0,-one,0,
			0,-one,0,
			0,-one,0,
			0,-one,0,
			
			one,0,0,
			one,0,0,
			one,0,0,
			one,0,0,
			
			-one,0,0,
			-one,0,0,
			-one,0,0,
			-one,0,0,
	});
	
	IntBuffer texCoords = IntBuffer.wrap(new int[]{
		one,0,0,0,0,one,one,one,	
		0,0,0,one,one,one,one,0,
		one,one,one,0,0,0,0,one,
		0,one,one,one,one,0,0,0,
		0,0,0,one,one,one,one,0,
		one,0,0,0,0,one,one,one,
	});
	
	ByteBuffer indices = ByteBuffer.wrap(new byte[]{
			0,1,3,2,
			4,5,7,6,
			8,9,11,10,
			12,13,15,14,
			16,17,19,18,
			20,21,23,22,
	});
	
	@Override
	public void onDrawFrame(GL10 gl)
	{
		// �����Ļ����Ȼ���
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		// ���õ�ǰ��ģ�͹۲����
		gl.glLoadIdentity();
		
		//���������GL_LIGHTING���ʲô��������
		gl.glEnable(GL10.GL_LIGHTING);
		
		
		gl.glTranslatef(0.0f, 0.0f, z);
		//������ת
		gl.glRotatef(xrot, 1.0f, 0.0f, 0.0f);
		gl.glRotatef(yrot, 0.0f, 1.0f, 0.0f);

		//ѡ��ʹ�õ�����
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[filter]);
		
		gl.glNormalPointer(GL10.GL_FIXED, 0, normals);
		gl.glVertexPointer(3, GL10.GL_FIXED, 0, vertices);
		gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, texCoords);

		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	
		//�����ı���
		gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 24,  GL10.GL_UNSIGNED_BYTE, indices);

	    gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	    gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
	    //��ת�Ƕ�
	    if ( key )
		{
		    xrot+=xspeed; 
		    yrot+=yspeed; 
		}
	    
    	if (!light)				// �ж��Ƿ�ʼ��Դ
		{
    		gl.glDisable(GL10.GL_LIGHT1);		// ����һ�Ź�Դ
		}
		else					// ����
		{
			gl.glEnable(GL10.GL_LIGHT1);		// ����һ�Ź�Դ
		}
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		float ratio = (float) width / height;
		//����OpenGL�����Ĵ�С
		gl.glViewport(0, 0, width, height);
		//����ͶӰ����
		gl.glMatrixMode(GL10.GL_PROJECTION);
		//����ͶӰ����
		gl.glLoadIdentity();
		// �����ӿڵĴ�С
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
		// ѡ��ģ�͹۲����
		gl.glMatrixMode(GL10.GL_MODELVIEW);	
		// ����ģ�͹۲����
		gl.glLoadIdentity();	
	}

	public void fogMode()
	{
		mGl10.glFogf(GL10.GL_FOG_MODE, fogMode[fogfilter]);		// ����������ģʽ
	}
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		mGl10 = gl;
		// ����ϵͳ��͸�ӽ�������
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
		// ���ñ�������ɫΪ��������ɫ
		gl.glClearColor(0.5f,0.5f,0.5f,1.0f);	
		
		gl.glFogx(GL10.GL_FOG_MODE, fogMode[fogfilter]);		// ����������ģʽ
		gl.glFogfv(GL10.GL_FOG_COLOR, fogColor,0);			// ���������ɫ
		gl.glFogf(GL10.GL_FOG_DENSITY, 0.35f);			// ��������ܶ�
		gl.glHint(GL10.GL_FOG_HINT, GL10.GL_DONT_CARE);			// ����ϵͳ��μ�������
		gl.glFogf(GL10.GL_FOG_START, 1.0f);				// �����Ŀ�ʼλ��
		gl.glFogf(GL10.GL_FOG_END, 5.0f);				// �����Ľ���λ��
		gl.glEnable(GL10.GL_FOG);					// ʹ������
		
		
		gl.glEnable(GL10.GL_CULL_FACE);
		// ������Ӱƽ��
		gl.glShadeModel(GL10.GL_SMOOTH);
		// ������Ȳ���
		gl.glEnable(GL10.GL_DEPTH_TEST);
		
		
		IntBuffer textureBuffer = IntBuffer.allocate(3);
		// ��������
		gl.glGenTextures(3, textureBuffer);
		texture = textureBuffer.array();
		
		// ���� Nearest �˲���ͼ
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[0]);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_NEAREST); 
		gl.glTexParameterx(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_NEAREST); 
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, GLImage.mBitmap, 0);
		
		// ���������˲�����
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[1]);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR); 
		gl.glTexParameterx(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_LINEAR); 
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, GLImage.mBitmap, 0);
		
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture[2]);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR); 
		gl.glTexParameterx(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_LINEAR_MIPMAP_NEAREST); 
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, GLImage.mBitmap, 0);
		
		gl.glClearDepthf(1.0f);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		//���û�����
	    gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT, lightAmbient);

	    //���������
	    gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, lightDiffuse);

	    //���ù�Դ��λ��
	    gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_POSITION, lightPosition);
	    
	    //����һ�Ź�Դ
	    gl.glEnable(GL10.GL_LIGHT1);
	}
	
	
	
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch ( keyCode )
		{
			case KeyEvent.KEYCODE_DPAD_UP:
				key = true;
				xspeed=-step;
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				key = true;
				xspeed=step;
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				key = true;
				yspeed=-step;
				break;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				key = true;
				yspeed=step;
				break;
			case KeyEvent.KEYCODE_DPAD_CENTER:
				fogfilter++;					// �任����ģʽ
				if (fogfilter>2)					// ģʽ�Ƿ����2
				{
					fogfilter=0;				// ����
				}
				fogMode();
				break;
		}
		return false;
	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		key = false;
		return false;
	}
}

