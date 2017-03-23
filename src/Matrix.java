import java.util.Scanner;

/* Sudoku solver using backtracking algorithms */

public class Matrix {
    private int matrix[][];
    private boolean complete = false;
    int[] next;
    
    
    // Class constructor
    public Matrix(int[][] puzzle) {
    	// make a copy of 2D array
    	matrix = new int[9][9];
    	matrix = puzzle;
    	// 
    	next = new int[2];
    }
    
    // Method to find the next empty grid to fill
    private boolean findEmpty() {
    	for (int i =0; i < 9; i++) {
    		for(int j = 0; j < 9; j++) {
    			if (matrix[i][j] == 0) {
    				next[0] = i;
    				next[1] = j;
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    // Prints values stored in matrix
    public void printMatrix() {
    	// prints sudoku grid
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    public void solveSudoku() {
    	System.out.println("solving...");
    	if (solveGrid()) {
    		System.out.println("Solved grid: ");
    		printMatrix();
    	} 
    }
    
    private boolean solveGrid() {
    	// returns true of puzzle is solved 
    	// i.e. no remaining empty grids
    	if(!findEmpty()) {
    		return true;
    	}
    	
    	// stores next grid to fill 
    	int row = next[0];
    	int col = next[1];
    	
		// check if its possible to place 1 - 9 on the grid
    	for (int i = 1; i <= 9; i++) {
    		if(canPlace(row, col, i)) {
    			matrix[row][col] = i;
    			// recursive call to initiate depth first search
    			if (solveGrid()) {   				
    				return true;
    			}
    			// if unable to place value i, reset grid value to 0
    			matrix[row][col] = 0;
    		}
    	}
    	
    	// returns false if its not possible to place 1 - 9 in the grid 
    	return false;
    } 
    
    
    private boolean canPlace(int x, int y, int n) {
    	// checks the row for collision
    	for (int i = 0; i < 9; i++) {
    		if (matrix[x][i] == n) {
    			return false; 
    			}
    	}
    	
    	// checks the column for collision
    	for (int j = 0; j < 9; j++) {
    		if (matrix[j][y] == n) {
    			return false;
    		}
    	}
    	
    	// checks the box for collision
    	int xThirds = x / 3;
    	int yThirds = y / 3;
    	for (int k = 0; k < 3; k++) {
    		for (int m = 0; m <3; m++) {
    			if (matrix[xThirds*3+k][yThirds*3+m] == n) {
    				return false;
    			}
    		}
    	}
    	
    	// returns true if there is no collision
    	return true;
    }




    public static void main(String[] args) {
    	System.out.println("===== Sudoku Solver =====");
    	System.out.println("Please enter puzzle to solve: ");
        Scanner sc = new Scanner(System.in);
        int[][] puzzle = new int[9][9];
        for (int i = 0; i < 9; i++) {
        	for (int j = 0; j < 9; j++) {
        		puzzle[i][j] = sc.nextInt();
        	}
        }
        Matrix solver= new Matrix(puzzle);
        solver.solveSudoku(); 
    }
}
