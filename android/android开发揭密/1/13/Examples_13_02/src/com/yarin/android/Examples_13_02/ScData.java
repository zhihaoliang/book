package com.yarin.android.Examples_13_02;

import java.util.ArrayList;
import java.util.List;
//VERTEX����ṹ
class VERTEX
{
	float x, y, z;// 3D ����
	float u, v;// ��������
	public VERTEX(float x,float y,float z,float u,float v)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.u = u;
		this.v = v;
	}
}
//TRIANGLE�����νṹ
class TRIANGLE
{
	// VERTEXʸ�����飬��СΪ3
	VERTEX[]	vertex	= new VERTEX[3];
}
//SECTOR���νṹ
class SECTOR
{
	// Sector�е������θ���
	int numtriangles;
	// �����е�list
	List<TRIANGLE>	triangle	= new ArrayList<TRIANGLE>();
}
