/*
 * =====================================================================================
 *
 *       Filename:  hanoi.c
 *
 *    Description:  
 *
 *        Version:  1.0
 *        Created:  2013年10月29日 14时13分09秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  Dr. Fritz Mehner (mn), mehner@fh-swf.de
 *        Company:  FH Südwestfalen, Iserlohn
 *
 * =====================================================================================
 */
#include<stdio.h>

/**
*此函数主要打印盘子的移动信息
*n:表示当前移动的盘子
*fromTower:表示盘子的初始位置
*toTower:表示盘子要移动到的位置
*/
void print_info(int n,char fromTower,char toTower){
	//表示第几此移动盘子
	static int index = 0;
	printf("%c===================>%c : %d : %d\n",fromTower,toTower,n,++index);
}

void move(int n,char fromTower,char toTower,char passTower){
	if(n == 1){
		print_info(n,fromTower,toTower);
	}else{
		move(n-1,fromTower,passTower,toTower);
		print_info(n,fromTower,toTower);
		move(n-1,passTower,toTower,fromTower);
	}
}

void main(){
	int n ;
	scanf("%d",&n);
	if(n <= 0){
		printf("盘子的数目必需大于0\n");
	}else{
		move(n,'A','C','B');
	}
}
 
