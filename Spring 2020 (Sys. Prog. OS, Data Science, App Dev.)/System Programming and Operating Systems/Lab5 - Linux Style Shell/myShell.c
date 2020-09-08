#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <dirent.h>
#include <unistd.h>
#include <limits.h>
#include <stdio.h> 
#include<sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <errno.h>
#include <sys/param.h>
#include <fcntl.h>




extern char *environ[];

#define READ 0
#define WRITE 1

int parse(char* line);
char* parsePath(char* path);
void runProgram(char* argv[]);
void ch_dir(char* path);
int is_dir(char *path);
char* rmNL(char* s);



int main(int args, char** argv){

	char *buff = NULL;
        size_t buffsize = 0;
        ssize_t line;
	char wd[100];
	getcwd(wd, 100);
	strcat(wd,"/myShell");
	if(setenv("SHELL", wd, 1) != 0){//set shell path environment variable
		puts("SHELL ENV CHANGE FAIL");
	}
	if(args > 1){
		FILE* file;// i think this is how i would start building in being able to read a file right off the bat
		if(file = fopen(argv[1],"r")){//realized my implementation only handled input from the command line as the program is being run
			char *line = (char*)malloc(sizeof(char)*100);
			while(fgets(line,(sizeof(char)*100), file)){//may not catch internal commands
				int check = parse(line);
				if(check == 0){
					puts("failed command");
				}
			}
			return 0;
		}
		char* temp = (char*)malloc(sizeof(char)*99);
		for(int i = 1; argv[i] != NULL; i++){//collect the arguements into one line
			strcat(temp, argv[i]);
			strcat(temp, " ");	
		}
		int check = parse(temp);
		if(check == 0){//check if the command succeeded
                	puts("failed command");
			puts("enter help for shell information");
                }	
	}
	while(1){//prompt user 
		char s[100];
		printf("\n%s>",getcwd(s , 100));
		int check = getline(&buff,&buffsize,stdin);
		

		if(!strcmp(buff,"exit\n") || !strcmp(buff,"^d\n") || check == -1 || !strcmp(buff,"quit\n")){//catch exiting input
			puts("");
			break;
		}else if(!strcmp(buff,"help\n")){//print help information
			puts("cd : change directories.\n  - cd with no arguements will print the current directory.\n  - cd can take 1 argument, the name of a directory to change the current working directory to.");
			puts("");
			puts("clr : clear the view.\n  - clears all text from the terminal and prints the prompt.\n  - this does not take arguments");
			puts("");
			puts("dir : print the items in a directory.\n  - dir without arguments prints the contents of the current directory.\n  - dir can take any number of arguements that are strings to directories. these directories and their contents will be printed seperately to stdout.");
			puts("");
			puts("environ : List all the environment variables.\n  - provides the user with a list of the environment variables of the shell printed on seperate lines to stdout");
			puts("");
			puts("echo : output input.\n  - echo takes the next line of input and outputs it to stdout.");
			puts("");
			puts("help : prints out helpful information for navigating the shell.\n  - such as this message!.");
			puts("");
			puts("pause : pauses the operation of the shell until the user chooses to resume the shell.\n  - accepts input from stdin, does not take arguements.");
			puts("");
			puts("quit/exit : leave the shell.\n  - both of these have the same function of leaving the shell.\n  - One of these is listed in the \"The shell must support the following internal commands\" and the other is listed in the  \"Built in commands\" section.");
			puts("");
			puts("path : add directory paths to the PATH environment variable.\n  - (this is not listed as part the section \"The shell must support the following internal commands\" though it is part of the \"Built in commands\" section.");
			puts("");
			puts("REDIRECTION");
			puts("");
			puts(" > : Redirect the output from the left command to the file on the right");
			puts("");
			puts(" < : (BROKEN)Redirecting the input for a comand(left) to be from a file(right)");
			puts("");
			puts(" | : (HALF BROKEN)Pipe the output from a command(left) to be the input for a command/file(right)");
			puts("");
			puts("BACKGROUND");
			puts("");
			puts(" & : run the command(s)(all to the left) in the background leaving the\n      shell open for use");


		}else{//parse input
			int check = parse(buff);//parse information and get back if it succeeded or failed
			if(check == 0){
				puts("failed command");
			}
		}
	}

}

