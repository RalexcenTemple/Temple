import java.util.Scanner;


public class AsciiArt {
 
	public static void main(String []args) {
		
		final int SIZE=10;
		
		System.out.println("Size Defualt = 10");
		System.out.println("__________________");
		System.out.println("A:Change SIZE value");
		System.out.println("B:Print Pattern");
		System.out.println("++++++++++++++++++");
		Scanner ui = new Scanner(System.in);
		String UI1 = ui.next();
		System.out.println(UI1);
		if(UI1.contentEquals("A")) {
			System.out.println("Enter new SIZE value: ");
			final int newSIZE = ui.nextInt();
			System.out.println("SIZE value: " + newSIZE);
			System.out.println("The pattern will be printed with the new size value");
			makePattern(newSIZE);
		}
		else {
			makePattern(SIZE);
		}
		
	}
	
	public static void makePattern(int x) {
		for(int i = 0; i < x; i++) {
			if(i%2 == 0) {
				final String zero = "0"; 
				System.out.printf(zero);
			}else {
				final String one = "1";
				System.out.printf(one);
				
			}
			for(int j = 0; j <(x*2);j++) {
				final String star = "*";
				System.out.printf(star);
				
				//This will likely get points taken off as it makes the ratio of the size
				// not consistent with the value of the variable SIZE
				// just thought it was more interesting than printing out the same thing for each size
				for(int g = 0; g <(x/5);g++) { 
					if(((x*2)%4)==0) {
						final String colon = ":";
						System.out.printf(colon);
					}
				final String pipe = "|";
				System.out.printf(pipe);
				}
				
			}
			if(i%2 == 0) {
				final int zero = 0; 
				System.out.println(zero);
			}else {
				final int one = 1;
				System.out.println(one);
				
			}
		}
	}
	
	
}
