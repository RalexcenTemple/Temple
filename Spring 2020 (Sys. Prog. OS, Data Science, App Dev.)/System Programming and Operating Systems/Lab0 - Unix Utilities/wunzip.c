#include <stdlib.h>
#include <stdio.h>
#include <elf.h>

//Reed Ceniviva
//CIS 3207 System Programming and OS
//Lab0 Unix Utils
//Description: Recreate basic unix utility calls


int main(int args, char** argv){//got the main arguments mized up
	if(args == 1){
		printf("wunzip: file1 [file2 ...]\n");
		return(1);
	}
	for(int i = 1; i < args; i++){
		//printf("I: %d\n", i);
		FILE *fp = fopen(argv[i], "rb");
		if(fp){
			char c;
			int a;
			//int j = 0;
			while(1){
				fread(&a, sizeof(int), 1, fp);
				fread(&c, sizeof(char), 1, fp);
				if(feof(fp)){
					break;
				}
				//printf("A: %d\n C: %c C(int): %d\n", a,c,(int)c);
				//if(a == 1 || (int)c == 10){
				if((int)c == 10){
					puts("");	
				}else{
					for(int m = 0; m < a; m++){
						printf("%c",c);
					}
				}
			//printf("J: %d\n", j);
			}
			fclose(fp);
		}
	}
}
	
