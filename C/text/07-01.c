/*
 * =====================================================================================
 *
 *       Filename:  07-01.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2013年10月24日 11时30分00秒
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
	print_star();
	print_msg();
	print_star();
}

void print_star(){
	printf("************************\n");
}

void print_msg(){
	printf("hello,girl\n");
}
