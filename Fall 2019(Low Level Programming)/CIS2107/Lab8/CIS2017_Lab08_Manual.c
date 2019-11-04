/*
 * Name:	Reed	Ceniviva	 
 * Section:	03
 * Date:	10/25/2019 	
 * Lab:  	CIS2107_Lab08_Manual 
 * Goal: 	To design and implement functions to process characters and strings.
 */


#include <stdio.h>
#include <memory.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

//functions
void * upperLower(const char * s);
int convertStrtoInt(const char *s1, const char *s2, const char *s3, const char *s4);
float convertStrtoFloat(const char *s1, const char *s2, const char *s3, const char *s4);
void compareStr(const char *s1, const char *s2);
void comparePartialStr(const char *s1, const char *s2, int n);
void randomize(void);
int tokenizeTelNum(char *num);
void reverse(char *text);
int countSubstr (char * line, char * sub);
int countChar (char * line, char c);
int countWords(char *string);
void countAlpha(char *string);
void startsWithB(char *string[]);
void endsWithed(char *string[]);

int main() {

    //random generator
    srand(time(NULL));

    //test for upperLower
    const char test[] = "This iS A Test";
    upperLower(test);

    //test for convertStrtoInt
    printf("\n\nThe sum of 4 strings is: %d", convertStrtoInt("3", "4", "5", "6"));

    //test for convertStrtoFloat
    printf("\n\nThe sum of 4 strings is: %.2f", convertStrtoFloat("3.5", "4.5", "5.5", "6.5"));

    //test for compareStr
    compareStr("Test1", "Test2");

    //test for comparePartialStr
    comparePartialStr("Test1", "Test2", 4);

    //test for randomize
    randomize();

    //test for tokenize number
    char str[] = "(267) 436-6281";
    tokenizeTelNum(str);

    //test for reverse
    char line[] = "Hello world";
    reverse(line);

    //test for countSubstr
    char *line1 = "helloworldworld";
    char *substring = "world";
    printf("\n\nNumber of Substrings %s inside %s: %d\n", substring, line1, countSubstr(line1, substring));

    //test for countChar
    char w = 'w';
    printf("\nNumber of character %c inside %s: %d\n", w, line1, countChar(line1, w));

    //test for countAlpha
    char str1[] = "Hello it's me.";
    countAlpha(str1);

    //test for countWords
    char countstring[] = "hello world!";
    printf("\n\nNumber of words in string is: %d\n", countWords(countstring));

    //test for startsWithB
    char *series[] = {"bored", "hello", "Brother", "manual", "bothered"};
    startsWithB(series);

    //test for endsWithed
    endsWithed(series);

}

// 1.(Displaying Strings in Uppercase and Lowercase) 
void * upperLower (const char * s) {
	char *sPtr;
	sPtr = &s[0];
	while(*sPtr != '\0'){
		printf("%c", toupper(*sPtr));
		++sPtr;	
	}
	puts("");
	sPtr = &s[0];
	while(*sPtr != '\0'){
		printf("%c", tolower(*sPtr));
		++sPtr; 
	}
	puts("");
}

// 2.(Converting Strings to Integers for Calculations) 
int convertStrtoInt(const char *s1, const char *s2, const char *s3, const char *s4) {
	int i1,i2,i3,i4;
	char* e1,e2,e3,e4;//didnt quite understand the second argument until the following function
	i1 = (int)strtod(s1,e1);
	i2 = (int)strtod(s2,e2);
	i3 = (int)strtod(s3,e3);
	i4 = (int)strtod(s4,e4);
	int sum = i1 + i2 + i3 + i4;
	return sum;    
}

//3.(Converting Strings to Floating Point for Calculations) 
float convertStrtoFloat(const char *s1, const char *s2, const char *s3, const char *s4) {
	float f1,f2,f3,f4;
	char* e1,e2,e3,e4;
	f1 = (float)strtod(s1,NULL);
	f2 = (float)strtod(s2,NULL);
	f3 = (float)strtod(s3,NULL);
	f4 = (float)strtod(s4,NULL);   
	float sum = f1 + f2 + f3 + f4;
	return sum;
}

//4.(Comparing Strings) 
void compareStr(const char *s1, const char *s2) {
	int info = strcmp(s1,s2);
	if(info < 0){
		printf("%s < %s\n",s1,s2);
	}else if(info == 0){
		printf("%s = %s\n",s1,s2);
	}else{
		printf("%s > %s\n",s1,s2);
	} 
	puts("");
}

//5.(Comparing Portions of Strings) 
void comparePartialStr(const char *s1, const char *s2, int n) {
	int info = strncmp(s1,s2,n);
	printf("Comparison of first %d chars: ",n);
	if(info < 0){
                printf("%s < %s\n",s1,s2);
        }else if(info == 0){
                printf("%s = %s\n",s1,s2);
        }else{
                printf("%s > %s\n",s1,s2);
        }
	puts("");
}

