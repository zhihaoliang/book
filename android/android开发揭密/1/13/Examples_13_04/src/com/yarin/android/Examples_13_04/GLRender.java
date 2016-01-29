package com.yarin.android.Examples_13_04;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLUtils;
import android.opengl.GLSurfaceView.Renderer;

public class GLRender implements Renderer
{
	int texture = -1;
				
	int	xloop;						// X��ѭ������
	int	yloop;						// Y��ѭ������

	float xrot, yrot, zrot;
	
	// ������ӵ���ʾ�б�
	FloatBuffer boxVertices = FloatBuffer.allocate(60);
	FloatBuffer boxTexCoords= FloatBuffer.allocate(40);
	
	// ������Ӷ�������ʾ�б�
	FloatBuffer topVertices = FloatBuffer.allocate(12);
	FloatBuffer topTexCoords= FloatBuffer.allocate(8);
	
	float[][] boxcol = {
			{1.0f, 0.0f, 0.0f},
			{1.0f, 0.5f, 0.0f},
			{1.0f, 1.0f, 0.0f},
			{0.0f, 1.0f, 0.0f},
			{0.0f, 1.0f, 1.0f},
	};
	
	float[][] topcol= {
			{0.5f, 0.0f, 0.0f},
			{0.5f, 0.25f, 0.0f},
			{0.5f, 0.5f, 0.0f},
			{0.0f, 0.5f, 0.0f},
			{0.0f, 0.5f, 0.5f},
	};
	
	public void BuildLists(GL10 gl)
	{
		boxTexCoords.put(new float[]{1.0f, 1.0f,0.0f, 1.0f,0.0f, 0.0f,1.0f,0.0f});
		boxVertices.put(new float[]{-1.0f, -1.0f, -1.0f,1.0f, -1.0f, -1.0f,1.0f, -1.0f,  1.0f,-1.0f, -1.0f,  1.0f});
		
		boxTexCoords.put(new float[]{0.0f, 0.0f,1.0f, 0.0f,1.0f, 1.0f,0.0f, 1.0f});
		boxVertices.put(new float[]{-1.0f, -1.0f,  1.0f,1.0f, -1.0f,  1.0f,1.0f,  1.0f,  1.0f,-1.0f,  1.0f,  1.0f});
		
		boxTexCoords.put(new float[]{1.0f, 0.0f,1.0f, 1.0f,0.0f, 1.0f,0.0f, 0.0f});
		boxVertices.put(new float[]{-1.0f, -1.0f, -1.0f,-1.0f,  1.0f, -1.0f,1.0f,  1.0f, -1.0f,1.0f, -1.0f, -1.0f});
		
		boxTexCoords.put(new float[]{1.0f, 0.0f,1.0f, 1.0f,0.0f, 1.0f,0.0f, 0.0f});
		boxVertices.put(new float[]{1.0f, -1.0f, -1.0f,1.0f,  1.0f, -1.0f,1.0f,  1.0f,  1.0f,1.0f, -1.0f,  1.0f});
		
		boxTexCoords.put(new float[]{0.0f, 0.0f,1.0f, 0.0f,1.0f, 1.0f,0.0f, 1.0f});
		boxVertices.put(new float[]{-1.0f, -1.0f, -1.0f,-1.0f, -1.0f,  1.0f,-1.0f,  1.0f,  1.0f,-1.0f,  1.0f, -1.0f});
		
		topTexCoords.put(new float[]{0.0f, 1.0f,0.0f, 0.0f,1.0f, 0.0f,1.0f, 1.0f});
		topVertices.put(new float[]{-1.0f,  1.0f, -1.0f,-1.0f,  1.0f,  1.0f,1.0f,  1.0f,  1.0f,1.0f,  1.0f, -1.0f});
	}
	@Override
	public void onDrawFrame(GL10 gl)
	{
		// �����Ļ����Ȼ���
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);		
		
		// ������
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		
		for (yloop=1;yloop<6;yloop++)
		{
			for (xloop=0;xloop<yloop;xloop++)
			{
				gl.glLoadIdentity();			// ����ģ�ͱ仯����
				
				// ���ú��ӵ�λ��
				gl.glTranslatef(1.4f+((float)(xloop)*2.8f)-((float)(yloop)*1.4f),((6.0f-(float)(yloop))*2.4f)-7.0f,-20.0f);
				
				gl.glRotatef(45.0f-(2.0f*yloop)+xrot,1.0f,0.0f,0.0f);
				
				gl.glRotatef(45.0f+yrot,0.0f,1.0f,0.0f);
				
				gl.glColor4f(boxcol[yloop-1][0], boxcol[yloop-1][1], boxcol[yloop-1][2], 1.0f);
				
				gl.glVertexPointer(3, GL10.GL_FLOAT, 0, boxVertices);
				gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, boxTexCoords);
				gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
				gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 4, 4);
				gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 8, 4);
				gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 12, 4);
				gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 16, 4);

	            /* Select The Top Color */
				gl.glColor4f(topcol[yloop-1][0], topcol[yloop-1][1], topcol[yloop-1][2], 1.0f);
				gl.glVertexPointer(3, GL10.GL_FLOAT, 0, topVertices);
				gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, topTexCoords);
				gl.glDrawArrays(GL10.GL_TRIANGLE_FAN, 0, 4);
			}
		}
		
	    gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	    
	    xrot+=0.5f;
	    yrot+=0.6f; 
	    zrot+=0.3f; 
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
		gl.glFrustumf(-ratio, ratio, -1, 1, 1, 30);
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
		
		gl.glEnable(GL10.GL_CULL_FACE);
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
		
		IntBuffer intBuffer = IntBuffer.allocate(1);
		// ��������
		gl.glGenTextures(1, intBuffer);
		texture = intBuffer.get();
		// ����Ҫʹ�õ�����
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
		
		//��������
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, GLImage.mBitmap, 0);
		
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR_MIPMAP_NEAREST);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		// ������ʾ�б�
		BuildLists(gl);		
		
		gl.glEnable(GL10.GL_LIGHT0);					// ʹ��Ĭ�ϵ�0�ŵ�
		//gl.glEnable(GL10.GL_LIGHTING);					// ʹ�õƹ�
		gl.glEnable(GL10.GL_COLOR_MATERIAL);				// ʹ����ɫ����

	}
}

