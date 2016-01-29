/*
 * =====================================================================================
 *
 *       Filename:  03-02.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2013年10月22日 10时54分29秒
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
	float corpus = 1000;
	float r1 = 0.0036;
	float r2 = 0.0225;
	float r3 = 0.0198;

	float p1 = corpus * (1 + r1);
	float p2 = corpus * (1 + r2);
	float p3 = corpus * (1 + r3/2) * (1 + r3/2);

	printf("%f,%f,%f\n",p1,p2,p3);
}

