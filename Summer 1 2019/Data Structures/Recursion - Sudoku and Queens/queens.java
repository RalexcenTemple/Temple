
public class queens {
	
	public static void main(String[] args) {
		int SIZE = 8;
		int[][] board = new int[SIZE][SIZE];
		int pos = 0;
		
		for(int i = 0; i < 8; i ++) {
			for(int j = 0; j < 8; j ++) {
				board[i][j] = 1;
			}
		}
		
		printBoard(board);
		solve(board,pos);
	}
	
	public static boolean solve(int[][] board, int pos) {
		
		if(pos == 8) {
			System.out.println("|********COMPLETE********|");
			printBoard(board);
			return true;
		}
		
		for(int i = 0; i < board.length;i++) {
			if(valid(board,i,pos)) {
				//mark the board
				board[i][pos] = 8;
				//printBoard(board);
				if(solve(board,pos+1)) {
					return true;
				}
				else {
					board[i][pos] = 1;
				}
			}
		}
		//System.out.println("Fixing Board");
		for(int j = 0; j < board.length;j++) {
			if(board[j][pos] == 8) {
				board[j][pos] = 1;
			}
		}
		//printBoard(board);
		return false;
	}
	
	public static boolean valid(int[][] board, int row,int col) {//check if this spot is in view of a queen
		int SWDis = board.length - row;
		//System.out.println("Checking Position| Row: " + row + " | Col: " + col + " |");
		if(board[row][col] == 8) {
			return true;
		}
		for(int i = 1; i <= col;i++) {//check the west
			if(board[row][col-i] == 8) {
				return false;
			}
		}
		//System.out.println("Nothing in West Direction");
		for(int i = 1; i <= col || i <= row;i++) {//check the north west
			if(row-i >= 0 && col-i >= 0) {//make sure the spot we want to check is still on the board
				if(board[row-i][col-i] == 8) {//check if that spot is a queen and thus in view
					return false;
				}
			}
		}
		//System.out.println("Nothing in North West Direction");
		for(int i = 1; i <= col || i <= SWDis;i++) {//check the north west
			if(row+i < board.length && col-i >= 0) {//make sure the spot we want to check is still on the board
				if(board[row+i][col-i] == 8) {//check if that spot is a queen and thus in view
					return false;
				}
			}
		}
		//System.out.println("Nothing in South West Direction");
		return true;
	}
	
	public static void printBoard(int[][] board) {
		for(int i = 0; i < 8; i ++) {
			System.out.print("|");
			for(int j = 0; j < 8; j ++) {
				System.out.print("[" + board[i][j] + "]");
			}
			System.out.println("|");
		}
	}
}