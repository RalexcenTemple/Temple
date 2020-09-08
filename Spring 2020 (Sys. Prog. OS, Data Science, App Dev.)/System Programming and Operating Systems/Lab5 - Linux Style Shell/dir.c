#include <dirent.h>
#include <stdio.h>

int main(int args, char* argv[]){
	DIR *dir;
        struct dirent *s;
	printf("ARGS : %d\n", args);
	for(int i = 1; argv[i] != NULL; i++){

		printf("ARGV[%d] : %s \n" , i , argv[i]);

	}

        if(argv[1]){//view directory specified
		printf("SPECIFIED DIRECTORY : %s \n", argv[1]);
                if((dir = opendir(argv[1])) == NULL){
                        puts("OPENING DIRECTORY FAILED");
			return -1;
                }

                while((s = readdir(dir))!=NULL){
                        printf("%s\n",s->d_name);
                }
        }else{//to view the current directory if none was specified
                dir = opendir(".");
		if(dir){
			while((s = readdir(dir)) != NULL){
				printf("%s\n", s->d_name);
			}
		}
        }
}

