#include <stdio.h>
#include <stdlib.h>

//student: Reed Ceniviva
//Date: 5-Sep-19
//Class: CIS 2107
//Homework: Lab 02 Paycheck
//Write a Cprogram called paycheckto calculate the paycheck for a Temple employeebased on the hourlySalary, weeklyTime (working for maximum 40 hours) and overtime (working for more than 40 hours).

unsigned int getENum(unsigned int ENum);
float getHSal(float HSal);
float getWTime(float WTime);
void printE(unsigned int num, float sal, float time);

int main(int argc, char** argv){
	puts("Welcome to \"TEMPLE HUMAN RESOURCES\"");
	puts("");
	unsigned int ENum;
	float HSal;
	float WTime;
	ENum = getENum(ENum);
	HSal = getHSal(HSal);
	WTime = getWTime(WTime);
	puts("\t==============================");
	printE(ENum,HSal,WTime);
	puts("\t==============================");
	puts("Thank you for using \"TEMPLE HUMAN RESOURCES\"");
}

void printE(unsigned int num, float sal, float time){
	printf("\tEmployee #: %u\n",num);
	printf("\tHourly Salary: $%.1f\n", sal);
	printf("\tWeekly Time: %.1f\n", time);
	unsigned int OT = 0;
	if(time > 40){
		OT = time - 40;
		time = 40;
	}
	float RegPay = (time * sal);
	float OTPay = (OT * (float)(sal * 1.5));
	printf("\tRegular Pay: $%.1f\n",RegPay);
	printf("\tOvertime Pay: $%.1f\n",OTPay);
	float netPay = (RegPay + OTPay);
	printf("\tNet Pay: $%.1f\n",netPay);
}

unsigned int getENum(unsigned int ENum){
	printf("\tEnter Employee Number: ");
	int temp;
	int scanNum = scanf("%d",&temp);
	int buff = 0;
	while((getchar()) != '\n'){
		buff++;
	}
	if(scanNum != 1 || temp < 0 || buff > 0){
		puts("\tThis is not a valid Employee Number.");
		puts("\tPlease try again");
		unsigned int temp;
		return getENum(temp);
	}else{
		ENum = temp;
		return ENum;
	}	
}

float getHSal(float HSal){
	printf("\tEnter Hourly Salary: ");
	int scanNum = scanf("%f", &HSal);
	if(scanNum==0 || HSal < 0){
		puts("\tThis is not a valid hourly salary amount.");
		puts("\tPlease try again");
		float temp;
		return getHSal(temp);
	}else{
		return HSal;
	}
}

float getWTime(float WTime){
	printf("\tEnter Weekly Time: ");
	int scanNum = scanf("%f",&WTime);
	if(scanNum == 0 || WTime < 0){
		puts("\tThis is not a weekly time.");
		puts("\tPlease Try again");
		float temp;
		return getWTime(temp);
	}else{
		return WTime;
	}
}
