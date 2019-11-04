package hangmanCheat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class hangman {
	private int WORDSIZE;
	private int WRONGGUESS;
	private int WRONGGUESSALLOWED;
	private Set<String> words;
	private Set<Character> guessed;
	private Set<Character> correct;
	private String secretWord = "";
	public boolean win = false;
	
	
	public hangman() {
		System.out.println("WELCOME TO MY HANGMAN GAME");
		words = new HashSet<String>();
		String fileName = "words.txt";
		try {
			Scanner scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				//System.out.println(line);
				words.add(line.toLowerCase());
			}
			//System.out.println(words.toString());
			scanner.close();
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scanner in = new Scanner(System.in);
		WORDSIZE = getWordSize(words, in);
		words = thoseOfSize(words, WORDSIZE);
		System.out.println("Please Select Your Difficulty: ");
		System.out.println("1) Easy");
		System.out.println("2) Moderate");
		System.out.println("3) Hard");
		int dif = in.nextInt();
		if(dif == 1) {
			WRONGGUESSALLOWED = 26;
		}else if(dif == 2) {
			WRONGGUESSALLOWED = 20;
		}else {
			WRONGGUESSALLOWED = 14;
		}
		WRONGGUESS =0;
		guessed = new HashSet<Character>();
		correct = new HashSet<Character>();
		for(int i = 0; i < WORDSIZE;i++) {
			secretWord += "_";
		}
		startGame(in);
		in.close();
	}
		
	public void startGame(Scanner in) {
		printGame();
		while(WRONGGUESS < WRONGGUESSALLOWED) {
			if(words.size() > 1 && (correct.size()-1) < WORDSIZE) {
			System.out.println("1) Guess a Character");
			System.out.println("2) Guess a Word");
			System.out.println("WHAT WOULD YOU LIKE TO DO: ");
			int choice = in.nextInt();
			String guess;
			if(choice == 1) {
				guess = getCharGuess(in);
				guessed.add(guess.charAt(0));
				String pos = bestPos(guess);
				//System.out.println(pos);
				if(validPos(pos)) {
					System.out.println("You have guessed a correct letter");
					correct.add(guess.charAt(0));
					updateSecretWord(pos);
					words = wordsWithPos(pos, guess);
				}else {
					words = wrongCharGuess(guess);
				}
				//System.out.println(words.toString());
				//System.out.println("AVAILABLE WORDS: " + words.size());
				//System.out.println("Guessed Letters: " + guessed.toString());
				printGame();
			}else if(choice == 2) {
				guess = getWordGuess(in);
				if(words.size() == 1) {
					if(words.contains(guess)){
						winGame(guess);
						break;
					}else {
						wrongWordGuess(guess);
					}
				}else if(words.size() > 1) {
					if(words.contains(guess)) {
						words.remove(guess);
					}
						wrongWordGuess(guess);
				}
				printGame();
			}else {
				System.out.println("Please enter a correct choice");
			}
			}else{
				System.out.println("Your choices have been reduced enough, you must guess what the word is");
				String guess = getWordGuess(in);
				if(words.contains(guess)){
					winGame(guess);
					break;
				}else {
					wrongWordGuess(guess);
					WRONGGUESS++;
				}
				printGame();
			}
		}
		in.close();
	}
	
	private void printGame() {
		System.out.println("+++++++++++++++++++++++++++++++++++++");
		System.out.println("HANGMAN GAME:");
		System.out.println("WRONG GUESSES: " + WRONGGUESS + "/" + WRONGGUESSALLOWED);
		System.out.println(printMan());
		System.out.println("SECRET WORD: " + secretWord);
		System.out.println(" LETTER POS: 1234567890");
		System.out.println("Guessed Letters: " + guessed.toString());
		System.out.println("+++++++++++++++++++++++++++++++++++++");
	}
	
	private String printMan() {
		String[] man = new String[26];
		man[0] = "---------\n";
		man[1] = "    |  \n";
		man[2] = "    O  \n";
		man[3] = "   /";
		man[4] = "|";
		man[5] = "\\ \n";
		man[6] = "  /";
		man[7] = " | ";
		man[8] = "\\\n";
		man[9] = "    |  \n";
		man[10]= "   / ";
		man[11]= "\\\n";
		man[12]= "  /   ";
		man[13]= "\\\n";
		man[14]= "--------- \n";
		man[15]= " |   |  \n";
		man[16]= " |   |   ('yeah hes okay')\n";
		man[17]= " |   |  O   \n";
		man[18]= " |   | /|\\ \n";
		man[19]= " |   |  |   \n";
		man[20]= " |   | / \\ \n";
		man[21]= " |   |----- \n";
		man[22]= " |   | | |  \n";
		man[23]= " |   | | |";
		man[24]= " |   | | | what word are you even going for at this point";
		man[25]= " |   |   | | |||";
		
		String toReturn = "";
		for(int i = 0; i < WRONGGUESS;i++) {
			toReturn += man[i];
		}
		return toReturn;
		
	}
	
	private void updateSecretWord(String pos) {
		for(int i = 0 ; i < WORDSIZE;i++) {
			if(pos.charAt(i) != '_' && secretWord.charAt(i) == '_') {
				String temp = secretWord.substring(0,i);
				String tempback = secretWord.substring(i+1,WORDSIZE);
				String newSecretWord = temp + pos.charAt(i) + tempback;
				secretWord = newSecretWord;
			}
		}
	}
	
 	private Set<String> wrongCharGuess(String guess) {
		System.out.println("INCORRECT CHARACTER GUESS");
		System.out.println(guess + " IS NOT IN THE SECRET WORD");
		guessed.add(guess.charAt(0));
		
		Set<String> temp = words;
		
		Iterator<String> WORDS = temp.iterator();
		while(WORDS.hasNext()) {
			String str = WORDS.next();
			if(str.contains(guess)) {
				WORDS.remove();
			}
		}
		WRONGGUESS++;
		return temp;
		
	}
	
	private void wrongWordGuess(String guess) {
		System.out.println(guess + " IS NOT THE SECRET WORD");
		WRONGGUESS++;
	}
	
	private void winGame(String guess) {
		System.out.println("CONGRADULATION YOU HAVE WON THE GAME");
		System.out.println(guess + " WAS THE SECRET WORD");
		win = true;
	}
	
	private Set<String> wordsWithPos(String pos, String letter) {
		Set<String> newWords = new HashSet<>();
		for(String word: words) {
			String thiskey = "";
			for(int i = 0; i < WORDSIZE;i++) {
				if(word.charAt(i) == letter.charAt(0)) {
					thiskey += letter;
				}else {
					thiskey += "_";
				}
			}
			
			if(thiskey.equals(pos)) {
				//System.out.println(thiskey);
				//System.out.println(pos);
				newWords.add(word);
			}
		}
		guessed.add(letter.charAt(0));
		return newWords;
	}
	
	private String getCharGuess(Scanner in){
		System.out.print("What letter do you want to guess: ");
		String guess = in.nextLine().toLowerCase();
		if(guess.length() > 1 || guess.length() == 0) {
			System.out.println("Please enter a single character as your guess");
			return getCharGuess(in);
		} else {
			if(guessed.contains(guess.charAt(0))) {
				System.out.println("You have already guessed that letter");
				return getCharGuess(in);
			}else {
				System.out.println("You have guessed: " + guess);
				return guess;
			}
		}
	}
	
	private String getWordGuess(Scanner in) {
		System.out.print("What word do you want to guess: ");
		String guess = in.nextLine();
		if(guess.length() == 0 || guess.contains(" ")) {
			System.out.println("Please enter a whole word");
			return getWordGuess(in);
		}else {
			return guess;
		}
	}
	
	private String bestPos(String letter){
		Map<String,Integer> pos = new HashMap<String,Integer>();
		
		for(String word: words) {
			String key = "";
			for(int i =0;i < WORDSIZE;i++) {
				if(word.charAt(i) == letter.charAt(0) && secretWord.charAt(i) == '_') {
					key += letter;
				}else {
					key += "_";
				}
			}
			if(pos.containsKey(key)) {
				pos.put(key,(pos.get(key) + 1));
			}else {
				pos.put(key,1);
			}
			
		}
		int size = 0;
		String toReturn = "";
		for(String key: pos.keySet()) {
			//System.out.println("Key: " + key + "| Value: " + pos.get(key).toString());
			if(pos.get(key) > size) {
				size = pos.get(key);
				toReturn = key;
			}
		}
		//System.out.println("Largest Family: " + toReturn + "| Size: " + size);
		return toReturn;
		
	}
	
	private boolean validPos(String pos) {
		String empty = "";
		for(int i = 0; i < WORDSIZE; i++) {
			empty += "_";
		}
		if(pos.equals(empty)) {
			return false;
		}else {
			return true;
		}
	}
	
	private int getWordSize(Set<String> words, Scanner in) {
		System.out.println("Please enter the size of the word you would like to try and guess: ");
		int size = in.nextInt();
		if(ofThisSize(words,size)) {
			return size;
		}else {
			System.out.println("Please enter a different size for your word");
			return getWordSize(words, in);
		}
	}
	
	private boolean ofThisSize(Set<String> words, int size) {
		for(String word: words) {
			if(word.length() == size) {
				return true;
			}
		}
		return false;
	}

	private Set<String> thoseOfSize(Set<String> words, int size){
		Iterator<String> Words = words.iterator();
		while(Words.hasNext()) {
			String str = Words.next();
			if(str.length() != size) {
				Words.remove();
			}
		}
		return words;
	}
	
	public static void main(String[] args) {
		hangman temp = new hangman();
		if(!temp.win) {
			System.out.println("You Lost, the remaining optional words are bellow");
			System.out.println("Number of words: " + temp.words.size());
			System.out.println("words: " + temp.words.toString());
		}
		
	}

}
