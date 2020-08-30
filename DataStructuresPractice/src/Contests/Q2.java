package Contests;

import Algorithms.Graphs.UnionFind;

import java.util.HashMap;

public class Q2 {
	
	public int minDays(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
	            return 0;
	        }
	        int m = grid.length;
	        int n = grid[0].length;
	        UnionFind UnionSets = new UnionFind(m * n + 1);
	        int[][] directions = {{0, 1}, {1, 0}, {-1,0}, {0,-1}}; // only check forward and downward directions
	        int count =0;
	        
	        for (int i = 0; i < m; i++) {
	            for (int j = 0; j < n; j++) {
	                if (grid[i][j] == 1) {
	                    count++;
	                    for (int[] d : directions) {
	                        int x = i + d[0];
	                        int y = j + d[1];
	                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
	                            int id1 = i * n + j;   //making 2d matrix to 1d matrix (1,1) in 3*3 matrix  =    1 * 3 + 1  = 4 = > (1,1) middle index will become 4th index in 1D matrix
	                            int id2 = x * n + y;
	                            if(UnionSets.union(id1, id2)) count-- ;
	                        }
	                    }
	                }
	            }
	        }
		
		  HashMap<Integer, Integer> hm = new HashMap<>();
	        for(int i = 0; i< UnionSets.parents.length ; i++){
	        	if(i == UnionSets.parents[i]) continue;
	        	else{
	        		hm.put(UnionSets.parents[i],hm.getOrDefault(UnionSets.parents[i],0)+1);
				}
			}
	        
	        int max = hm.size();
	        return max-1;
	}
	
	public static void main(String[] args) {
		Q2 obj = new Q2();
		int[][] grid = {{1,1,0,1,1},{1,1,1,1,1},{1,1,0,1,1},{1,1,0,1,1}};
		
		System.out.println(obj.minDays(grid));
	}
}
