/*
 * =====================================================================================
 *
 *       Filename:  charText.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2013年10月23日 10时44分21秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Dr. Fritz Mehner (mn), mehner@fh-swf.de
 *        Company:  FH Südwestfalen, Iserlohn
 *
 * =====================================================================================
 */
# include <stdio.h>
main(){
	char a[10]={'a'};
	int i;
	for(i=0;i<10;i++){
		printf("hello %d is %c\n",i,a[i]);
	}
}

