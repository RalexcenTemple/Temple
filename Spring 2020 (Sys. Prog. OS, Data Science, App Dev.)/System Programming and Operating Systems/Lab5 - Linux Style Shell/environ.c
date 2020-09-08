#include <stdio.h>
#include<sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>
#include <sys/param.h>

extern char *environ[];


int main(int args, char* argv[]){
	char** envars = (char**)environ[0];	
	for(int i = 0; envars[i] !=NULL;i++){//provided in "20200211.pdf"
                printf("%s\n",envars[i]);
        }

}
