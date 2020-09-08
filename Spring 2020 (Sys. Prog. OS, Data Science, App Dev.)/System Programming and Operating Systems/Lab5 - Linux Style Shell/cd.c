#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <dirent.h>
#include <unistd.h>
#include <limits.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>
#include <sys/param.h>


#define EXIT_STAT_ERROR 1

extern char *environ[];



int is_dir(char *path);

int main(int args, char* argv[]){
	//for(int i = 0; i < args; i++){
	//	printf("ITEM %d: %s\n",i,argv[i]);
	//}	
		
		char** envars = (char**)environ[0];
	for(int i = 0; envars[i] != NULL; i++){
			printf("ENVAR %d : %s \n", i , envars[i]);
		}
		char* mypwd = envars[18];
		mypwd += 4;
		
		char s[MAXPATHLEN];
		//printf("ARGS : %d \n", args);
		if(args < 2){
			printf("%s\n", getcwd(s, MAXPATHLEN));
		}else{	 
  
    			// printing current working directory 
    			//printf("%s\n", getcwd(s, 100));
	
			if(is_dir(argv[1])){
				if(chdir(argv[1]) != 0){
					puts("DIRECTORY NOT FOUND");		
				}else{
					//puts("DIRECTORY FOUND");
					strcat(mypwd, "/");
					strcat(mypwd, argv[1]);
					if(setenv("PWD",mypwd,0) != 0){
						puts("ENVIRONMENT CHANGE FAILURE");
					}
					printf("%s\n", getcwd(s, MAXPATHLEN));		
				}	
			}else{
				//puts("PROVIDED PATH WAS NOT A DIRECTORY");
			}
		}
		//printf("PWD = %s \n", mypwd);
		//printf("PWD = %s \n", envars[18]);
		
}

int is_dir(char *path){ //given by the professor in "20200218.pdf"
        struct stat stat_buf;

        if(stat(path, &stat_buf) != 0){
                fprintf(stderr, "stat: %s\n", strerror(errno));
		//puts("ERROR");
                return 0;
        }
        return S_ISDIR(stat_buf.st_mode);
}

