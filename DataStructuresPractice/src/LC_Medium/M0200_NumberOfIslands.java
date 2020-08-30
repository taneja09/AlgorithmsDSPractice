package LC_Medium;

/*
https://www.coursera.org/lecture/algorithms-part1/analysis-of-algorithms-introduction-xaxyP
https://leetcode.com/problems/number-of-islands/discuss/715392/BFS-DFS-and-Union-Find-or-Java-or-With-Comments-Resource-and-Image
The challenge here is to find which all 1's are connected to each other to find an island.
read nodes for creating root and weighted array for nodes
*/

import Algorithms.Graphs.UnionFind;

import java.util.LinkedList;
import java.util.Queue;


public class M0200_NumberOfIslands {
    /**********  DFS Solution TC - O(m*n) SC = O(m*n) internal stack space ***************************/
   	/*
   	1. Visit index with val = '1', its an island shrink it with 0
   	2. check its connected island if '1' shrink them also using DFS
   	3. at last if everything is shrinked that means all 1 were connecetd and result is count 1
   	
   	11110
   	11010
   	11000
   	00000
   	
   	keep on shrinking connected island till we are able to find 1
   	 */
    public int numIslandsDFS(char[][] grid) {
        int count = 0;
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j< grid[0].length; j++){
                if(grid[i][j] =='1'){
                    shrinkTheIsland(grid,i,j,grid.length,grid[0].length);
                    ++count;
                }
            }
        }
        
        return count;
    }
    
    private void shrinkTheIsland(char[][] grid, int i, int j, int row, int col){
        if(i< row && i>= 0 && j>=0 && j<col && grid[i][j] == '1' ){
            grid[i][j] = 0;
            shrinkTheIsland(grid,i+1,j,row,col);
            shrinkTheIsland(grid,i,j+1,row,col);
            shrinkTheIsland(grid,i-1,j,row,col);
            shrinkTheIsland(grid,i,j-1,row,col);
        }
    }
    /**********  BFS Solution Solution TC - O(m*n) SC = O(m*n) que at max contains all element in grid ***************************/
    
    public int numIslandsBFS(char[][] grid) {
        int count = 0;
        Queue<int[]> q = new LinkedList<>();
        int dir[][]  = {{1,0},{0,1},{-1,0},{0,-1}};
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j< grid[0].length; j++){
                if(grid[i][j] =='1'){
                    q.add(new int[] {i,j});
                    grid[i][j] = '0';
                    count++;
                    shrinkRegion(q,grid, dir);
                }
            }
        }
        
        return count;
    }
    
    private void shrinkRegion(Queue<int[]> q ,char[][] grid, int[][] dir) {
        while (!q.isEmpty()) {
            int[] currIndex = q.poll();
            for (int[] d : dir) {
                int x = currIndex[0] + d[0];
                int y = currIndex[1] + d[1];
                if(x>= 0 && y>= 0 && x< grid.length && y< grid[0].length && grid[x][y] =='1'){
                    q.offer(new int[]{x,y});
                    grid[x][y] = '0';
                }
            }
        }
    }
    
    /**********  Union Find Solution TC - O(m*n) SC = O(m*n) ***************************/
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        UnionFind UnionSets = new UnionFind(m * n + 1);
        int[][] directions = {{0, 1}, {1, 0}}; // only check forward and downward directions
        int count =0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    for (int[] d : directions) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '1') {
                            int id1 = i * n + j;   //making 2d matrix to 1d matrix (1,1) in 3*3 matrix  =    1 * 3 + 1  = 4 = > (1,1) middle index will become 4th index in 1D matrix
                            int id2 = x * n + y;
                            if(UnionSets.union(id1, id2)) count-- ;
                        }
                    }
                }
            }
        }
        return count;
    }
    

    
    public static void main(String[] args) {
        M0200_NumberOfIslands cl = new M0200_NumberOfIslands();
        //char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        char[][] grid = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        int res = cl.numIslands(grid);
        System.out.print(res);
    }
}

// Union Find Rank

