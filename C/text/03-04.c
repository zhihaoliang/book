/*
 * =====================================================================================
 *
 *       Filename:  03-04.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2013年10月22日 15时12分58秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Dr. Fritz Mehner (mn), mehner@fh-swf.de
 *        Company:  FH Südwestfalen, Iserlohn
 *
 * =====================================================================================
 */
# include <stdio.h>
# include <math.h>
main(){
	double a = 3.0;
	double b = 4.0;
	double c = 5.0;
	double s = (a + b + c) / 2;
	//gcc test.c –lm  -lm就是链接到math库
	double area = sqrt(s * (s - a) * (s - b) * (s - c));

	printf("%f,%f,%f,%f,%f\n",a,b,c,s,area);
	
	return 0;
}

