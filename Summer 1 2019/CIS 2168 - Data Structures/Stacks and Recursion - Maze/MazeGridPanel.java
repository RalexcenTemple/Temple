import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JPanel;

public class MazeGridPanel extends JPanel{
	private int rows;
	private int cols;
	private Cell[][] maze;



	// extra credit
	public void genDFSMaze() {
		boolean[][] visited;
		Stack<Cell> stack  = new Stack<Cell>();
		Cell start = maze[0][0];
		stack.push(start);
	}

	//homework
	public void solveMaze() {
		//System.out.println("SOLVE MAZE METHOD ENTERED");
		Stack<Cell> stack  = new Stack<Cell>();
		Cell start = maze[0][0];
		start.setBackground(Color.GREEN);
		Cell finish = maze[rows-1][cols-1];
		finish.setBackground(Color.RED);
		stack.push(start);
		//System.out.println("***************STARTING WHILE STATEMENT**************");
		while(!stack.isEmpty() && !stack.peek().equals(finish)) {
			
			//if(stack.peek() == null) {
			//	
			//}else {
				Cell temp = stack.peek();
				if(!temp.northWall && !visited(temp.row-1,temp.col)) {
					Cell next = maze[temp.row-1][temp.col];
					System.out.println("GETTING NORTH CELL");
					temp.setBackground(Color.GREEN);
					stack.push(next);
				} else if(!temp.southWall && !visited(temp.row+1,temp.col)) {
					Cell next = maze[temp.row+1][temp.col];
					System.out.println("GETTING SOUTH CELL");
					temp.setBackground(Color.GREEN);
					stack.push(next);
				} else if(!temp.eastWall && !visited(temp.row,temp.col+1)) {
					Cell next = maze[temp.row][temp.col+1];
					System.out.println("GETTING EAST CELL");
					temp.setBackground(Color.GREEN);
					stack.push(next);
				} else if(!temp.westWall && !visited(temp.row,temp.col-1)) {
					Cell next = maze[temp.row][temp.col-1];
					System.out.println("GETTING WEST CELL");
					temp.setBackground(Color.GREEN);
					stack.push(next);
				} else {
					//temp is at a dead end of it's current path, must mark current spot as a dead end and pop it off the stack
					System.out.println("DEAD END");
					temp.setBackground(Color.LIGHT_GRAY);
					stack.pop();
				}
			}
		}

	//}


	

	


	public boolean visited(int row, int col) {
		Cell c = maze[row][col];
		Color status = c.getBackground();
		if(status.equals(Color.WHITE)  || status.equals(Color.RED)) {
			return false;
		}


		return true;


	}


	public void genNWMaze() {
		
		for(int row = 0; row  < rows; row++) {
			for(int col = 0; col < cols; col++) {

				if(row == 0 && col ==0) {
					continue;
				}

				else if(row ==0) {
					maze[row][col].westWall = false;
					maze[row][col-1].eastWall = false;
				} else if(col == 0) {
					maze[row][col].northWall = false;
					maze[row-1][col].southWall = false;
				}else {
					boolean north = Math.random()  < 0.5;
					if(north ) {
						maze[row][col].northWall = false;
						maze[row-1][col].southWall = false;
					} else {  // remove west
						maze[row][col].westWall = false;
						maze[row][col-1].eastWall = false;
					}
					maze[row][col].repaint();
				}
			}
		}
		this.repaint();
		
	}

	public MazeGridPanel(int rows, int cols) {
		this.setPreferredSize(new Dimension(800,800));
		this.rows = rows;
		this.cols = cols;
		this.setLayout(new GridLayout(rows,cols));
		this.maze =  new Cell[rows][cols];
		for(int row = 0 ; row  < rows ; row++) {
			for(int col = 0; col < cols; col++) {

				maze[row][col] = new Cell(row,col);

				this.add(maze[row][col]);
			}

		}


		this.genNWMaze();
		this.solveMaze();
		
	}




}
