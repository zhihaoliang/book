/*
 * =====================================================================================
 *
 *       Filename:  07-03-a.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2013年10月25日 15时58分54秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Dr. Fritz Mehner (mn), mehner@fh-swf.de
 *        Company:  FH Südwestfalen, Iserlohn
 *
 * =====================================================================================
 */
#include<stdio.h>
#include"07-03-b.c" 
int index;
void main(){
	int power(int);
	int b = 3;
	int c;
	int d;
	int m;
	printf("Please enter the number a and its power m:\n");
	scanf("%d,%d",&index,&m);
	c = index * b;
	printf("%d*%d == %d\n",index,b,c);
	d = power(m);
	printf("%d**%d=%d\n",index,m,d);
}
