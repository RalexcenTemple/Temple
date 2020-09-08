#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>


int RANDSIZE = 9;
void mult(int N, int method);
void printa(int N, double X[][N]);
double ijk(int N, double A[][N], double B[][N], double C[][N]);
double ikj(int N, double A[][N], double B[][N], double C[][N]);
double jik(int N, double A[][N], double B[][N], double C[][N]);
double jki(int N, double A[][N], double B[][N], double C[][N]);
double kij(int N, double A[][N], double B[][N], double C[][N]);
double kji(int N, double A[][N], double B[][N], double C[][N]);



int main(int argc, char* argv[]){

	int N = 0;
	int method = 0;	

	if(argc > 1){
		//printf("Your Input Was: %d \n ",atoi(argv[1]));
		N = atoi(argv[1]);
		if(argc > 2){
			method = atoi(argv[2]);
			//printf("Method Chosen: ");
			if(method > 5){
				//printf("IJK\n");
			//}else if(method == 1){
				//printf("IKJ\n");
			//}else if(method == 2){
				//printf("JIK\n");
			//}else if(method == 3){
				//printf("JKI\n");
			//}else if(method == 4){
				//printf("KIJ\n");
			//}else if(method == 5){
				//printf("KJI\n");
				printf("Incorrect Input, Program will Exit\n");
			}
		}
	}
	if(method <= 5){
		mult(N, method);
	}
}

void mult(int N, int method){

	double (*A)[N] = malloc(N * sizeof(*A));
	double (*B)[N] = malloc(N * sizeof(*B));
	double (*C)[N] = malloc(N * sizeof(*C));
	double time = 0;

	for(int i = 0; i < N;i++){
		for(int j = 0; j < N;j++){
			A[i][j] = rand()%RANDSIZE;
			B[i][j] = rand()%RANDSIZE;
			C[i][j] = 0;
		}
	}

	//printf("MATRIX A\n");
	//printa(N, A);
	//printf("MATRIX B\n");
	//printa(N, B);
	//printf("MATRIX C\n");
        //printa(N, C);
	if(method == 0){
		time = ijk(N, A, B, C);	
	}else if(method == 1){
		time = ikj(N, A, B, C);	
	}else if(method == 2){
		time = jik(N, A, B, C);
	}else if(method == 3){
		time = jki(N, A, B, C);
	}else if(method == 4){
		time = kij(N, A, B, C);
	}else if(method == 5){
		time = kji(N, A, B, C);
	}

	//printf("RESULT\n");
        //printa(N, C);
	printf("Length,%d,Time,%f,Method,%d\n",N, time,method);

	//FILE *fp = fopen("output.csv","a");
	//if(fp == NULL){
	//	printf("ERROR OPENING OUTPUT FILE\n");
	//}else{
	//	fprintf(fp,"Length,%d,Time,%f,Method,%d\n",N, time,method);
	//}
	//fclose(fp);

}

//USED FOR DEBUGGING
void printa(int N, double X[][N]){

	for(int i = 0; i < N; i++){
		for(int j = 0; j < N; j++){
			printf(" %.1f ", X[i][j]);
		}
		printf("\n");
	}

}

double ijk(int N, double A[][N], double B[][N], double C[][N]){
	clock_t t;
	t = clock();
	for(int i = 0; i < N; i++){
		for(int j = 0; j < N; j++){
			for(int k = 0; k < N; k++){
				C[i][j] += A[i][k]*B[k][j];
			}
		}
	}
	t = clock() - t;
	double totalTime = ((double)t)/CLOCKS_PER_SEC;
	return totalTime;
}

double ikj(int N, double A[][N], double B[][N], double C[][N]){
        clock_t t;
        t = clock();
        for(int i = 0; i < N; i++){
                for(int k = 0; k < N; k++){
                        for(int j = 0; j < N; j++){
                                C[i][j] += A[i][k]*B[k][j];
                        }
                }
        }
        t = clock() - t;
        double totalTime = ((double)t)/CLOCKS_PER_SEC;
        return totalTime;
}

double jik(int N, double A[][N], double B[][N], double C[][N]){
        clock_t t;
        t = clock();
        for(int j = 0; j < N; j++){
                for(int i = 0; i < N; i++){
                        for(int k = 0; k < N; k++){
                                C[i][j] += A[i][k]*B[k][j];
                        }
                }
        }
        t = clock() - t;
        double totalTime = ((double)t)/CLOCKS_PER_SEC;
        return totalTime;
}

double jki(int N, double A[][N], double B[][N], double C[][N]){
        clock_t t;
        t = clock();
        for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                        for(int i = 0; i < N; i++){
                                C[i][j] += A[i][k]*B[k][j];
                        }
                }
        }
        t = clock() - t;
        double totalTime = ((double)t)/CLOCKS_PER_SEC;
        return totalTime;
}

double kij(int N, double A[][N], double B[][N], double C[][N]){
        clock_t t;
        t = clock();
        for(int k = 0; k < N; k++){
                for(int i = 0; i < N; i++){
                        for(int j = 0; j < N; j++){
                                C[i][j] += A[i][k]*B[k][j];
                        }
                }
        }
        t = clock() - t;
        double totalTime = ((double)t)/CLOCKS_PER_SEC;
        return totalTime;
}

double kji(int N, double A[][N], double B[][N], double C[][N]){
        clock_t t;
        t = clock();
        for(int k = 0; k < N; k++){
                for(int j = 0; j < N; j++){
                        for(int i = 0; i < N; i++){
                                C[i][j] += A[i][k]*B[k][j];
                        }
                }
        }
        t = clock() - t;
        double totalTime = ((double)t)/CLOCKS_PER_SEC;
        return totalTime;
}

