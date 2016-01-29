/*
 * =====================================================================================
 *
 *       Filename:  01.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2013年10月29日 17时11分23秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Dr. Fritz Mehner (mn), mehner@fh-swf.de
 *        Company:  FH Südwestfalen, Iserlohn
 *
 * =====================================================================================
 */
#include <stdio.h>

main(){
	int a = 3;
	int b = 4;
	int *point_1;
	int *point_2;

	point_1 =&a;
	point_2= &b;

	printf("%d,%d,%d,%d\n",a,b,*point_1,*point_2);
	printf("%ld,%ld\n",point_1,point_2);
}

