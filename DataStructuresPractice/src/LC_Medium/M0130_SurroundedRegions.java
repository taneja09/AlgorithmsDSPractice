package LC_Medium;

/**
 Time Complexity - O(mn)
 Reason -
 
 Space Complexity - O(1)
 Reason -  No extra space has been used ... internal stack O(m*m)
 */
import Algorithms.Graphs.UnionFind;
import javafx.util.Pair;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Algorithm - Visit all boundaries and mark surrounding '0' to '*'
because they can't be converted to 'x' as they are touching boundary 0.

Now flip the remaining 0 to 'X' and flip '*' back to 0
*/

/*
 * Approach2: https://leetcode.com/problems/surrounded-regions/discuss/167165/Java-Union-Find-with-Explanations
 * We aim to find all 'O's such that it is on the
 * border or it is connected to an 'O' on the border. If we regard 'O' mentioned
 * above as a node (or an element), the problem becomes to find the connected
 * components (or disjoint sets) connected to borders. So-called borders should
 * also be represented as an element, so elements connected to it can be merged
 * with it into a set. That's the usage of dummyBorder.
 *
 * 		for O in board
			if O is on border
				union(dummyBorder, O)
			else
				for neighbour of O
					if (neighbour is 'O')
						union(neighbour, O)

		for each cell
			if cell is 'O' && (find(cel) != find(dummyBorder))
				flip
 */
public class M0130_SurroundedRegions {
	
	/***************** Union -Find Solution  TC = O(m*n) SC = O(m*n) parents and Rank array ******************************/
	
	public void solveUF(char[][] board) {
		if (board.length == 0 || board[0].length == 0) return;
		if (board.length < 3 || board[0].length < 3) return;
		int dir[][] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		int m = board.length, n = board[0].length;
		int border = m * n;
		UnionFind uf = new UnionFind(border + 1);
		
		for (int x = 0; x < m; x++) {
			for (int y = 0; y < n; y++) {
				if (board[x][y] != 'O') continue;
				int curIndex = x * n + y;   // 2D to 1D index
				
				if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
					uf.union(border, curIndex);
					continue;
				}
				
				for (int[] d : dir) {
					int nextX = x + d[0];
					int nextY = y + d[1];
					if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && board[nextX][nextY] == 'O') {
						int next = nextX * n + nextY;  // 2D to 1D index
						uf.union(next, curIndex);
					}
				}
			}
		}
		
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'O' && uf.find(i * n + j) != uf.find(border)) {
					board[i][j] = 'X';
				}
			}
		}
	}
	
	/***************** DFS Solution ******************************/
	public void solve(char[][] board) {
		if (board.length == 0 || board[0].length == 0) return;
		if (board.length < 3 || board[0].length < 3) return;
		int dir[][] = {{1,0},{-1,0},{0,1},{0,-1}};
		int m = board.length, n = board[0].length;
		
		for (int i = 0; i < m; i++) {
			if (board[i][0] == 'O')
				boundaryDFS(board, i, 0);
			if (board[i][n-1] == 'O')
				boundaryDFS(board, i, n-1);
		}
		for (int j = 1; j < n - 1; j++) {
			if (board[0][j] == 'O') boundaryDFS(board, 0, j);
			if (board[m - 1][j] == 'O') boundaryDFS(board, m - 1, j);
		}
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(board[i][j] == 'O') board[i][j] = 'X';
				if (board[i][j] == '*') board[i][j] = 'O';
			}
		}
	}
	
	private void boundaryDFS(char[][] board,int i, int j){
		if (i < 0 || j < 0 || i > board.length - 1 || j > board[0].length - 1 || board[i][j] != 'O') return;
		board[i][j] = '*';
		boundaryDFS(board, i + 1, j);
		boundaryDFS(board, i - 1, j);
		boundaryDFS(board, i, j + 1);
		boundaryDFS(board, i, j - 1);
	}
	
	/***************** BFS Solution ******************************/
	
	public void solveBFS(char[][] board) {
		if (board.length == 0 || board[0].length == 0) return;
		if (board.length < 3 || board[0].length < 3) return;
		int m = board.length, n = board[0].length;
		Queue<Pair<Integer,Integer>> q=new LinkedList<>();
		
		//Add all border elements to queue.
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				if((i==0 || j==0 || i==board.length-1 || j==board[0].length-1) && board[i][j]=='O')
					q.add(new Pair<>(i,j));
			}
		}
		
		while(!q.isEmpty()){
			Pair<Integer,Integer> currentRegion = q.poll();
			int x = currentRegion.getKey();
			int y = currentRegion.getValue();
			
			board[x][y] = '*';
			if(x>0 && x<m && y+1>=0 && y+1<n && board[x][y+1] != '*' && board[x][y+1] == 'O' )q.add(new Pair(x,y+1));
			if(x>0 && x<m && y-1>=0 && y-1<n && board[x][y-1] != '*' && board[x][y-1] == 'O')q.add(new Pair(x,y-1));
			if(x-1>0 && x-1 <m && y>=0 && y<n && board[x-1][y] != '*' && board[x-1][y] == 'O')q.add(new Pair(x-1,y));
			if(x+1>0 && x+1 <m && y>=0 && y<n && board[x+1][y] != '*' && board[x+1][y] == 'O')q.add(new Pair(x+1,y));
		}
		
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++) {
				if(board[i][j] == 'O') board[i][j] = 'X';
				if(board[i][j] == '*') board[i][j] = 'O';
			}
		}
	}
	
	public static void main(String[] args) {
		char[][] board = {{'X','O','X','O','X','O'},{'O','X','O','X','O','X'},{'X','O','X','O','X','O'},{'O','X','O','X','O','X'}};
		M0130_SurroundedRegions cl = new M0130_SurroundedRegions();
		//cl.solve(board);
		//cl.solveBFS(board);
		cl.solveUF(board);
		for(char[] arr : board)
			System.out.println(Arrays.toString(arr));
	}
}
