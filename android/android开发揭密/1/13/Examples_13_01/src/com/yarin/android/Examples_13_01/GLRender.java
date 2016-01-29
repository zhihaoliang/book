package com.yarin.android.Examples_13_01;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLUtils;
import android.opengl.GLSurfaceView.Renderer;
import android.view.KeyEvent;

public class GLRender implements Renderer
{
	public static final int	num	= 50;					// ������Ŀ
	boolean					twinkle = true;					// ��˸������
	boolean					key;
	public Star[]			star		= new Star[num];	// ������ǵ�����
	float					zoom		= -10.0f;				// ������۲��ߵľ���
	float					tilt		= 90.0f;				// ���ǵ����
	float					spin;								// ��˸���ǵ���ת	
	int one = 0x10000;
	Random random = new Random();
	int						texture;							// ����
	
	IntBuffer coord = IntBuffer.wrap(new int[]{
			0,0,
			one,0,
			one,one,
			0,one,
	});
	IntBuffer vertexs = IntBuffer.wrap(new int[]{
			-one,-one,0,
			one,-one,0,
			one,one,0,
			-one,one,0,
	});
	ByteBuffer indices = ByteBuffer.wrap(new byte[]{
			1, 0, 2, 3
	});
	
	@Override
	public void onDrawFrame(GL10 gl)
	{
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);// �����Ļ����Ȼ���
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);		// ѡ������

		for (int i=0; i<num; i++)				// ѭ���������е�����
		{
			gl.glLoadIdentity();				// ����ÿ������֮ǰ������ģ�͹۲����
			gl.glTranslatef(0.0f,0.0f,zoom);			// ������Ļ����
			gl.glRotatef(tilt,1.0f,0.0f,0.0f);			// ��б�ӽ�
			gl.glRotatef(star[i].angle,0.0f,1.0f,0.0f);	// ��ת����ǰ�������ǵĽǶ�
			gl.glTranslatef(star[i].dist,0.0f,0.0f);	// ��X�������ƶ�
			gl.glRotatef(-star[i].angle,0.0f,1.0f,0.0f);	// ȡ����ǰ���ǵĽǶ�
			gl.glRotatef(-tilt,1.0f,0.0f,0.0f);		// ȡ����Ļ��б

			//���ö�������
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			//������ɫ����
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
			
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			
			if (twinkle)					// ������˸Ч��
			{
				// ʹ��byte����ֵָ��һ����ɫ
				gl.glColor4f((float)star[(num-i)-1].r/255.0f,(float)star[(num-i)-1].g/255.0f,(float)star[(num-i)-1].b/255.0f,1.0f);
				gl.glVertexPointer(3, GL10.GL_FIXED, 0, vertexs);
				gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, coord);
				
				{
					coord.position(0);
					vertexs.position(0);
					indices.position(0);
					
					gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4, GL10.GL_UNSIGNED_BYTE, indices);
				}
			    //���ƽ���
			    gl.glFinish();
			}

			gl.glRotatef(spin,0.0f,0.0f,1.0f);			// ��z����ת����
			
			// ʹ��byte����ֵָ��һ����ɫ
			gl.glColor4f((float)star[(num-i)-1].r/255.0f,(float)star[(num-i)-1].g/255.0f,(float)star[(num-i)-1].b/255.0f,1.0f);
			gl.glVertexPointer(3, GL10.GL_FIXED, 0, vertexs);
			gl.glTexCoordPointer(2, GL10.GL_FIXED, 0, coord);
			
			{
				coord.position(0);
				vertexs.position(0);
				indices.position(0);
				
				gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 4, GL10.GL_UNSIGNED_BYTE, indices);
			}
		    //���������ν���
		    gl.glFinish();
		    
		    gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		    //ȡ����������
		    gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		    gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		    
			spin+=0.01f;					// ���ǵĹ�ת
			star[i].angle+=(float)(i)/(float)num;		// �ı����ǵ���ת�Ƕ�
			star[i].dist-=0.01f;				// �ı����������ĵľ���

			if (star[i].dist<0.0f)			// ���ǵ���������ô
			{
				star[i].dist+=5.0f;			// ������5����λ
				star[i].r=random.nextInt(256);		// ��һ���º�ɫ����
				star[i].g=random.nextInt(256);		// ��һ������ɫ����
				star[i].b=random.nextInt(256);		// ��һ������ɫ����
			}

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

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config)
	{
		gl.glShadeModel(GL10.GL_SMOOTH);						// ������Ӱƽ��
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);					// ��ɫ����
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);			// ����ϵͳ��͸�ӽ�������
		
		IntBuffer buffer = IntBuffer.allocate(1);
		// ����һ������
		gl.glGenTextures(1, buffer);
		texture = buffer.get();
		// ����һ�������˲�����
		gl.glBindTexture(GL10.GL_TEXTURE_2D, texture);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
		gl.glTexParameterx(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, GLImage.mBitmap, 0);
		
		
		gl.glEnable(GL10.GL_TEXTURE_2D);				// ��������ӳ��
		gl.glShadeModel(GL10.GL_SMOOTH);				// ������Ӱƽ��
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);			// ��ɫ����
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);	// ������ϸ��͸������
		gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE);			// ���û�ɫ����ȡ�ð�͸��Ч��
		gl.glEnable(GL10.GL_BLEND);					// ���û�ɫ

		for (int i=0; i<num; i++)				// ����ѭ������ȫ������
		{
			Star starTMP = new Star();
			starTMP.angle=0.0f;				// �������Ƕ�����Ƕȿ�ʼ
			starTMP.dist=((float)(i)/(float)num)*5.0f;		// �������������ĵľ���
			starTMP.r=random.nextInt(256);			// Ϊstar[loop]���������ɫ����
			starTMP.g=random.nextInt(256);			// Ϊstar[loop]���������ɫ����
			starTMP.b=random.nextInt(256);			// Ϊstar[loop]���������ɫ����
			star[i] = starTMP;
		}
	}

	public boolean onKeyUp(int keyCode, KeyEvent event)
	{
		twinkle=!twinkle;
		return false;
	}
}


class Star
{
	int		r, g, b;	// ���ǵ���ɫ
	float	dist;		// ���Ǿ������ĵľ���
	float	angle	= 0.0f;// ��ǰ���������ĽǶ�
}
