main(){
	 printf("%d\n",text());
	 printf("%d\n",text());
}

text(){
	static int i = 100;
	i += 100;
	return i;
 }
