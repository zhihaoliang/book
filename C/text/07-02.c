/*
 * =====================================================================================
 *
 *       Filename:  07-02.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2013年10月25日 14时38分12秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Dr. Fritz Mehner (mn), mehner@fh-swf.de
 *        Company:  FH Südwestfalen, Iserlohn
 *
 * =====================================================================================
 */
#include <stdio.h>

void print_st_index(){
	static index  = 0;
	printf("This time index is %d\n",(++index));
}

void print_index(){
	int index = 0;
	printf("This time index is %d\n",(++index));
}

void main(){
	int i;
	printf("Print static index begin\n");
	for(i=0;i<10;i++){
		print_st_index();
	}
	printf("Print static index end\n");

	printf("Print index begin\n");
        for(i=0;i<10;i++){
                print_index();
        }
        printf("Print index end\n");


}

