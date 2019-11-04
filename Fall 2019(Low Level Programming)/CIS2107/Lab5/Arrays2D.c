#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <stdbool.h>

#define RAND_MIN 1
#define RAND_MAX 99

//Reed Ceniviva

void max(size_t row, size_t col, int arr[row][col]);
void rowSum(size_t row, size_t col, int arr[row][col]);
void columnSum(size_t row, size_t col, int arr[row][col]);
bool isSquare(size_t row, size_t col);
void displayOutputs(size_t row, size_t col, int arr[row][col]);

int main(int args, char** argv){

	puts("Let's create a 2Dim array!");
	printf("\n");

	printf("      How many rows? ");
	int row;
	scanf("%d",&row);
	puts("");
	printf("      How many columns? ");
	int col;
	scanf("%d",&col);
	puts("");

	int arr[row][col];//create array

	for(int i = 0; i < row; i++){
		for(int j =0; j < col;j++){
			printf("\n      enter [%d][%d]: ",i,j);
			scanf("%d",&arr[i][j]);//place user values into matrix
		}
	}
	puts("");
	displayOutputs(row, col, arr);
	max(row, col, arr);
	rowSum(row, col, arr);
	columnSum(row, col, arr);
	if(isSquare(row, col)){
		puts("This is a square array.\n");
	}else{  puts("This is not a sqaure array.\n");}
}

void max(size_t row, size_t col, int arr[row][col]){
	int temp = 0;//placeholder for the max value found
	for(int i = 0; i < row; i++){
		for(int j = 0; j < col; j++){
			if(temp < arr[i][j]){
				temp = arr[i][j];
			}
		}
	}
	
	printf("The max value in this 2D Array is: %d\n", temp);

}

void rowSum(size_t row, size_t col, int arr[row][col]){

	int sum = 0;
	printf("What Row would you like to sum: ");
	int rowC;
	scanf("%d",&rowC);
	if(rowC >= row || rowC < 0){
		puts("Please enter a valid row");
		rowSum(row,col,arr);
	}else{
		for(int i = 0; i < col; i++){//only going through a single row
			sum += arr[rowC][i];
		}
		printf("Sum of row %d = %d\n",rowC,sum);
		puts("Yes = 1 | No = 0");
		printf("Would you like to find the sum of a different row: ");
		int choice = 0;
		scanf("%d", &choice);
		puts("");
		if(choice == 1){
			rowSum(row,col,arr);
		}//no need to check the else, if wrong input or 0 is entered the function ends anyway
	}
}

void columnSum(size_t row, size_t col, int arr[row][col]){
	int sum = 0;
        printf("What Column would you like to sum: ");
        int colC;
        scanf("%d",&colC);
        if(colC >= col || colC < 0){
                puts("Please enter a valid column");
                columnSum(row,col,arr);
        }else{
                for(int i = 0; i < row; i++){//only going through a single col
                        sum += arr[i][colC];
                }
                printf("Sum of column %d = %d\n",colC,sum);
                puts("Yes = 1 | No = 0");
                printf("Would you like to find the sum of a different column: ");
                int choice = 0;
                scanf("%d", &choice);
                puts("");
                if(choice == 1){
                        columnSum(row,col,arr);
                }//no need to check the else, if wrong input or 0 is entered the function ends anyway
        }

}

bool isSquare(size_t row, size_t col){
	if(row == col){
		return true;
	}else{
		return false;
	}
}

void displayOutputs(size_t row, size_t col, int arr[row][col]){
	for(int i = 0; i < row; i++){
			printf("[");
		for(int j = 0; j < col; j++){
			printf(" %d ", arr[i][j]); 
		}
			printf("]\n");
	}

}


