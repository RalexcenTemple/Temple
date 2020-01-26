#include <stdlib.h>
#include <stdio.h>
#include <string.h>


//Reed Ceniviva
//CIS 3207 System Programming and OS
//Lab0 Unix Utils


int main(int args, char** argv){
        if(args == 1){
                puts("wzip: file1 [file2 ...]");
                return 1;
        }
        char c = 0;
        int count = 0;
        char d = 0;
        int i = 1;
        while(i < args){
		FILE *fp = fopen(argv[i], "r");
                if(fp){
                        while(1){
				c = fgetc(fp);
                                if(count == 0){//catch initial run
                                        d = c;
                                        count ++;
                                }else if(c == d){//adjust for conecutive matches
                                        count ++;
                                }else{//adjust for a change in character
                                        if(c==EOF){
						break;
					}else{
						fwrite(&count, sizeof(int), 1, stdout);
                                        	fwrite(&d, sizeof(char), 1, stdout);
                                        	d = c;
                                        	count = 1;
					}
                                }
                        }
                i++;
                }else{
                        puts("FAILED TO OPEN FILE");
                        return(-1);
                }
                if(i == args){
                        fwrite(&count, sizeof(int), 1, stdout);
                        fwrite(&d, sizeof(char), 1, stdout);
                }
        }
}