int parse(char* line){
	char* argv[100];
	int lines = 0;
	char* parts = strtok(line," ");
	while(parts != NULL){//parse out the parts of the line again
		argv[lines] = parts;
		lines++;
		parts = strtok(NULL," ");
	}
	argv[lines-1][strlen(argv[lines-1])] = '\0';
	//"Set the first location in the array after your C-Strings to NULL"
	argv[lines] = NULL;
	int argc = lines;
	
	
	for(int i = 0; argv[i] != NULL; i++){
		argv[i] = rmNL(argv[i]);//make sure theres an ending null character for each segment of command line
	}
	
	if(!strcmp(argv[0],"cd")){//cd must be caught within the shell
		ch_dir(argv[1]);  //since changing environment variables from a different process doesnt help
		return 1;
	}else{

	char** envars = (char**) environ[0];//get environment variables
	int j = 0;
	
	int pathlen = strlen(argv[0]);
	char* nlPos;

	if((nlPos = strchr(argv[0],'\n')) != NULL){//replace with rmNL
		*nlPos = NULL;				//to afraid to change code at this point to replace it
	}

	int absolute = 0;

	if(strchr(argv[0],'/')){//check for an absolute path name
		argv[0] = parsePath(argv[0]);
		absolute++;
	}

	FILE *fp = fopen(argv[0], "r");	
	
	if(!fp && !absolute){//failed to open file, not an absolute path 
		char* globalPaths = envars[4];
		
		char* parsePaths = (char*)malloc(strlen(envars[4]) * sizeof(char));
		strcpy(parsePaths,(envars[4]));//get paths to check for programs
		
		parsePaths += 5;//get rid of the "PATH=" at the begining of the paths list.
		char*cp = strchr(parsePaths,':');
		while(cp != NULL){//while there are still more paths to check
			cp[0] = '\0';
			char* temp;
			strcpy(temp,argv[0]);
			
			int comeon = (strlen(parsePaths) + strlen(argv[0]));
			char* checkPath = malloc((comeon) * (sizeof(char)));
			
			memcpy(checkPath,parsePaths,(strlen(parsePaths)));
			strcat(checkPath,"/./");
			strcat(checkPath, argv[0]);//put the filename on the end of the pathto check
	
			argv[0] = checkPath;
			

			int lineLen = 0;
			for(int i = 0; argv[i] != NULL; i++){
				lineLen += (strlen(argv[i]));
			}
			
			char* fuLine = malloc(lineLen * sizeof(char));
			strcat(fuLine, argv[0]);

			for(int i = 1; argv[i] != NULL; i++){ // put line back together
				strcat(fuLine, " ");
				strcat(fuLine, argv[i]);
			}	
			//fuline is now the name of the file apended to a path found in the path env var
			argv[0] = temp;
			int check = parse(fuLine);//run full prepared line 
			if(check != 0){
				return check;
			}
			parsePaths = (cp + 1);//move pointer to next path
			cp = strchr(parsePaths, ':');//move cp to the next path
		}
	}else if(fp){//file found
		runProgram(argv);
		close(fp);
		return 1;
	}else if(absolute && !fp){//absolute file not found
		
	}else{//file not found anywhere
		puts("FILE NOT FOUND");
	}

	}
	return 0;
}

char* parsePath(char* path){//make a path readible for searching in system
	int count = 0;
	
	for(int i = 0; path[i] != NULL; i++){//count the number of slashes to be added
		if(path[i] == '/'){
			count++;
		}
	}
	size_t newPathLen = (strlen(path) + count);
	
	
	char *newPath = malloc(newPathLen * sizeof(char));//allocate memory for new string
	int j = 0;
	for(int i = 0; path[i] != NULL; i++){
		if(j < newPathLen){
			newPath[j] = path[i];
			j++;
			if(path[i] == '/' && path[i+1] != NULL){//add extra slash when a slash is found
				newPath[j] = '/';
				j++;
			}
		}else{//the number of char being added doesnt resemble the number of characters there should be
			puts("THE NUMBER OF CHARACTERS BEING ADDED SEEMS INCORRECT");
		}
	}
	
	char* toReturn = newPath;

	return toReturn;
}


