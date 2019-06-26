import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class southieStyle {
	public static void main(String []args) throws FileNotFoundException {
		//String test = "1 i left my caR keys by the hARbOr , 2 cubA a tuna an IdeA , 3 very haRd , 4 deeR DeERs DIR diRs, 5 dooR DooRs, are bure VERY";
		
		//String original = ""; // realized I didnt need this variable
		String southieLine = "";
		File sFile = new File("jaws.txt"); //script file
		Scanner sLines = new Scanner(sFile); //script lines
		//Scanner sLines = new Scanner(test);
		PrintStream southOut = new PrintStream(new File("southieJaws.txt")); //file to print new script to
		//int i = 0;
		while(sLines.hasNextLine()) {
			String line = sLines.nextLine();
			//original = original + line + "\n";
			Scanner words = new Scanner(line);
				while(words.hasNext()) {
					String word = words.next();
					//i++;
					//System.out.println("Word " + i + ": " + word);
					word = very(word);
					word = ares(word);
					//System.out.println("Southie Word " + i + ": " + word);
					southieLine = southieLine + word + " ";
				}
				southOut.println(southieLine); //prints line by line the resulting script after changes to the file
				words.close(); //closes word scanner before restarting the loop/ending the loop
				southieLine = ""; //clears the string assigned the lines of text
		}
		sLines.close();//close line scanner before restarting the loop/end of loop
		
		
	}
	
	public static String very(String scr) { //checks for very and switches it out for wicked
		
			for(int i = 0; i < scr.length(); i++) {//try to catch the most common ways it would appear
				if(scr.equals(" very") || scr.equals("very")){
					scr = "wicked";
				}
				if(scr.equals(" VERY") || scr.equals("VERY")) {
					scr = "WICKED";
				}
				if(scr.equals(" Very") || scr.equals("Very")) {
					scr = "Wicked";
				}
			}
			return scr;
		}
	
	
	public static String ares(String scr) { //rule 1 
		for(int i = 0; i < (scr.length()); i++) {
			if(Character.toUpperCase(scr.charAt(i)) == 'A') {
				if(i+1 < scr.length()) {
				if(Character.toUpperCase(scr.charAt(i+1)) =='R') { //r following a vowel
					String temp1 = scr.substring(0,i+1);
					String temp2 = scr.substring(i+2,scr.length());
					if(Character.isUpperCase(scr.charAt(i+1))) {
						temp1 = temp1 + "H"; //replace 'R' with 'H'
						temp1 = temp1 + temp2;
						scr = temp1;
					}else {
					temp1 = temp1 + "h"; //replace 'r' with 'h'
					temp1 = temp1 + temp2;
					scr = temp1;
					}
				}
				}else if(i!=0 && scr.charAt(i-1) != ' ' && i == scr.length()-1) { //rule 2 (ends with an a)
					String temp1 = scr.substring(0,i+1);
					String temp2 = scr.substring(i+1,scr.length());
					temp1 = temp1 + "r"; //If a word ends in 'a', append an 'r'
					temp1 = temp1 + temp2;
					scr = temp1;
				}
			}
			if(Character.toUpperCase(scr.charAt(i)) == 'I') {
				if(scr.length() > 1 && i+1 != scr.length()){
				if(Character.toUpperCase(scr.charAt(i+1)) =='R') { //r following a vowel
					if(i+1 == scr.length()-1) { //exception 1 ( ir at the end of a word)
						String temp1 = scr.substring(0,i+1);
						String temp2 = scr.substring(i+2,scr.length());
						if(Character.isUpperCase(scr.charAt(i+1)) && Character.isUpperCase(scr.charAt(i))) {
							temp1 = temp1 + "YAH"; //replace 'R' with "YAH" instead of 'h'
							temp1 = temp1 + temp2;
							scr = temp1;
						}else {
							temp1 = temp1 + "yah"; //replace 'r' with "yah" instead of 'h'
							temp1 = temp1 + temp2;
							scr = temp1;
						}
					}else{
					String temp1 = scr.substring(0,i+1);
					String temp2 = scr.substring(i+2,scr.length());
					if(Character.isUpperCase(scr.charAt(i+1))) {
						temp1 = temp1 + "H"; //replace 'R' with 'H'
						temp1 = temp1 + temp2;
						scr = temp1;
					}else {
					temp1 = temp1 + "h"; //replace 'r' with 'h'
					temp1 = temp1 + temp2;
					scr = temp1;
					}
					}
				}
				}
			}
			if(Character.toUpperCase(scr.charAt(i)) == 'E') {
				if(i+1 != scr.length()) {
				if(Character.toUpperCase(scr.charAt(i+1)) =='R' && i != 0) { //r following a vowel
					if(Character.toUpperCase(scr.charAt(i-1)) == 'E' && i+2 == scr.length()) { // exception 1 (eer at the end of a word)
						String temp1 = scr.substring(0,i+1);
						String temp2 = scr.substring(i+2,scr.length());
						if(Character.isUpperCase(scr.charAt(i+1)) && Character.isUpperCase(scr.charAt(i))) {
							temp1 = temp1 + "YAH"; //replace 'R' with "YAH" instead of 'h'
							temp1 = temp1 + temp2;
							scr = temp1;
						}else {
							temp1 = temp1 + "yah"; //replace 'r' with "yah" instead of 'h'
							temp1 = temp1 + temp2;
							scr = temp1;
						}
					}else {
					String temp1 = scr.substring(0,i+1);
					String temp2 = scr.substring(i+2,scr.length());
					if(Character.isUpperCase(scr.charAt(i+1))) {
						temp1 = temp1 + "H"; //replace 'R' with 'H'
						temp1 = temp1 + temp2;
						scr = temp1;
					}else {
					temp1 = temp1 + "h"; //replace 'r' with 'h'
					temp1 = temp1 + temp2;
					scr = temp1;
					}
					}
				}
				}
			}
			if(Character.toUpperCase(scr.charAt(i)) == 'O' && i+1 != scr.length()) {
				if(Character.toUpperCase(scr.charAt(i+1)) =='R' && i != 0) { //r following a vowel
					if(Character.toUpperCase(scr.charAt(i-1)) == 'O' && i+1 == (scr.length()-1)) {//exception 2 (oor at the end of a word)
						String temp1 = scr.substring(0,i+1);
						String temp2 = scr.substring(i+2,scr.length());
						if(Character.isUpperCase(scr.charAt(i+1)) && Character.isUpperCase(scr.charAt(i)) && Character.isUpperCase(scr.charAt(i-1))) {
							temp1 = temp1 + "WAH"; //replace 'R' with "WAH" instead of 'h'
							temp1 = temp1 + temp2;
							scr = temp1;
						}else {
							temp1 = temp1 + "wah"; //replace 'r' with "wah" instead of 'h'
							temp1 = temp1 + temp2;
							scr = temp1;
						}
					}else{
					String temp1 = scr.substring(0,i+1);
					String temp2 = scr.substring(i+2,scr.length());
					if(Character.isUpperCase(scr.charAt(i+1))) {
						temp1 = temp1 + "H"; //replace 'R' with 'H'
						temp1 = temp1 + temp2;
						scr = temp1;
					}else {
					temp1 = temp1 + "h"; //replace 'r' with 'h'
					temp1 = temp1 + temp2;
					scr = temp1;
					}
					}
				}
			}
			if(Character.toUpperCase(scr.charAt(i)) == 'U' && i+1 != scr.length()) {
				if(Character.toUpperCase(scr.charAt(i+1)) =='R') { //r following a vowel
					String temp1 = scr.substring(0,i+1);
					String temp2 = scr.substring(i+2,scr.length());
					if(Character.isUpperCase(scr.charAt(i+1))) {
						temp1 = temp1 + "H"; //replace 'R' with 'H'
						temp1 = temp1 + temp2;
						scr = temp1;
					}else {
					temp1 = temp1 + "h"; //replace 'r' with 'h'
					temp1 = temp1 + temp2;
					scr = temp1;
					}
				}
			}
		}
		
		return scr;
		
	}
}
