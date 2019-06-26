import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class sortingClean {

	public static void main(String[] args) {
		System.out.println(":Starting Sorting Algorithms Program:");
		sorts(true);
	}
	
	public static void sorts() {
		Scanner in = new Scanner(System.in);
		int[] comps = new int[3];
		int[] exchs = new int[3];
		int[] times = new int[3];
		
		//get the size of the array from the user
		int SIZE = 0;
		while(SIZE < 2) {
			System.out.println("Please enter the sample size you want to test: ");
			SIZE = in.nextInt();
			if(SIZE < 2) {
				System.out.println("Please enter a number greater than one");
			}
		}
		//get the number of times for the sorting algorithms to run from the user
		int x = 0;
		while(x < 1) {
			System.out.println("How many times do you want this length array to be evaluated: ");
			x = in.nextInt();
			if(x < 1) {
				System.out.println("please enter a positive number of times you would like the sorting algorithms to run");
			}
		}
		//run the sorting algorithms and display the results
		for(int i = 0; i < x; i++) {
			System.out.println("+++++++++++++++++++++++++Run: " + (i+1) + " ++++++++++++++++++++++++++");
			int[] nums = generateUniqueArray(SIZE);
			int[] temp = nums.clone();
			
			System.out.println("Starting Array: " + Arrays.toString(temp));
			//Insertion Sort 
			int[] ISResult = InsertionSort(nums);
			System.out.println("Insertion Sort Array: " + Arrays.toString(nums));
			comps[0] += ISResult[1];
			exchs[0] += ISResult[2];
			times[0] += ISResult[0];
			nums = temp.clone();
			
			//Quick Sort
			int[] QSResult = QuickSort(nums);
			System.out.println("    Quick Sort Array: " + Arrays.toString(nums));
			comps[1] += QSResult[1];
			exchs[1] += QSResult[2];
			times[1] += QSResult[0];
			nums = temp.clone();
			
			//Merge Sort
			
			int[] MSResult = MergeSort(nums);
			System.out.println("    Merge Sort Array: " + Arrays.toString(nums));
			comps[2] += MSResult[1];
			exchs[2] += MSResult[2];
			times[2] += MSResult[0];
			nums = temp.clone();
			
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		in.close();
		System.out.println("RESULTS OF INSERTION SORT:");
		System.out.println("Average Insertion Sort Comparisons: " + comps[0]/x);
		System.out.println("  Average Insertion Sort Exchanges: " + exchs[0]/x);
		System.out.println("    Average Insertion Sort Runtime: " + times[0]/x);
		System.out.println("RESULTS OF QUICK SORT:");
		System.out.println("    Average Quick Sort Comparisons: " + comps[1]/x);
		System.out.println("      Average Quick Sort Exchanges: " + exchs[1]/x);
		System.out.println("        Average Quick Sort Runtime: " + times[1]/x);
		System.out.println("RESULTS OF MERGE SORT:");
		System.out.println("    Average Merge Sort Comparisons: " + comps[2]/x);
		System.out.println("      Average Merge Sort Exchanges: " + exchs[2]/x);
		System.out.println("        Average Merge Sort Runtime: " + times[2]/x);
	}
	
	public static void sorts(boolean check) {
		Scanner in = new Scanner(System.in);
		int[] comps = new int[3];
		int[] exchs = new int[3];
		int[] times = new int[3];
		
		//get the size of the array from the user
		int MAXSIZE = 1025;
		//run the sorting algorithms and display the results
		for(int SIZE = 2; SIZE< MAXSIZE; SIZE = SIZE*2) {
			for(int i = 0; i < 1000; i++) {
				//System.out.println("+++++++++++++++++++++++++Run: " + (i+1) + " ++++++++++++++++++++++++++");
				int[] nums = generateUniqueArray(SIZE);
				int[] temp = nums.clone();
				
				//Insertion Sort 
				int[] ISResult = InsertionSort(nums);
				comps[0] += ISResult[1];
				exchs[0] += ISResult[2];
				times[0] += ISResult[0];
				nums = temp.clone();
				
				//Quick Sort
				int[] QSResult = QuickSort(nums);
				comps[1] += QSResult[1];
				exchs[1] += QSResult[2];
				times[1] += QSResult[0];
				nums = temp.clone();
				
				//Merge Sort
				
				int[] MSResult = MergeSort(nums);
				comps[2] += MSResult[1];
				exchs[2] += MSResult[2];
				times[2] += MSResult[0];
				nums = temp.clone();
				
				//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			}
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("RESULTS FOR AN INT ARRAY OF SIZE: " + SIZE + " | AFTER 1000 RUNS");
			/*
			System.out.println("RESULTS OF INSERTION SORT:");
			System.out.println("Average Insertion Sort Comparisons: " + comps[0]/1000);
			System.out.println("  Average Insertion Sort Exchanges: " + exchs[0]/1000);
			System.out.println("    Average Insertion Sort Runtime: " + times[0]/1000);
			System.out.println("RESULTS OF QUICK SORT:");
			System.out.println("    Average Quick Sort Comparisons: " + comps[1]/1000);
			System.out.println("      Average Quick Sort Exchanges: " + exchs[1]/1000);
			System.out.println("        Average Quick Sort Runtime: " + times[1]/1000);
			System.out.println("RESULTS OF MERGE SORT:");
			System.out.println("    Average Merge Sort Comparisons: " + comps[2]/1000);
			System.out.println("      Average Merge Sort Exchanges: " + exchs[2]/1000);
			System.out.println("        Average Merge Sort Runtime: " + times[2]/1000);
			*/
			System.out.println("| Sort Type |(1)Average Comparisons |(2)Average Exhanges |(3)Average Runtime |");
			System.out.println("|Insertion: |(1)     " + comps[0]/1000 + "         |(2)    " + exchs[0]/1000 + "       |(3)    " + times[0]/1000 + "      |");
			System.out.println("|Quick:     |(1)     " + comps[1]/1000 + "          |(2)    " + exchs[1]/1000 + "        |(3)    " + times[1]/1000 + "       |");
			System.out.println("|Merge:     |(1)     " + comps[2]/1000 + "          |(2)    " + exchs[2]/1000 + "        |(3)    " + times[2]/1000 + "      |");
		}
		
		in.close();
		
	}
	
	public static int[] generateArray(int size) {
		int[] nums = new int[size];
		for(int i = 0; i < size;i++) {
			int num = (int)(Math.random() * size);
			nums[i] = num;
		}
		return nums;
	}
	
	public static int[] generateUniqueArray(int size) {
		List<Integer> nums = new ArrayList<Integer>();
		while(nums.size()<size){
			int num = (int)(Math.random() * size);
			if(!nums.contains(num)) {
				nums.add(num);
			}
		}
		int[] thesenums = new int[nums.size()];
		int count =0;
		for(int n: nums) {
			thesenums[count] = n;
			count++;
		}
		return thesenums;
	}
	
	public static int[] InsertionSort(int[] nums) {
		long startTime = System.nanoTime();
		int comparisons = 0;
		int exchanges = 0;
		int[] toReturn = new int[3];
		int n = nums.length;  
	    for (int r = 1; r < n; r++) {  
	    	comparisons++;//comparison in the for statement each time it runs
	    	int ind = nums[r];  
	    	int l = r-1;
	    	while ( (l > -1) && ( nums [l] > ind ) ) {
	    		comparisons++;//comparison in the while statement each time it runs
	    		nums [l+1] = nums [l];
	    		exchanges++;//exchanges these two index
	    		l--;
	    		}
	    	nums[l+1] = ind;
	    	//exchanges++;//sets this index to a different value
	    	}  
		long endTime = System.nanoTime();
		int time = (int)(endTime - startTime);
		
		toReturn[0] = time;
		toReturn[1] = comparisons;
		toReturn[2] = exchanges;
		return toReturn;
	}

	public static int[] QuickSort(int[] nums) {
		//System.out.println("QuickSort Started");
    	int[] stats = new int[2];
		int[] quicknums = nums;
		long startTime = System.nanoTime();
		int[] result = QuickSort(quicknums,0,quicknums.length -1,stats);
		long endTime = System.nanoTime();
		int time = (int)(endTime - startTime);
		//System.out.println("Comparisons: " + result[0]);
		//System.out.println("exchanges: " + result[1]);
		//System.out.println("Runtime: " + time);
		int[] toReturn = new int[3];
		toReturn[0] = time;
		toReturn[1] = result[0];
		toReturn[2] = result[1];
		return toReturn;
	}
	
	public static int[] partition(int nums[], int l, int r){ 
		int[] toReturn = new int[3];
		int comp= 0;
		int exch =0;
		int pivot = nums[r];  
        int i = (l-1);
        //exch++;
        for (int j=l; j<r; j++) { 
        	comp++;
            if (nums[j] <= pivot) { 
            	comp++;
                i++; 
                int temp = nums[i]; 
                nums[i] = nums[j]; 
                nums[j] = temp; 
                exch ++;
            } 
        } 
        int temp = nums[i+1]; 
        nums[i+1] = nums[r]; 
        nums[r] = temp; 
        exch ++;
        toReturn[0] = i+1;
        toReturn[1] = comp;
        toReturn[2] = exch;
        return toReturn;
    } 

    public static int[] QuickSort(int nums[], int l, int r,int[] stats){ 
        if (l < r) {
        	stats[0]++;
            int[] ind = partition(nums, l, r);
            stats[0] += ind[1];
            stats[1] += ind[2];
            QuickSort(nums, l, ind[0]-1,stats);
            QuickSort(nums, ind[0]+1, r,stats); 
        }	
        return stats;
    } 	

    public static int[] MergeSort(int[] nums) {
		//System.out.println("MergeSort Started");
		int[] mergenums = new int[nums.length];
		int[] stats = new int[2];
		stats[0] = 0;
		stats[1] = 0;
		int[] toReturn = new int[3];
		int[] temp = new int[2];
		mergenums = nums;
		long startTime = System.nanoTime();
		temp = MergeSort(mergenums,0,mergenums.length-1,stats);
		long endTime = System.nanoTime();
		int time = (int)(endTime - startTime);
		//System.out.println("Runtime: " + time);
		toReturn[0] = time;
		toReturn[1] = temp[0];
		toReturn[2] = temp[1];
		return toReturn;
	}
	
	public static int[] merge(int nums[], int l, int m, int r) { 
		int[] toReturn = new int[2];
		int comp = 0;
		int exch = 0;
        int size1 = m - l + 1; 
        int size2 = r - m; 
        int[] L = new int[size1]; 
        int[] R = new int[size2]; 
        //populate seperate arrays for the split
        for (int i=0; i<size1; i++) {
        	L[i] = nums[l + i]; 
        }
        for (int i=0; i<size2; i++) {
        	R[i] = nums[(m+i)+1]; 
        }
        int i = 0;
        int j = 0; 
        int k = l; 
        while (i < size1 && j < size2) { 
        	comp++;
            if (L[i] <= R[j]) { 
                nums[k] = L[i]; 
                i++; 
                exch++;
            }else{ 
                nums[k] = R[j]; 
                j++; 
            } 
            comp++;
            k++; 
        } 
        while (i < size1) { 
            nums[k] = L[i]; 
            exch++;
            i++; 
            k++; 
        } 
        while (j < size2) { 
            nums[k] = R[j]; 
            exch++;
            j++; 
            k++; 
        } 
        toReturn[0] = comp;
        toReturn[1] = exch;
        return toReturn;
    } 
  
	public static int[] MergeSort(int nums[], int l, int r, int[] stats) {
        if (l < r) 
        { 
            int ind = (l+r)/2; 
            MergeSort(nums, l, ind,stats); 
            MergeSort(nums, ind+1, r,stats); 
            int[] temp = merge(nums, l, ind, r); 
            stats[0] += temp[0];
            stats[1] += temp[1];
        } 
        return stats;
    } 
	
}
