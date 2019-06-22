import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {
//Reed Ceniviva
//2/24/19
	public static void main(String[] args) {
		Scanner ui = new Scanner(System.in);
		//list of different moves
		String[] ASTG = new String[]{"Applaro","Svartrå","Tunholmen","Godafton"};
		
		System.out.println("~~~~~~~~~~~~HAVSTA~~~~~~~~~~~~");
		System.out.println("           :RULES:        ");
		System.out.println("Applaro > Svartrå & Tunholmen");
		System.out.println("      Svartrå > Tunholmen");
		System.out.println("    Tunholmen > Godafton");
		System.out.println("Godafton > Applaro & Svartrå");
		System.out.println("WOULD YOU LIKE TO PLAY A GAME? (Y/N)");
		
		String play = ui.next().toUpperCase();
		//only till you say stop so i only check for stopping
		if(play.equals("N")) {
			System.out.println("Goodbye");
			System.out.println("Rounds: 0");
			System.out.println("Player Wins: 0");
			System.out.println("Computer Wins: 0");
		}else {
			game(ASTG, 0,0,0,ui);//moves,rounds,player wins, computer wins, scanner
		}
		ui.close();
	}
	
	public static void game(String[] moves, int round, int pWins, int cWins, Scanner ui) {
		round++; //count of rounds
		Random rand = new Random();
		String cpuMove = moves[rand.nextInt(4)];//cpu picks random move
		int moveInd = 0;
		String move = "";
		//get user move
		while(moveInd>4||moveInd<1) {//catch exception exceptions for player's move pick
			try {
				moveInd = enterMove(ui);
			}catch(InputMismatchException e) {
				System.out.println("#|#|#|#|# PLEASE ENTER CORRECT INPUT #|#|#|#|#");
				ui.nextLine();
			}
		}
		move = moves[moveInd-1];//assign it the correct string value
		//find winner
		System.out.println("Player Move: " + move);
		System.out.println(" CPU Move  : " + cpuMove);
		if(move.contentEquals(cpuMove)) {//narrowed down all cases that CPU wins
			System.out.println("~~~~~~~CPU WINS~~~~~~");
			cWins++;//increment the number of wins of the computer
		}else if(move.equals("Applaro") && cpuMove.equals("Godafton")) {
			System.out.println("~~~~~~~CPU WINS~~~~~~");
			cWins++;
		}else if(move.equals("Svartrå") && !(cpuMove.equals("Tunholmen"))) {
			System.out.println("~~~~~~~CPU WINS~~~~~~");
			cWins++;
		}else if(move.equals("Tunholmen") && !(cpuMove.equals("Godafton"))) {
			System.out.println("~~~~~~~CPU WINS~~~~~~");
			cWins++;
		}else if(move.equals("Godafton") && cpuMove.equals("Tunholmen")) {
			System.out.println("~~~~~~~CPU WINS~~~~~~");
			cWins++;
		}else {//leaving only the cases for the player to win otherwise
			System.out.println("~~~~~Player Wins~~~~~");
			pWins++;//increment the number of wins for the player
		}
		//decide to replay or not and print results
		System.out.println("WOULD YOU LIKE TO PLAY AGAIN? (Y/N)");
		String again = ui.next().toUpperCase();
		if(again.equals("N")) {
			System.out.println("Results");
			System.out.println("  Rounds     : " + round);
			System.out.println(" Player Wins : " + pWins);
			System.out.println("Computer Wins: " + cWins);
		}else {
			game(moves,round,pWins,cWins,ui);//recursively call the function to play another game
		}
	}
	
	//asks user for their move
	public static int enterMove(Scanner ui) {
		System.out.println("PLEASE SELECT A MOVE:(enter one of the numbers)");
		System.out.println("Applaro (1) | Svartrå (2) | Tunholmen (3) | Godafton (4)");
		int move = ui.nextInt();
		return move;
	}
}
