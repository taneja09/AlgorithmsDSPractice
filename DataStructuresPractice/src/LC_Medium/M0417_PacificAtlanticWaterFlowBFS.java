package LC_Medium;
/*
https://leetcode.com/problems/pacific-atlantic-water-flow/discuss/90758/Not-understanding-the-problem.-Could-someone-please-explain
 */
/*
BFS Solution => add all borders to queue and check BFS to add more indexes from which water can flow

 */
import java.util.*;

public class M0417_PacificAtlanticWaterFlowBFS {
	int[][]dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
	public List<List<Integer>> pacificAtlantic(int[][] matrix) {
		List<List<Integer>> result = new ArrayList();
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;
		
		int n = matrix.length, m = matrix[0].length;
		
		//One visited map for each ocean - Keep a visited matrix for each queue. In the end, add the cell visited by two queue to the result.
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        
		//Two Queue and add all the Pacific border to one queue; Atlantic border to another queue.
		Queue<int[]> pQueue = new LinkedList<>();
		Queue<int[]> aQueue = new LinkedList<>();
		
		for(int i=0; i<n; i++){ //Vertical border
           pQueue.offer(new int[]{i, 0}); //pacific ocean at left side column
           aQueue.offer(new int[]{i, m-1});  //atlantic ocean at right side column
           pacific[i][0] = true;  //visited marked
           atlantic[i][m-1] = true; //visited marked
       }
		
		for(int i=0; i<m; i++){ //Horizontal border
	            pQueue.offer(new int[]{0, i}); //pacific ocean at top row
	            aQueue.offer(new int[]{n-1, i}); //atlantic ocean at top row
	            pacific[0][i] = true;  //visited marked
	            atlantic[n-1][i] = true;  //visited marked
		}
	
		//BreadthFirst Search for both matrix
		bfs(matrix, pQueue, pacific);
		bfs(matrix, aQueue, atlantic);
		
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
	
	public void bfs(int[][]matrix, Queue<int[]> queue, boolean[][]visited){
		int n = matrix.length, m = matrix[0].length;
		while(!queue.isEmpty()){
			int[] cur = queue.poll();
			for(int[] d:dir){
				int x = cur[0]+d[0];
				int y = cur[1]+d[1];
				/*
				cur[0]][cur[1] is our current position and we are testing [x][y]. We already know that current index can flow towards oceans.
				Here we are checking if its possible from [x][y] to flow towards cur[0][1],
				if yes, then we can include [x][y] also in queue to check its neighbor further and for that
				[x][y] should be >= cur[0][1] then only flow will be possible.
				 */
				if(x<0 || x>=n || y<0 || y>=m || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]) continue;
				visited[x][y] = true;  //mark visited only if  matrix[x][y] >= matrix[cur[0]][cur[1]] i.e analysed neighbour > border because we are going from middle towards boundary
				queue.offer(new int[]{x, y});
			}
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
