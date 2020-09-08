import java.util.Scanner;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class sort {
	static Scanner s = new Scanner(System.in);
	public static String time;
	public static int totalTime = 0;

	
	public static void main(String[] args) {
		int again = 1;
		sol("Bubble Sort Program");				//System.out.println
		sol("By: Reed Ceniviva");
		//while(again > 0) {
		sol("");
		int num = 1;
		int parts = 0;
		if(args.length == 1){
			num = Integer.parseInt(args[0]);
			int[] nums = creatNums(num);
			int[] sortedNums = bubbleSort(nums, parts);
		}else if(args.length > 1){
			num = Integer.parseInt(args[0]);
			parts = Integer.parseInt(args[1]) + 1;
			int[] nums = creatNums(num);
			int numParts = num/parts;
			int[][] partNums = new int[parts][numParts];
			for(int i = 0; i < parts; i++){
				for(int j = 0; j < numParts; j++){
					partNums[i][j] = nums[j + (i * numParts)];
				}
				bubbleSort(partNums[i], i + 1); //Partition is not 0 till last call
			}
			for(int m = 0; m < parts; m++){ //combine arrays
				for(int n = 0; n < numParts; n++){
					nums[n + (m * numParts)] = partNums[m][n];
				}
			}	
			bubbleSort(nums, 0);
		}else{
			num = getNum();
			int[] nums = creatNums(num);
			int[] sortedNums = bubbleSort(nums,parts);
		}
		//int[] nums = creatNums(num);			//create random number array
		//sol("Unsorted");
		//sopa(nums);								//System out print array
		//int[] sortedNums = bubbleSort(nums, parts);	//sort random number array
		//sol("Sorted");
		//sopa(sortedNums);
		sol(time);								//print time
		//sop("(yes = 1 | no = 0) Run again?: ");	//System out print
		//	again = cont();						//check if user wants to run another test
		//}
		s.close();
	}


	//All run information is dumped into output.txt
	//Sort Time and Array length are written to data.csv 
	public static int[] bubbleSort(int[] nums, int part) {//https://www.geeksforgeeks.org/bubble-sort/
		int length = nums.length;
		double Microtime = 0;
		try (FileWriter fw = new FileWriter("output.txt", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw)){
			//export initial array information
			fpl("Partition : " + part, pw);
			fpl("(T)Array Length: " + length, pw);
			fpl("||| UNSORTED |||", pw);
			fpa(nums, pw);
		
		//SORTING IN PROGRESS
		long start = System.nanoTime();
		for(int i = 0; i < length-1; i++) {
			for(int j = 0; j < length-1-i; j++) {//subtract i as the last value is for sure correct at the end of each run
				if(nums[j] > nums[j+1]) {//move larger number up
					int temp = nums[j];
					nums[j] = nums[j+1];
					nums[j+1] = temp;
				}
			}
		}
		long end = System.nanoTime();
		//SORTING END

		fpl("||| SORTED |||", pw);
		fpa(nums,pw);
		Microtime = (end-start)/1000;
		sol("Bubble Sort Time: " + Microtime + " (MicroSeconds)");
		time = ("Bubble Sort Time: " + Microtime + " (MicroSeconds)");
		fpl("(B)Array Length: " + length, pw);
		fpl(time, pw);
		pw.close();
		} catch (IOException e){
			sol("Error");
			e.printStackTrace();
		}
		totalTime += Microtime;
		if(part == 0){//only outputs data on the final partition (last sort if partitioned)
			try (FileWriter fw = new FileWriter("data.csv", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw)){
				sol("Data is sent to the file: data.csv");
				pw.println("Array Length," + length + ",Time(Microseconds)," + totalTime);
				
				pw.close();
			}catch (IOException e){
				sol("Error");
				e.printStackTrace();
			}
		}
		return nums;
	}
	
	public static int getNum(){
		//Scanner s = new Scanner(System.in);
		
		sop("Please Enter the Number of int to sort: ");
		
		int num = s.nextInt();
		return num;
	}
	
	public static int cont(){
		//Scanner s = new Scanner(System.in);	
		int num = s.nextInt();
		return num;
	}
	
	public static int[] creatNums(int num) {
		Random rand = new Random();
		long s = 56;
		rand.setSeed(s);
		int[] nums;
		nums = new int[num];
		
		for(int i = 0; i < num; i++) {
			nums[i] = rand.nextInt();
		}
		
		return nums;
	}
	
	public static void sol(String text){//system out line
		System.out.println(text);
	}
	
	public static void sop(String text){//system out print
		System.out.print(text);
	}
	
	public static void sopa(int[] nums) {//system out print Array
		int index = 0;
		for(int item: nums) {
			System.out.println("index: " + index + "| value: " + item);
			index++;
		}
	}
	
	public static void fpa(int[] nums, PrintWriter pw) {//file print Array
			//FileWriter writeOut = new FileWriter(out.getName());
			int index = 0;
			
			for(int item: nums) {
				pw.println("index: " + index + "| value: " + item);
				index++;
			}
			//sol("Array Output Successful");
	}
	
	public static void fpl(String text, PrintWriter pw){//file print line
			//FileWriter writeOut = new FileWriter(out.getName());
			pw.println(text);
			//writeOut.close();
			//sol("Line Output Successful");
	}
	
}
