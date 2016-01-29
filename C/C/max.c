main(){
	int a;
	int b;
	scanf("%d,%d",&a,&b);
	int c =maxNum(a,b);
	printf("max is %d\n",c);
}

int maxNum(int a,int b){
	if(a>b){
		return a;
	}
	return b;
}
