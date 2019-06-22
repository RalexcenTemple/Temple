package arraypractice;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

/**
 *
 * @author jfiore
 * edits made by Reed Ceniviva for Array Lab
 */
public class ArrayPractice {
    /* sets every item in A[] to initialValue */
    public static void initialize(int A[], int initialValue) {
    	for(int i = 0; i < A.length; i++) {
    		A[i] = initialValue;
    	}
        return;
    }

    /* returns the average of the items in A
     * Be careful: A[] is an array of int and the method returns
     * double. What do we do to handle this? */
    public static double average(int A[]) {
    	int add = 0;
    	double div = 0.0;
    	for(int i = 0; i<A.length;i++) {
    		add = add + A[i];
    	}
    	div = add/(A.length);
        return div;
    }

    /* returns the number of times that x appears in A[] */
    public static int numOccurrences(int A[], int x) {
    	int count = 0;
    	for(int i = 0; i <A.length;i++) {
    		int temp = 0;
    		temp = A[i];
    		if(temp == x) {
    			count++;
    		}
    	}
        return count;
    }


    /* returns the index of the first occurrence of
     * x in A[] or -1 if x doesn't exist in A[] */
    public static int find(int A[], int x) {
    	for(int i = 0; i <A.length;i++) {
    		int temp = 0;
    		temp = A[i];
    		if(temp == x) {
    			return i;
    		}
    	}
        return -1;
    }

    /* Returns the index of the first occurrence of
     * item within the first n elements of A[] or -1
     * if item is not among the first n elements of A[] */
    public static int findN(int A[], int item, int n) {
    	for(int i = 0; i <n;i++) {
    		int temp = 0;
    		temp = A[i];
    		if(temp == item) {
    			return i;
    		}
    	}
        return -1;
    }

    /* returns the index of the last occurrence of
     * x in A[] or -1 if x doesn't exist in A[] */
    public static int findLast(int A[], int x) {
    	for(int i = A.length-1; i > -1;i--) {
    		int temp = 0;
    		temp = A[i];
    		if(temp == x) {
    			return i;
    		}
    	}
        return -1;
    }

    /* returns the largest item found in A */
    public static int largest(int A[]) {
    	int lorge = 0;
    	for(int i = 0; i <A.length;i++) {
    		if(A[i] > lorge) {
    			int temp = A[i];
    			lorge = temp;
    		}
    	}
        return lorge;
    }

    /* returns the index of the largest item found in A */
    public static int indexOfLargest(int A[]) {
    	int lorge = 0;
    	int lorgeI = 0;
    	for(int i = 0; i <A.length;i++) {
    		if(A[i] > lorge) {
    			int temp = A[i];
    			lorge = temp;
    			lorgeI = i;
    		}
    	}
        return lorgeI;
    }

    /* returns the index of the largest odd number
     * in A[] or -1 if A[] contains no odd numbers */
    public static int indexOfLargestOdd(int A[]) {
    	int lorge = -1;
    	int lorgeI = -1;
    	for(int i = 0; i <A.length;i++) {
    		if(((A[i]+1)%2)==0){
    			if(lorge < A[i]) {
    				int temp = A[i];
        			lorge = temp;
        			lorgeI = i;
    			}
    		}
    	}
        return lorgeI;
    }

    /* inserts n into A[] at A[index] shifting all */
    /*  the previous items one place to the right. For example */
    /*  if A is  */
    /*   |---+---+---+---+---+---+---+---+---+---| */
    /*   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | */
    /*   |---+---+---+---+---+---+---+---+---+---| */
    /*   | 5 | 7 | 6 | 9 | 4 | 3 | 0 | 0 | 0 | 0 | */
    /*   |---+---+---+---+---+---+---+---+---+---| */

    /*   and we call insert(A, 15, 1), A then becomes */

    /*   |---+----+---+---+---+---+---+---+---+---| */
    /*   | 0 |  1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | */
    /*   |---+----+---+---+---+---+---+---+---+---| */
    /*   | 5 | 15 | 7 | 6 | 9 | 4 | 3 | 0 | 0 | 0 | */
    /*   |---+----+---+---+---+---+---+---+---+---| */
    /*  the element in A[] that's in the right-most */
    /*    position is removed.                      */
    /*                                              */
    /*  if index < 0 or index >= A.length-1, the method */
    /*                                    does nothing */
    public static void insert(int A[], int n, int index) {
    	int[] temp = new int[A.length];
    	if(index < 0 || index >= A.length-1) {
    		
    	}else {
    		int j = 0;
    		for(int i = 0; i < A.length;i++) {
    			if(i == index) {
    				temp[i] = n;
    				j--;
    			}else {
    				temp[i] = A[j];
    			}
    			j++;
    		}
    	}
    	for(int i = 0; i < A.length;i++) {
    		A[i] = temp[i];
    	}
        return;
    }

    /* returns a new array consisting of all of the
     * elements of A[] */
    public static int[] copy(int A[]) {
    	int[] temp = new int[A.length];
    	for(int i = 0; i < A.length;i++) {
    		temp[i] = A[i];
    	}
        return temp;
    }

    /* Returns a new array consisting of all of the
       first n elements of A[]. If n>A.length, returns a
       new array of size n, with the first A.length elements
       exactly the same as A, and the remaining n-A.length elements
       set to 0. If n<=0, returns null. */
    public static int[] copyN(int A[], int n) {
    	if(n<=0) {
    		return null;
    	}else {
    	int[] temp = new int[n];
    	for(int i = 0; i < n;i++) {
    		if(i < A.length) {
    			temp[i] = A[i];
    		}else {
    			temp[i] = 0;
    		}
    			
    	}
        return temp;
    	}
    }

    /* returns a new array consisting of all of the
     * elements of A[] followed by all of the
     * elements of B[]. For example, if 
     A[] is: {10,20,30} and 
     B[] is: {5, 9, 38}, the method returns the
     array : {10,20,30,5,9,38} */
    public static int[] copyAll(int A[], int B[]) {
    	int newL = A.length + B.length;
    	int[] temp = new int[newL];
    	for(int i = 0; i <newL; i++) {
    		if(i<A.length) {
    			temp[i] = A[i];
    		}else {
    			temp[i] = B[i-A.length];
    		}
    	}
        return temp;
    }

    /* reverses the order of the elements in A[].
     * For example, if A[] is:
     {10,20,30,40,50}, after the method, A[] would
     be {50,40,30,20,10} */
    public static void reverse(int A[]) {
    	int[] temp = new int[A.length];
    	int j = 0;
    	for(int i = A.length-1; i > -1;i--) {
    		temp[j] = A[i];
    		j++;
    	}
    	for(int i = 0; i < A.length;i++) {
    		A[i] = temp[i];
    	}
        return;
    }

    /* Extra credit:
     *
     * Returns a new array consisting of all of the
     * elements of A, but with no duplicates. For example,
     * if A[] is {10,20,5,32,5,10,9,32,8}, the method returns
     * the array {10,20,5,32,9,8} */
    public static int[] uniques(int A[]) {
    	int[] temp = new int[A.length];
    	initialize(temp,0);
    	int j = 0;
    	for(int i =0;i<A.length;i++) {
    		if(numOccurrences(temp,A[i]) == 0) {
    			temp[j] = A[i];
    			j++;
    		}
    	}
    	int[] result = new int[((find(temp,0)))];
    	for(int i = 0; i < find(temp,0);i++) {
    		result[i] = temp[i];
    	}
        return result;
    }

    public static void main(String[] args) {
        return;
    }
}
