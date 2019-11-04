#include <stdio.h>
#include <stdlib.h>
//Student:Reed Ceniviva
//Date:05-Sep-19
//CIS 2107
//Lab 02 Revenue
//Write a Cprogram called revenueto calculate therevenue from a sale based on the unit price and quantity of a product input by the user

void printT(float price, unsigned int quant);
float getPrice(float price);
unsigned int getQuantity(unsigned int quant);

int main(int argc, char** argv){
	puts("Welcome to \"Temple\" store");
	puts("");
	float price;
	price = getPrice(price);
	unsigned int quant;
	quant = getQuantity(quant);
	puts("");
	printT(price,quant);
	puts("");
	puts("Thank you for using \"Temple\" store");
}

float getPrice(float price){
	float temp;
	printf("Enter item price: ");
	int scanNum = scanf("%f",&price);
	if(scanNum != 1 || price < 0){
		puts("\tPlease enter a valid price");
		puts("\tPlease try again");
		return getPrice(temp);
	}else{
		return price;
	}
}

unsigned int getQuantity(unsigned int quant){
	int temp;
	printf("Enter quantity: ");
	int scanNum = scanf("%u",&temp);
	if(scanNum != 1 || temp < 0){
		puts("\tPlease enter a valid quantity");
		puts("\tPlease try again");
		return getQuantity(quant);
	}else{
		quant = temp;
		return quant;
	}
}

void printT(float price, unsigned int quant){
	float disc = 0.0;
	if(quant >= 150){
		disc = 25.0;
	}else if(quant >= 100){
		disc = 15.0;
	}else if(quant >= 50){
		disc = 10.0;
	}
	//discount has been set correctly
	float totalPrice = ((float)quant * price);
	float totalDisc = (totalPrice*(disc/100));
	float Total = totalPrice - totalDisc;
	//all variables have been obtained
	printf("\tThe item price is: $%.1f\n",price);
	printf("\tThe order is: %u item(s)\n",quant);
	printf("\tThe cost is: $%.1f\n",totalPrice);
	printf("\tThe discount is: %.1f%%\n",disc);
	printf("\tThe discount amount is: $%.1f\n",totalDisc);
	printf("\tThe total is: $%.1f\n",Total);
}
