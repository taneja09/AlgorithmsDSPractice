package LC_Medium;

import java.util.ArrayList;
import java.util.List;
/*
DFS Solution => start from the border elements and do depth search if the neighbours care eligibile for water flow
we take height as min to start the flow and keep on changing reference height while processing element
*/
public class M0417_PacificAtlanticWaterFlowDFS {
	
	public List<List<Integer>>  pacificAtlantic(int[][] matrix) {
		List<List<Integer>> result = new ArrayList();
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;
		int[][]dir = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
		
		int n = matrix.length, m = matrix[0].length;
		//One visited map for each ocean - Keep a visited matrix for each queue. In the end, add the cell visited by two queue to the result.
		boolean[][]pacific = new boolean[n][m];
		boolean[][]atlantic = new boolean[n][m];
		
		
		for(int i=0; i<n; i++){
			dfs(matrix, pacific, dir ,Integer.MIN_VALUE, i, 0);
			dfs(matrix, atlantic, dir, Integer.MIN_VALUE, i, m-1);
		}
		for(int i=0; i<m; i++){
			dfs(matrix, pacific, dir, Integer.MIN_VALUE, 0, i);
			dfs(matrix, atlantic, dir, Integer.MIN_VALUE, n-1, i);
		}
		
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				List<Integer> al = new ArrayList();
				if(pacific[i][j] && atlantic[i][j]) { // check if path is possible & then add to result
					al.add(i);
					al.add(j);
					result.add(al);
				}
			}
		}
		
		return result;
	}
	
	private void dfs(int[][]matrix, boolean[][]visited, int[][] dir, int height, int x, int y){
		int n = matrix.length, m = matrix[0].length;
		if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < height) return;  //
		visited[x][y] = true;
		for(int[]d:dir){
			dfs(matrix, visited, dir, matrix[x][y], x+d[0], y+d[1]);  //keep on changing height value for current processed element
		}
	}
	
	public static void main(String[] args) {
		int[][] PAWaters = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
		M0417_PacificAtlanticWaterFlowBFS cl = new M0417_PacificAtlanticWaterFlowBFS();
		List<List<Integer>> result = cl.pacificAtlantic(PAWaters);
		for (List<Integer> res : result) {
			for (int i : res)
				System.out.print(i);
			System.out.println(" ");
		}
	}
}
