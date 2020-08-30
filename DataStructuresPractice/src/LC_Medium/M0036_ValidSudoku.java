package LC_Medium;

import java.util.HashSet;

/**
 * Collect the set of things we see, encoded as strings. For example:
 *
 * '4' in row 7 is encoded as "(4)7".
 * '4' in column 7 is encoded as "7(4)".
 * '4' in the top-right block is encoded as "0(4)2".
 *
 *	Scream false if we ever fail to add something because it was already added (i.e., seen before).
 *
 *
 *
 *
 */
public class M0036_ValidSudoku {
	HashSet<String> hs = new HashSet();
	
	public boolean isValidSudoku(char[][] board) {
  
		for(int i = 0; i< board.length; i++){
			for(int j = 0; j< board[0].length; j++){
				if (board[i][j] != '.') {
					if(! hs.add(board[i][j] + " in row " + i) || ! hs.add(board[i][j] + " in column " + j) || ! hs.add(board[i][j] + " in block " + i/3 + "-" + j/3)) return false;
				}
			}
		}
		
		return true;
   	}
	
	public boolean isValidSudoku1(char[][] board) {
  
		for(int i = 0; i< board.length; i++){
			for(int j = 0; j< board[0].length; j++){
				if (board[i][j] != '.') {
					String b = "(" + board[i][j] + ")";
					if(! hs.add(b+i) || ! hs.add(j+b) || ! hs.add(i/3 + b + j/3)) return false;
				}
			}
		}
		
		return true;
   	}
	public static void main(String[] args) {
		char[][] sudoku = new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
			{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
			{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
			{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
			{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
			{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
			{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
			{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
			{'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
		
		M0036_ValidSudoku cl = new M0036_ValidSudoku();
		System.out.println(cl.isValidSudoku1(sudoku));
		System.out.println(cl.isValidSudoku(sudoku));
	}
}