void runProgram( char * argv[]){
	
	char* fn = (char*)malloc(sizeof(char)*(strlen(argv[0])));
	int in=0;	//in
	int o =0;	//out
	int r =0;	//right
	int l =0;	//left
	int p =0;	//pipe
	int a =0;	//ampersan
	
	for(int i = 0; argv[i] != NULL; i++){//search string for redirection/background characters and save int values related to them
		//printf("ARGV[%d] : %s\n", i, argv[i]);
		if(!strcmp(argv[i],">")){//making this else if format was an after thought
			argv[i] = NULL;
			r = i+1;//location of redirection to
		}else
		if(!strcmp(argv[i],"<")){
			argv[i] = NULL;
                        l = i + 1;//location of redirection from
		
                }else
		if(!strcmp(argv[i],"|")){
			argv[i] = NULL;
                        p = i;//+-1 is from to for pipe
                }else
		if(!strcmp(argv[i],"&")){
			argv[i] = NULL;
			a = 1;//just need to know there is one
                }

	}

	strcat(fn,argv[0]);
	strcat(fn,"\0");
	
	int stdin_og = STDIN_FILENO;
	int stdout_og = STDOUT_FILENO;	
	if(fork()==0){//handle redirection before execing
		if(r){//redirection out
			o = open(argv[r], O_WRONLY|O_CREAT, S_IRWXU|S_IRWXG|S_IRWXO);
                        dup2(o, STDOUT_FILENO);//redirect the standard out
                        close(o);
		}else if(l){//redirection in
			printf("ARGV[L] = %s\n", argv[l]);
			in = open(argv[l], O_RDONLY);
                        dup2(in, STDIN_FILENO);//redirect the standard in 
                        close(in);
		}else if(p){//redirection pipe
			
			int pipeHole[2];
			pid_t pid2 = fork();
			if(pipe(pipeHole)==-1){
				if(pid2 == 0){
					close(pipeHole[WRITE]);
					dup2(pipeHole[READ], STDIN_FILENO);//redirect to 
				}else{
					close(pipeHole[READ]);
					dup2(pipeHole[WRITE], STDOUT_FILENO);//redirect from
				}
			}else{
				//pipe failure
				puts("pipe failure");
			}
		}
		execvp(fn,argv);//execute commands
	}else{
		if(!a){//wait if &
		int status = 0;
		wait(&status);
		}
	}
	dup2(STDIN_FILENO,stdin_og);//restore std in and out
	dup2(STDOUT_FILENO,stdout_og);
	return;

}


void ch_dir(char* path){
                char** envars = (char**)environ[0];
                char* mypwd = envars[18];
                mypwd += 4;
		
                char s[MAXPATHLEN];
                
                if(!path){
                        printf("%s", getcwd(s, MAXPATHLEN));//if nothing else included in command line
                }else{
			getcwd(s, 100);		//redirect to specified directory
			strcat(mypwd, "/");
                        strcat(mypwd, path);
			mypwd = rmNL(mypwd);	
		
			char* fPath = parsePath(mypwd);
			
			fPath = rmNL(fPath);
                        if(is_dir(fPath)){	// if it is a directory
                                if(chdir(fPath) != 0){//change directory
                                        puts("DIRECTORY NOT FOUND");
                                }else{
                                        if(setenv("PWD",mypwd,0) != 0){//change environment variable to reflect changed directory
                                                puts("ENVIRONMENT CHANGE FAILURE");
                                        }else{
                                        	printf("%s", getcwd(s, MAXPATHLEN));
					}
                                }
                        }else{
                                puts("PROVIDED PATH WAS NOT A DIRECTORY");
                        }
                }


}

int is_dir(char *path){//mostly prof provided code
        struct stat stat_buf;

	if(stat(path, &stat_buf) == -1){
                fprintf(stderr, "stat: %s\n", strerror(errno));
                return 0;
        }
        return S_ISDIR(stat_buf.st_mode);
}

char* rmNL(char* s){//remove the new line character and replace with a null character
	char *pos;
	if ((pos=strchr(s, '\n')) != NULL)
    		*pos = '\0';

	return s;
}
