package LC_Medium;
/**
 
 Time Complexity - O(m * n) * O(4^(nm)/4).
 Reason - m*n = size of matrix
 			O(4^(nm)/4) = there are 4 directions and (nm)/4) check points
 
 Space Complexity - O(1)
 Reason -  constant space
 
 PS: If we take another boolean matrix, then it will take O(m*n) space to mark visited nodes
 
*/


public class M0079_WordSearch {
	public boolean exist(char[][] board, String word) {
		char[] w = word.toCharArray();
		for (int x=0; x<board.length; x++) {
			for (int y=0; y<board[0].length; y++) {
				if (exist(board, x, y, w, 0)) return true;
			}
		}
		return false;
	}
	
	private boolean exist(char[][] board, int x, int y, char[] w , int index){
		if(index == w.length) return true;
		if(x<0 || y< 0 || x>=board.length || y>= board[0].length || board[x][y] != w[index] ) return false;
		board[x][y] = '*';
		// this part can be replaced with board[x][y] ^= 256; which will flip the 8th bit of character at board[x][y]
		//  board[x][y]  = a then binary of a ^ 256 = 001100001 ^ 100000000 = 101100001 ...MSB is flipped and can be set back by again doing XOR
		
		boolean exist = exist(board, x+1, y, w, index+1) || exist(board, x, y+1, w, index+1) ||
			exist(board, x-1, y, w, index+1) || exist(board, x, y-1, w, index+1);
		
		board[x][y] = w[index];
		return exist;
	}
	
	public static void main(String[] args) {
		char[][] matrix ={{'a','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		String s = "a";
		M0079_WordSearch cl = new M0079_WordSearch();
		System.out.println(cl.exist(matrix,s));
	}
}
