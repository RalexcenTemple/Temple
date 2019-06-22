public class hw2 {

	//Reed Ceniviva
	//HW 2
	//1-23-2019
	//this program prints out 6 verses to a song that has a lot of repeat lyrics, without repeating many lines of identical code

	public static void main(String []args) {
		// create and declare array of strings for the different animals included in the song
		String[] animals = new String[6];
		animals[0] = "cat";
		animals[1] = "hen";
		animals[2] = "duck";
		animals[3] = "goose";
		animals[4] = "sheep";
		animals[5] = "pig";
		
		int i = 0;
		
		while(i < 6) {
			//uses an array of animal names to print the first two lines of the verses each time
			System.out.println("Bought me a " + animals[i] + " and the " + animals[i] + " pleased me,");
			System.out.println("I fed my " + animals[i] + " under yonder tree.");
			
				//prints the correct amount of the song for the given animal that has been fed
				if( i >= 5) {
					System.out.println(pigNoise());
				}
				if( i >= 4) {
					System.out.println(sheepNoise());
				}
				if( i >= 3) {
					System.out.println(gooseNoise());
				}
				if( i >= 2) {
					System.out.println(duckNoise());
				}
				if( i >= 1) {
					System.out.println(henNoise());
				}
				if( i >= 0) {
					System.out.println(catNoise());
				}
			//prints an empty line for formatting's sake and increments 'i' for the while loop to eventually terminate
			System.out.println(" ");
			i++;
		}
		
	}
	
	//Collection of functions for animal noises
	///////
	public static String catNoise() {
		return "Cat goes fiddle-i-fee.";
	}
	
	public static String henNoise() {
		return "Hen goes chimmy-chuck, chimmy-chuck,";
	}
	
	public static String duckNoise() {
		return "Duck goes quack, quack,";
	}
	
	public static String gooseNoise() {
		return "Goose goes hissy, hissy,";
	}
	
	public static String sheepNoise() {
		return "Sheep goes baa, baa,";
	}
	
	public static String pigNoise() {
		return "Pig goes oink, oink,";
	}
	///////
}

