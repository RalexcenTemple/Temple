#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

//Reed Ceniviva
//9/26/19
//CIS2017_Lab05_2DArays & Arrays of Pointers to Functions

#define STUDENTS 3
#define EXAMS 4

void minimum(int stud, int exam, int arr[stud][exam]);
void maximum(int stud, int exam, int arr[stud][exam]);
void average(int stud, int exam, int arr[stud][exam]);
void printArray(int stud, int exam, int arr[stud][exam]);


int main(int args, char** argv){


	void (*f[4])(int,int,int[STUDENTS][EXAMS])={minimum,maximum,average,printArray};	
	int row, col;
	row = STUDENTS;
	col = EXAMS;
	int arr[STUDENTS][EXAMS] = {{77,68,86,73},{96,87,89,78},{70,90,86,81}} ;
	//arr = {{77,68,86,73},{96,87,89,78},{70,90,86,81}};
	
	int choice = 5;
	while(choice != 0){
		choice = 5;
		puts("________________________");
		puts("Please Select and Action");
        	puts("1) Find Lowest Grade");
        	puts("2) Find Highest Grade");
        	puts("3) Fine Average Grade");
        	puts("4) Print Out The Grades");
		puts("0) Exit Program");
        	printf("Enter Choice: ");
		scanf("%d",&choice);
		switch(choice){
			case 1:
				puts("________________________");
				(*f[0])(STUDENTS,EXAMS,arr);
				break;
			case 2:
				puts("________________________");
				(*f[1])(STUDENTS,EXAMS,arr);
				break;
			case 3:
				puts("________________________");
				(*f[2])(STUDENTS,EXAMS,arr);
				break;
			case 4:
				puts("________________________");
				(*f[3])(STUDENTS,EXAMS,arr);
				break;
			case 0:
				puts("Exiting Program");
				break;
			default:
				puts("Improper Selection, Program will terminate");
				choice = 0;
		}
	}
}

void minimum(int stud, int exam, int arr[stud][exam]){
	int low = 200;
	for(int i = 0; i < stud;i++){
		for(int j =0; j < exam; j++){
			if(low > arr[i][j]){
				low = arr[i][j];
			}
		}
	}
	printf("The Lowest Grade is: %d\n", low);
}

void maximum(int stud, int exam, int arr[stud][exam]){
	int low = 0;
        for(int i = 0; i < stud;i++){
                for(int j =0; j < exam; j++){
                        if(low < arr[i][j]){
                                low = arr[i][j];
                        }
                }
        }
        printf("The Highest Grade is: %d\n", low);

}

void average(int stud, int exam, int arr[stud][exam]){
	printf("What student would you like to find the average grade of: ");
	int total = 0;
	int choice;
	scanf("%d",&choice);
	puts("");
	if(choice < stud && choice >= 0){ 
		for(int i = 0; i < exam; i++){
			total += arr[choice][i];
		}
		double avg = ((double)total)/exam;
		printf("The average grade for student %d is %.2f\n",choice,avg);
		puts("YES = 1 | NO = 0");
		printf("Would you like to find the average of a different student: ");
		int cont = 0;
		scanf("%d", &cont);
		puts("");
		if(cont == 1){
			average(stud,exam,arr);
		}
	}else{
		puts("Invalid selection, action canceled");
	}
}

void printArray(int stud, int exam, int arr[stud][exam]){
	puts("                 [0]  [1]  [2]  [3]");
	for(int i = 0; i < stud;i++){
		printf("\nStudentGrades[%d] ",i);
		for(int j = 0;j< exam;j++){
			printf("%-5d",arr[i][j]);
		}
	}
	puts("");
}


