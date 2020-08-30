package LC_Medium;
/*
https://www.youtube.com/watch?v=OutDY_ICb80
 */
import java.util.*;

public class M0909_SnakesAndLadder {
	public int snakesAndLadders(int[][] board) {
		int n = board.length;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		boolean[] visited = new boolean[n * n + 1];
		
		for (int move = 0; !queue.isEmpty(); move++) {
			for (int size = queue.size(); size > 0; size--) {
				 int num = queue.poll();
				 if (visited[num]) continue;
				 visited[num] = true;
				 if (num == n * n) return move;
				for (int i = 1; i <= 6 && num + i <= n * n; i++) {
					int next = num + i;
					int value = getBoardValue(board, next);
					if (value > 0) next = value;
					if (!visited[next]) queue.offer(next);
				}
			}
		}
		
		return -1;
	}
	
	private int getBoardValue(int[][] board, int num){
		int n = board.length;
		int oldRow = (num - 1) / n;   //e.g 9 = (9-1)/6 = 1 this is actual row for 6*6 and value 9
		int row = n-1 -oldRow;   //because we are playing from end so n-1
		int oldCol = (num-1) % n;   //actual column for a matrix 6*6  and number 9 = 8%6 = 2
		int col = oldRow % 2 == 0 ? oldCol : n - 1 - oldCol;  //if current examined row is even = we are in correct direction else minus from n-1
		return board[row][col];
	}
	public static void main(String[] args) {
		int[][] board = {{-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1},
			{-1,35,-1,-1,13,-1},
			{-1,-1,-1,-1,-1,-1},
			{-1,15,-1,-1,-1,-1}};
		M0909_SnakesAndLadder cl = new M0909_SnakesAndLadder();
		System.out.println(cl.snakesAndLadders(board));
	}
}
