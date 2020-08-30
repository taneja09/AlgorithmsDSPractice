package Interviews.Cisco;

import java.util.*;

public class Q3_SnakeAndLadder {
	public int[] snakesAndLadders(int n, int dicemax, int[][] coordinates, int[] startEnd){
		int[][] board = new int[n][n];
		int countSlide = 0;
		int countLadder = 0;
		int move = 0;
		FillBoard(board);
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(startEnd[0]);
		boolean[] visited = new boolean[n * n + 1];
		
		for (; !queue.isEmpty(); move++) {
			for (int size = queue.size(); size > 0; size--) {
				int num = queue.poll();
				if (visited[num]) continue;
				visited[num] = true;
				if (num == startEnd[1]) return new int[]{move,countLadder,countSlide};
				
				for (int i = 1; i <= dicemax && num + i <= n * n; i++) {
					int next = num + i;
					if(next == startEnd[1]){
						if (!visited[next]) queue.offer(next);
						break;
					}
					for(int[] jump : coordinates) {
						if (next == jump[0]) {
							next = jump[1];
							if (next > jump[0]) countLadder++;
							else countSlide++;
						}
					}
					if (!visited[next]) queue.offer(next);
				}
			}
		}
		return new int[-1];
	}
	
	public static void main(String[] args) {
		int size = 4;
		int dicemax = 2;
		int[][] coordinates = {{2,6},{8,11},{9,3},{15,1}};
		int[] startEnd = {1,7};
		Q3_SnakeAndLadder cl = new Q3_SnakeAndLadder();
		System.out.println(Arrays.toString(cl.snakesAndLadders(size,dicemax,coordinates,startEnd)));
	}
	
	private void FillBoard(int[][] board){
		int n  = board.length;
		int m = board[0].length;
		int fill = 1;
		
		for(int i = n-1; i>=0 ; i--){
			if(n%2 == 0 && i%2 != 0 || n%2 != 0 && i%2 == 0){
				for(int j = 0; j< board[0].length; j++){
					board[i][j] = fill++;
				}
			}else{
				for( int j = m-1; j>= 0; j--){
					board[i][j] = fill++;
				}
			}
		}
	}
}
