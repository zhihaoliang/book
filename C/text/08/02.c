/*
 * =====================================================================================
 *
 *       Filename:  02.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2013年10月29日 17时42分41秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Dr. Fritz Mehner (mn), mehner@fh-swf.de
 *        Company:  FH Südwestfalen, Iserlohn
 *
 * =====================================================================================
 */
#include <stdio.h>
#include <stdlib.h>

void max(int *point_a,int *point_b){
	if(*point_a < *point_b){
		int temp;
		temp = *point_a;
		*point_a = *point_b;
		*point_b = temp; 
	}
}
main(){
	int a;
	int b;
	int *point_a = (int *)malloc(sizeof(int));
	int *point_b;
	scanf("%d,%d",&a,&b);
	*point_a = a;
	point_b = &b;
	max(point_a,point_b);
	printf("%d,%d,%d,%d",* point_a,* point_b,a,b);
}

