#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define RAND_MIN 0
#define RAND_MAX 100
#define SIZE 40

//Reed Ceniviva
//CIS2107 Sec:03 Lab:04(1D Arrays)
//Objective: To design and implement functionsto process 1D Arrays.


void fillArray(int array[], int size);
void findWithRange(int array[], int low, int high);
void printArray(int array[], int size);
void printArrayRange(int array[], int low, int high);
void reverseArray(int array[], int size);
void reverseArrayRange(int array[], int low, int high);
void findSequence(int array[], int size, int num1, int num2);

int main(int args,char** argv){

	int array[SIZE];
	fillArray(array,SIZE);
	puts("STARTING ARRAY");
	printArray(array,SIZE);
	printf("\n\n");
	puts("To find the smallest and largest numbers in a range of the array");
	printf("Please Enter the Lower Index: ");
	unsigned int lowerBound;
	scanf("%u",&lowerBound);
	puts("");
	printf("Please Enter the Upper Index: ");
	unsigned int upperBound;
	scanf("%u",&upperBound);
	puts("");
	findWithRange(array,lowerBound,upperBound);
	printf("\n\n\n");
	reverseArray(array,SIZE);
	puts("Array Reversed");
	printArray(array,SIZE);
	printf("\n\n\n");
	puts("To Reverse the Order Between two Indices");
	int low, high;
	printf("Please enter the smaller index: ");
	scanf("%d",&low);
	printf("\nPlease enter the larger index: ");
	scanf("%d",&high);
	puts("");
	reverseArrayRange(array,low,high);
	puts("Array After Reversal");
	printArray(array,SIZE);
	printf("\n\n\n");
	puts("To find a sequance within the array");
	int num1,num2;
	printf("Enter the two (2) number sequence you want to find:");
	scanf("%d %d",&num1,&num2);
	findSequence(array,SIZE, num1, num2);
}

void findSequence(int array[], int size, int num1, int num2){
	int check = 0;
	for(int i = 1; i < size; i++){
		if(array[i] == num1 && array[i+1] == num2){
			printf("Sequence Found, Starting at Index: %d\n", i);
			check = 1;
		}
	}
	if(check == 0){
		puts("sequence not found");
	}
}

void reverseArray(int array[],int size){
	int temp[40];
	puts("Original Array");
	printArray(array,size);
	for(int i = size; i > 0; i--){
		temp[(size+1)-i] = array[i];
	}
	for(int i = 1; i <= size;i++){
		array[i] = temp[i];
	}
}	

void reverseArrayRange(int array[], int low, int high){

	int temp[(high+1)-low];
	puts("Original Order");
	printArrayRange(array,low,high);
	int counter = 1;
	for(int i = high; i >= low; i--){
		temp[counter] = array[i];
		counter++;
	}
	counter = 1;
	for(int i = low; i<= high;i++){
		array[i] = temp[counter];
		counter++;
	}
	puts("");	

}

void printArrayRange(int array[], int low, int high){
	for(int i = low; i <= high;i++){
		if(i < 10){
                        printf("_");
                }
		printf("_%d_",i);
	}
	puts("");
	for(int i = low; i <= high;i++){
		if(array[i] < 10){
			printf(" ");
		}
		if(array[i] == 100){
			printf("%d",array[i]);
		}else{
			printf(" %d ",array[i]);
		}
	}
	puts("");
}

void printArray(int array[], int size){
	for(int i = 1; i <= size;i++){
                if(i == 1){
                        for(int i = 1; i < 10; i++){
                                printf("__%d_",i);
                        }
			puts("_10_");
                }
		if((i-1)%10 == 0){
                        printf("\n");
                }
                if(array[i] < 10){
                        printf(" ");
                }
                if(array[i] == 100){
                        printf("%d",array[i]);
                }else{
                        printf(" %d ",array[i]);
                }

        }
	puts("");
	puts("");
}

void fillArray(int array[], int size){
	srand((unsigned)time(NULL));
	for(size_t i = 1; i <= SIZE; i++){
		array[i] = rand() % (RAND_MAX+1)+(RAND_MIN);
	}
}

void findWithRange(int array[], int low, int high){
	int upper, lower, temp;
	lower = RAND_MAX;
	upper = RAND_MIN;
	for(int i = low; i <= high; i++){
		if(array[i] < lower){
			lower = array[i];
		}
		if(array[i] > upper){
			upper = array[i];
		}
	}
	printArrayRange(array,low,high);
	printf("MAX and MIN values between indices %d and %d:\n",low,high);
	printf("MAX = %d\n",upper);
	printf("MIN = %d\n",lower);
	puts("");
}


