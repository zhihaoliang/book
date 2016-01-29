/*
 * =====================================================================================
 *
 *       Filename:  03.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2013年10月30日 10时58分38秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Dr. Fritz Mehner (mn), mehner@fh-swf.de
 *        Company:  FH Südwestfalen, Iserlohn
 *
 * =====================================================================================
 */
#include<stdio.h>

main(){
	int a[10];
	int i;
	printf("Please enter 10 integer numbers:");
	for(i=0;i<10;i++){
		scanf("%d",&a[i]);
	}
	
	for(i=0;i<10;i++){
		printf("%d",*(a+i));
	}	
	printf("\n");
}

