#include <stdio.h>
#include <time.h>//couldnt find anywhere online that listed sleep as being part ofthe time library
#include <unistd.h>
#include <stdlib.h>

#define MILI 1 //couldnt get the sleep method to work, aparently its not miliseconds
#define RAND_MIN 1
#define RAND_MAX 10
#define LENGTH 70

//Reed Ceniviva
//9/30/19
//CIS2107_LAB06_RACE
//Our contenders begin the race at “square 1” of 70 squares. Each square represents a possibleposition along the race course. The finish line is at square 70. The first contender to reach or passsquare 70 is rewarded with a pail of fresh carrots and lettuce. The course weaves its way up the sideof a slippery mountain, so occasionally the contenders lose ground

void race();
void printOuch(int *pos);
void printSpot(int *tPos, int *hPos);
int move(char *C,int *chance);
void printLength();

int main(){

	race();

}

void race(){
	int win = 0;
	printf("ON YOUR MARK,");
	sleep(2.0);
	printf(" GET SET\n");
	sleep(3.0);
	printf("BANG                 !!!!\n");
	puts("AND THEY'RE OFF        !!!!");
	
	for(int i = 0; i < LENGTH;i++){//give the user some refference for how far the finish is
		printf("+");
	}
	puts("");

	int tPos = 0;
	int hPos = 0;
	srand((unsigned)time(NULL));
	int count = 1;
	char tort = 't';
	char hare = 'h';
	while(win == 0){
		int tMove, hMove;
		tMove = 0;
		hMove = 0;
		
		int chance = ((rand() % (RAND_MAX))+(RAND_MIN));
		tMove = move(&tort,&chance);

		chance = ((rand() % (RAND_MAX))+(RAND_MIN));
		hMove = move(&hare,&chance);

		//had difficulties with getting +4000 back sometimes
		//likely fixed the problem but still need the functionality of the statements
		if(hMove + hPos <= 0||hMove + hPos > 2000){hPos = 0;}else{hPos += hMove;}
		if(tMove + tPos <= 0||tMove + tPos > 2000){tPos = 0;}else{tPos += tMove;}
		
		if(tPos == hPos && hPos < LENGTH -1){//OUCH condition
			printOuch(&tPos);
		}else{
			printSpot(&tPos,&hPos);
		}

		//puase for a second to provide view of race
		sleep(1);

		if(tPos >= (LENGTH-1) || hPos >= (LENGTH -1)){
			if(tPos == hPos){			
				puts("ITS A TIE!");
			}else if(tPos >= (LENGTH -1)){
				puts("TORTOISE WINS!!! YAY!!!");
			}else{
				puts("Hare wins. Yuch.");
			}
			win = 1;
                }
		count++;//used for printing the length of the race every couple of lines
		if(count%10 == 0){
			printLength();
		}
	}
}

void printLength(){
	for(int i = 0; i < LENGTH;i++){//give the user some refference for how far the finish is
                printf("+");
        }
	puts("");
}

void printOuch(int *pos){
	for(int i =0; i < LENGTH; i++){
		if(i == *pos){
			printf("OUCH!!");
		}else{
			printf(" ");
		}
	}
	puts("");
}

void printSpot(int *tPos, int *hPos){
	for(int i = 0; i < LENGTH; i++){
		if(i == *tPos){
			printf("T");
		}else if(i == *hPos){
			printf("H");
		}else{
			printf(" ");
		}
	}
	puts("");
}

int move(char *C, int *chance){
	if(*C == 't'){//rather than make two functions for the character movement
		if(*chance <= 5){
			return 3;
		}else if(*chance > 5 && *chance <= 7){
			return (-6);
		}else if(*chance > 7 && *chance <= 10){
			return 1;
		}else{
			puts("SOMETHING HAS GONE WRONG, TORTUISE DID NOT MOVE");
			return 0;
		}
	}else if(*C == 'h'){//tried to do a switch statement for this but ran into issues with the randum numbers
		if(*chance <= 2){
			return 0;
		}else if(*chance > 2 && *chance <= 4){
			return 9;
		}else if(*chance > 4 && *chance <= 5){
			return (-12);
		}else if(*chance > 5 && *chance <= 8){
			return 1;
		}else if(*chance > 8 && *chance <= 10){
			return (-2);
		}else{
			puts("SOMETHING HAS GONE WRONG, HARE CHANCE NOT VALID");
			return 70;
		}
		
	}
}

