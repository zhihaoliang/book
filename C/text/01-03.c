/*
 * =====================================================================================
 *
 *       Filename:  01-03.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2013年10月21日 14时19分26秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Dr. Fritz Mehner (mn), mehner@fh-swf.de
 *        Company:  FH Südwestfalen, Iserlohn
 *
 * =====================================================================================
 */
main()
{
	int a;
	int b;
	scanf("%d,%d",&a,&b);
	printf("max is %d\n",max(a,b));
}

int max(int a, int b)
{
	if(a>b){
		return a;
	}
	return b;
}
