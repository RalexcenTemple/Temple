
public class sudokuwNums {

	public static void main(String[] args) {
		int[][] board = buildBoard();
		printBoard(board);
		solveBoard(board,0,0);
		
		
		
	}
	
	public static boolean solveBoard(int[][] board, int row,int col) {//for numbers 1-9//if that number is not in that row or col//and that number is not in this 3x3//place that number there
		if(row ==9){
		    row = 0;
		    col++;
		}
		if(col ==9){
		    System.out.println("BOARD COMPLETE");
		    printBoard(board);
		    return true;
		}
		
		if(board[row][col] !=0){
		    return solveBoard(board,row+1,col);
		}
		
		
			for(int num = 1; num < 10; num++) {	// the column to place the number
				if(board[row][col] == num) {
					if(solveBoard(board,row+1,col)){
						return true;
					}else {
						board[row][col] = 0;
					}
				}
				if(valid(board,row,col,num)) {	//check the spot with the number
					board[row][col] = num;
					if(solveBoard(board,row+1,col)) {
						return true;
					}else {
						board[row][col] = 0;
					}
				}
			}
		
		return false;
	}
	
	public static boolean valid(int[][] board, int row , int col, int num) {
		if(board[row][col] != 0) {//check if something is already here
			//System.out.println("Spot Taken: Row: " + row + "| Col: " + col);
			return false;
		}
		if(validBox(board,row,col,num)) {//check if this number can be in this box
			//System.out.println("VALID BOX");
			if(validRow(board,row,num)) {
				//System.out.println("VALID ROW");
				if(validCol(board,col,num)) {
					//System.out.println("VALID COLUMN");
					//System.out.println("SPOT FOUND");
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean validBox(int[][] board, int row, int col, int num) {
		int[] box = new int[] {((row)/3),((col)/3)};
		for(int i = 1; i < 4;i++) {
			for(int j = 1; j < 4;j++) {
				if(board[((box[0]*3)-1)+i][((box[1]*3)-1)+j] == num) {
					//System.out.println("Non-Valid Spot due to " + num + " at Row: " + (((box[0]*3))+i) + "| and Col: " + (((box[1]*3))+j));
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean validRow(int[][] board, int row,int num) {
		for(int i = 0; i < 9;i++) {
			if(board[row][i] == num) {
				//System.out.println("Non-Valid Spot due to " + num + " at Row: " + row + "| and Col: " + i);
				return false;
			}
		}
		return true;
	}
	
	public static boolean validCol(int[][] board, int col,int num) {
		for(int i = 0;i < 9;i++) {
			if(board[i][col] == num) {
				//System.out.println("Non-Valid Spot due to " + num + " at Row: " + i + "| and Col: " + col);
				return false;
			}
		}
		return true;
	}
	
	
	public static int[][] buildBoard(){
		int[][] genBoard = new int[9][9];
		int[][] board = new int[9][9];
		for(int i = 0; i < 9; i ++) {
			for(int j = 0; j < 9; j ++) {
				board[i][j] = 0;
			}
		}
		board[0][1] = 6;
		board[0][3] = 3;
		board[0][6] = 8;
		board[0][8] = 4;
		board[1][0] = 5;
		board[1][1] = 3;
		board[1][2] = 7;
		board[1][4] = 9;
		board[2][1] = 4;
		board[2][5] = 6;
		board[2][6] = 3;
		board[3][1] = 9;
		board[3][4] = 5;
		board[3][5] = 1;
		board[3][6] = 2;
		board[3][7] = 3;
		board[3][8] = 8;
		board[5][0] = 7;
		board[5][1] = 1;
		board[5][2] = 3;
		board[5][3] = 6;
		board[5][4] = 2;
		board[5][7] = 4;
		board[6][0] = 3;
		board[6][2] = 6;
		board[6][3] = 4;
		board[6][7] = 1;
		board[7][4] = 6;
		board[7][6] = 5;
		board[7][7] = 2;
		board[7][8] = 3;
		board[8][0] = 1;
		board[8][2] = 2;
		board[8][5] = 9;
		board[8][7] = 8;
		return board;
	}
	
	public static void printBoard(int[][] board) {
		for(int i = 0; i < board.length; i ++) {
			System.out.print("|");
			for(int j = 0; j < board.length; j ++) {
				System.out.print("[" + board[i][j] + "]");
				if((j+1)%3 == 0 && (j+1) < 9) {
					System.out.print("|");
				}
			}
			System.out.println("|");
			if((i+1)%3 == 0) {
				System.out.println("|+++++++++|+++++++++|+++++++++|");
			}
		}
	}
	
}
