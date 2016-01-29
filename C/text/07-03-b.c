/*
 * =====================================================================================
 *
 *       Filename:  07-03-b.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2013年10月25日 16时10分09秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Dr. Fritz Mehner (mn), mehner@fh-swf.de
 *        Company:  FH Südwestfalen, Iserlohn
 *
 * =====================================================================================
 */
extern index;
int power(int n){
	int y = 1;
	int i;
	for(i=1;i<=n;i++){
		y *= index;
	}
	return y;
}