//6.(Random Sentences)
void randomize(void) {
	char arti[5][5] = {"the","a","one","some","any"};
	char noun[5][5] = {"boy","girl","dog","town","car"};
	char verb[5][8] = {"drove","jumped","ran","walked","skipped"};
	char prep[5][8] = {"to","from","over","on","under"}; 
   	int sentSize = 5 + 1 + 5 + 1 + 8 + 1 + 8 + 1 + 5 + 1 + 5 + 1;//max word size in each array as they happen with spaces and period
	char sent[sentSize]; 
	for(int i = 0; i < 20; i++){
		strcpy(sent, arti[(rand()%5)]);
		char first = sent[0];
		sent[0] = toupper(first);//would have used a for->6 loop if the capitalization and period at the end weren't included
		strcat(sent, " ");
		strcat(sent, noun[(rand()%5)]);
		strcat(sent, " ");
		strcat(sent, verb[(rand()%5)]);
		strcat(sent, " ");
		strcat(sent, prep[(rand()%5)]);
		strcat(sent, " ");	
		strcat(sent, arti[(rand()%5)]);
		strcat(sent, " ");
		strcat(sent, noun[(rand()%5)]);
		strcat(sent, ".");
		printf("%s\n",sent);
	}
	puts("");
}

//7.(Tokenizing Telephone Numbers) 
int tokenizeTelNum(char *num) {
	printf("%s\n",num);
	char elim[] = {"()-"};//symbols to token by
	char *tokenPtr = strtok(num,elim);
 	int code = strtod(tokenPtr,NULL);
	tokenPtr = strtok(NULL,elim);
	int f3 = strtod(tokenPtr,NULL);
	tokenPtr = strtok(NULL,elim);
	int l4 = strtod(tokenPtr,NULL);
	f3 = f3*10000;//leave space for the last four digits
	int L7 = f3 + l4;//get the last 7 digits
	long l7 = (long)(L7);//convert to long
	printf("%d %ld\n",code,l7);
  
}

//8.(Displaying a Sentence with Its Words Reversed) 
void reverse(char *text) {
	char* buffer[128][128];
	char* temp;// size of what is to be printed
	char* tokenPtr = strtok(text," ");
	int count = 0;
	while(tokenPtr != NULL){
		strcpy(buffer[count],tokenPtr);
		count++;
		tokenPtr = strtok(NULL," ");
	}
	puts("");
	printf("%d",count);	
	puts("");
	for(int i = count - 1; i >= 0;i--){
		printf("%s ",buffer[i]);
	}
	puts("");
}

//9.(Counting the Occurrences of a Substring) 
int countSubstr (char * line, char * sub) {
	int lineL = strlen(line);
	int subL = strlen(sub);
	int count = 0;
	for(int i = 0; i < lineL; i++){
		int j = 0;
		int match = 0;
		while(line[i]==sub[j]){
			match++;
			i++;
			j++;
			if(match == subL){
				count++;
				match = 0;
				j = 0;
			}
		}
	}
	return count;
}

//10.(Counting the Occurrences of a Character) 
int countChar (char *line, char c) {
	int lineL = strlen(line);
	int count = 0;
	for(int i = 0; i < lineL ; i++){
		if(line[i] == c){
			count++;
		}
	}
	return count;
}


//11.(Counting the Letters of the Alphabet in a String) 
void countAlpha(char *string){
        char alpha[] = "abcdefghijklmnopqrstuvwxyz";
        int sLen = strlen(string);
        int aLen = strlen(alpha);
	for(int i = 0 ; i < aLen;i++){
		printf("%c ",alpha[i]);
		int count = 0;
		for(int j = 0; j < sLen; j++){
			if(alpha[i]==(tolower(string[j]))){
				count++;
			}
		}
		printf("| %d",count);
		printf("\n");
	}
}

//12.(Counting the Number of Words in a String) 
int countWords(char *string) {
	char *tokenPtr = strtok(string, " \n"); 
   	int count = 0;
	while(tokenPtr != NULL){
		count++;
		tokenPtr = strtok(NULL, " \n");
	}
	return count;
}

//13.(Strings Starting with "b") 
void startsWithB(char *string[]) {
	size_t fSize = (sizeof(string)/sizeof(string[0]));
	for(int i = 0; i < 5;i++){
		if(string[i][0] == 'b' || string[i][0] == 'B'){
			printf("B-WORD: %s\n",string[i]);
		}
	}
}

//14.(Strings Ending with "ed") 
void endsWithed(char *string[]) {
	size_t fSize = (sizeof(string)/sizeof(string[0]));
	for(int i = 0; i < 5; i++){
		int sLen = strlen(string[i]);
		if(string[i][sLen-1] == 'd' && string[i][sLen-2] == 'e'){
			printf("%s \n", string[i]);
		}
	}

}
