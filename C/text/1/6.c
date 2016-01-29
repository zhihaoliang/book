/*
 * =====================================================================================
 *
 *       Filename:  6.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2013年10月21日 15时54分27秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Dr. Fritz Mehner (mn), mehner@fh-swf.de
 *        Company:  FH Südwestfalen, Iserlohn
 *
 * =====================================================================================
 */
main(){
	int a;
	int b;
	int c;
	scanf("%d,%d,%d",&a,&b,&c);
	printf("The max is %d\n",maxThree(a,b,c));
}

int maxThree(int a,int b,int c){
	int d = maxTwo(a,b);
	return maxTwo(d,c);
}

int maxTwo(int a,int b){
	if(a >b){
		return a;
	}
	return b;
}


