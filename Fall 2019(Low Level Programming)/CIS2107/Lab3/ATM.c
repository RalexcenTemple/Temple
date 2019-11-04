#include <stdio.h>
#include <stdlib.h>
#include <math.h>//used this to deal with any decimal values entered 
		//but then realized that is not an issue i need to worry about
//Reed Ceniviva 10-9-19
//Lab 3
//ATM program
void ATM();
void balance(unsigned int balance);
unsigned int cashWithdrawal(unsigned int balance, unsigned int withdrawal);
unsigned int cashDeposit(unsigned int deposit);
void quit(unsigned int actions);
unsigned int getPin(unsigned int PIN);
void reciept();



int main(int argc, char** argv){
	unsigned int actions = 1;
	unsigned int balance = 0;
	unsigned int pin = 5555;
	
	puts("      Welcome to Reeds ATM Service     ");
	int userPin = getPin(5555);
	ATM();
}

double checkNum(double num){//didnt read closely and tried to handle impropper inputs 
	double flatNum;
	flatNum = ceil(num);
	if(((num - flatNum) != 0) || num == 0){
		return 0;
	}else{
		return flatNum;
	}
}

void ATM(){
	unsigned int actions = 0;
	unsigned int Balance = 0;
	unsigned int withdrawal = 0;
	unsigned int deposit = 0;
	while(1){//continues till user chooses to quit
		puts("_______________________________________");
		puts("   Please Select a Transaction Option  ");
		puts("1)Balance             Cash Withdrawal(2");
        	puts("3)Cash Deposite                  Quit(4");
        	puts("        Please Select an Action        ");
		puts("---------------------------------------");
        	unsigned int Option;
        	scanf("%u",&Option);
		//int Option = (int)checkNum(choice);
		if(Option == 0 || Option > 4){
			puts("*****Please Enter A Valid Option*****");
		}else{
		//int Option = (int)option;
		//Due to the scope of a switch statement, the 
		unsigned int thisWithdrawal;
		unsigned int thisDeposit;
        	switch(Option){
                	case 1:
                	balance(Balance);
			actions++;
                	break;
                	case 2:
			thisWithdrawal = cashWithdrawal(Balance,withdrawal);
                	if(thisWithdrawal != 0){//if no transaction was performed
			withdrawal += thisWithdrawal;
			Balance -= thisWithdrawal;
			actions++;
			}
                	break;
                	case 3:
			thisDeposit = cashDeposit(deposit);
			if(thisDeposit != 0){//if no transaction was performed
                	deposit += thisDeposit;
			Balance += thisDeposit;
			actions++;
			}
                	break;
                	case 4:
                	reciept();
                	quit(actions);
                	break;
                	defualt:
                	puts("Invalid Input");
                	//reciept();
                	quit(actions);
                	break;
        		}
			reciept();
		}

	}
}

void balance(unsigned int balance){
	//print balance
	printf("\nThe current balance of your account is: %u \n\n",balance);
}

unsigned int cashWithdrawal(unsigned int balance, unsigned int withdrawal){
	//withdrawal cant go over 1000
	//balance cant go bellow 0
	//return the amount withdrawn
	if(balance == 0){
		puts("");
		puts("You have no Money to withdrawal");
		return 0;
	}else{
		unsigned int MAX = 1000;
		printf("Please enter the amount you would like to wothdrawal: ");
		double checkAmount = 0;
		scanf("%lf",&checkAmount);
		unsigned int amount = (unsigned int)checkNum(checkAmount);
		if(amount == 0){
			puts("You have chosen to not withdrawal money");
			return 0;
		}else if(amount%20 == 0){
			if((amount+withdrawal) > MAX){
				puts("Please enter an amount bellow your daily limit");
				puts("Enter 0 if you cannot make a deposit at this time");
				return cashWithdrawal(balance, withdrawal);
			}else if(amount > balance){
				puts("Please enter an amount bellow your balance");
				puts("Enter 0 if you cannot make a deposit at this time");
				return cashWithdrawal(balance, withdrawal);
			}else{
				return amount;
			}
		}else{
			puts("Please enter an amount that can be created using $20 bills");
			puts("Enter 0 if you cannot make a deposit at this time");
			puts("");
			return cashWithdrawal(balance,withdrawal);
		}
	}
}

unsigned int cashDeposit(unsigned int deposit){
	//deposite cant go above 10,000
	//return the amount deposited
	int MAX = 10000;
	printf("Please enter the amount you would like to deposite: ");
	double checkAmount = 0;
	scanf("%lf",&checkAmount);
	unsigned int amount = (unsigned int)checkNum(checkAmount);
	if(amount == 0){
		puts("You have chosen to not make a deposite");
		return 0;
	}else{//since any bills may be deposited the amount could be any number
		if((amount+deposit) > MAX){
			puts("This amount is over your daily limit to deposit");
			puts("Enter 0 if you cannot make a deposit at this time");
			puts("");
			return cashDeposit(deposit);
		}else{
			return amount;
		}
	}
}

void quit(unsigned int actions){//implement exit(0) to leave the program
	puts("Thank you for using Reed ATM Services");
	//tell the user the number of transaction they performed
	printf("You completed %u transaction(s) with us today \n",actions);
	exit(0);
}

unsigned int getPin(unsigned int PIN){//recieve and check the pin provided by the user
	for(unsigned int i = 0; i < 3;i++){
		unsigned int userPin = 0;
		printf("Please Enter your pin to continue: ");
		scanf("%d",&userPin);
		if(userPin != PIN){
			puts("Incorrect PIN, please try again");
			printf("You have %d more tries\n",2-(i));
		}else{
			return(userPin);
		}
	}
	puts("you have failed to enter a correct pin and will be logged out");
	quit(0);

}

void reciept(){//"all actions let the user "recieve" a reciept if choosen
	int choice = 0;
	puts("");
	puts("would you like a reciept for your transactions?");
	puts("1) Yes");
	puts("2) No");
	scanf("%d",&choice);
	if(choice == 1){
		//tried to add wait() statements between the points
		//to add a little more flare
		//this ended up being more trouble than was worth
		printf("Printing Reciept");
		printf(".");
		printf(".");
		printf(".\n");
	}
}
