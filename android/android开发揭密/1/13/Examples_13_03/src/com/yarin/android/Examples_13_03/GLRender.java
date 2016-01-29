package com.yarin.android.Examples_13_03;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLUtils;
import android.opengl.GLSurfaceView.Renderer;

public class GLRender implements Renderer
{
	float vertex[][][]=new float[45][45][3];	// Points���񶥵�����(45,45,3)
	int wiggle_count = 0;						// ָ�����β��˵��˶��ٶ�
	float hold;							// ��ʱ����
	float xrot, yrot, zrot;
	int texture = -1;
	FloatBuffer texCoord = FloatBuffer.allocate(8);
	FloatBuffer points = FloatBuffer.allocate(12);
	@Override
	public void onDrawFrame(GL10 gl)
	{
		int x, y;						// ѭ������
		float float_x, float_y, float_xb, float_yb;		// ���������εĲ��˷ָ�ɺ�С���ı���

		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);	// �����Ļ����Ȼ���
		gl.glLoadIdentity();					// ���õ�ǰ��ģ�͹۲����

		gl.glTranslatef(0.0f,0.0f,-12.0f);				// ������Ļ12����λ

		gl.glRotatef(xrot,1.0f,0.0f,0.0f);				// �� X ����ת
		gl.glRotatef(yrot,0.0f,1.0f,0.0f);				// �� Y ����ת
		gl.glRotatef(zrot,0.0f,0.0f,1.0f);				// �� Z ����ת

	    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	    gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	    
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, points);
	    gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoord);
	    
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);		// ѡ������
		
		for( x = 0; x < 44; x++ ){
			for( y = 0; y < 44; y++) {
				 float_x = (float)(x)/44.0f;		// ����X����ֵ
				 float_y = (float)(y)/44.0f;		// ����Y����ֵ
				 float_xb = (float)(x+1)/44.0f;		// X����ֵ+0.0227f
				 float_yb = (float)(y+1)/44.0f;		// Y����ֵ+0.0227f
		            
				texCoord.clear();
				texCoord.put(float_x);
				texCoord.put(float_y);
				texCoord.put(float_x);
				texCoord.put(float_yb);
				texCoord.put(float_xb);
				texCoord.put(float_yb);
				texCoord.put(float_xb);
				texCoord.put(float_y);
				
				points.clear();
				points.put(vertex[x][y][0]);
				points.put(vertex[x][y][1]);
				points.put(vertex[x][y][2]);
				
				points.put(vertex[x][y+1][0]);
				points.put(vertex[x][y+1][1]);
				points.put(vertex[x][y+1][2]);
				
				points.put(vertex[x+1][y+1][0]);
				points.put(vertex[x+1][y+1][1]);
				points.put(vertex[x+1][y+1][2]);
				
				points.put(vertex[x+1][y][0]);
				points.put(vertex[x+1][y][1]);
				points.put(vertex[x+1][y][2]);
				
				///////////////
				gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
			}
		}
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		if( wiggle_count == 2 )					// �������Ͳ����ٶ�(ÿ��2֡һ��)
		{
			for( y = 0; y < 45; y++ )			// ��Yƽ��ѭ��
			{
				hold=vertex[0][y][2];			// �洢��ǰ��ನ��ֵ
				for( x = 0; x < 44; x++)		// ��Xƽ��ѭ��
				{
					// ��ǰ����ֵ�������Ҳ�Ĳ���ֵ
					vertex[x][y][2] = vertex[x+1][y][2];
				}
				vertex[44][y][2]=hold;			// �ղŵ�ֵ��Ϊ�����Ĳ���ֵ
			}
			wiggle_count = 0;				// ����������
		}
		wiggle_count++;						// ��������һ
		
		xrot+=0.3f;						// X ����ת
		yrot+=0.2f;						// Y ����ת
		zrot+=0.4f;						// Z ����ת
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height)
	{
		float ratio = (float) width / (float)height;
		//����OpenGL�����Ĵ�С
		gl.glViewport(0, 0, width, height);
		//����ͶӰ����
		gl.glMatrixMode(GL10.GL_PROJECTION);
		//����ͶӰ����
		gl.glLoadIdentity();
		// �����ӿڵĴ�С
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 15);
		// ѡ��ģ�͹۲����
		gl.glMatrixMode(GL10.GL_MODELVIEW);	
		// ����ģ�͹۲����
		gl.glLoadIdentity();	
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		// ��ɫ����
		gl.glClearColor(0, 0, 0, 0);
		
		
		// ������Ӱƽ��
		gl.glShadeModel(GL10.GL_SMOOTH);
		// ������Ȳ���
		gl.glEnable(GL10.GL_DEPTH_TEST);
		
		//��������ӳ��
		gl.glClearDepthf(1.0f);
		//��Ȳ��Ե�����
		gl.glDepthFunc(GL10.GL_LEQUAL);
		//��ϸ��͸������
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		//����2D��ͼ,����
		gl.glEnable(GL10.GL_TEXTURE_2D);
		
		gl.glEnable(GL10.GL_LEQUAL);
		
		IntBuffer intBuffer = IntBuffer.allocate(1);
		// ��������
		gl.glGenTextures(1, intBuffer);
		texture = intBuffer.get();
		// ����Ҫʹ�õ�����
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
		
		//��������
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, GLImage.mBitmap, 0);
		// �����˲�
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		
		// ��Xƽ��ѭ��
		for(int x=0; x<45; x++)
		{
			// ��Yƽ��ѭ��
			for(int y=0; y<45; y++)
			{
				// �������Ӳ���Ч��
				vertex[x][y][0]=((float)x/5.0f)-4.5f;
				vertex[x][y][1]=(((float)y/5.0f)-4.5f);
				vertex[x][y][2]=(float)(Math.sin(((((float)x/5.0f)*40.0f)/360.0f)*3.141592654*2.0f));
			}
		}

	}

}

