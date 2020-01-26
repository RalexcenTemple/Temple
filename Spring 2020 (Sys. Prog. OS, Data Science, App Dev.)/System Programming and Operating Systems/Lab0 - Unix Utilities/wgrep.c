#include <stdlib.h>
#include <stdio.h>
#include <string.h>


//Reed Ceniviva
//CIS 3207 System Programming and OS
//Lab0 Unix Utils
//Description: Recreate basic unix utility calls
	
int main(int argv, char** args){
	if(argv >= 3){
		for(int i = 2 ; i < argv; i++){
			FILE *rFile = fopen(args[i],"r");
			if(rFile){
				char* buff = NULL;
				size_t buffSize = 0;
				ssize_t line;//using signed size_t to catch the -1 errors
				int lineCount = 0;
				while((line = getline(&buff, &buffSize,rFile)) != -1){//failed to read if -1
					lineCount++;
					int count = 0;
					for(int m = 0; m < line; m++){
						for(int j = 0; j < strlen(args[1]); j++){
							if(args[1][j] == buff[m]){
								count++;
								m++;
								if(count == strlen(args[1])){
									printf("%s", buff);
									m -= (count-1);
									count = 0;
									//evidently from the tests each match should print the line only once so making some changes to thesevalues post match
									m = line;
								}
							}else{
								m -= count;
								count = 0;
								break;
							}
						}
					}
				}
			}else{
				puts("wgrep: cannot open file");
				return 1;
			}
		}
	}else if(argv == 2){//realize this is pretty bad practice with ust duplicating code
		char* buff = NULL;
		size_t buffSize = 0;
		ssize_t line;
		int lineCount = 0;
		while((line = getline(&buff, &buffSize, stdin)) != -1){//failed to read if -1
                                        lineCount++;
                                        int count = 0;
					if(strcmp(buff,"\n") == 0){//check if the user is not entering informaion anymore
						break;
					}
                                        for(int m = 0; m < line; m++){
                                                for(int j = 0; j < strlen(args[1]); j++){
                                                        if(args[1][j] == buff[m]){
                                                                count++;
                                                                m++;
                                                                if(count == strlen(args[1])){
                                                                        printf("%s", buff);
                                                                        m -= (count-1);
                                                                        count = 0;
									m = line;
                                                                }
                                                        }else{
                                                                m -= count;
                                                                count = 0;
								break;
                                                        }
                                                }
                                        }
                                }
	}else if(argv == 1){
		puts("wgrep: searchterm [file ...]");
		return(1);
	}
	return(0);	
}
