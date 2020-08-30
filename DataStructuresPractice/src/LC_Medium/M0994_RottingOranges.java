package LC_Medium;

import java.util.LinkedList;
import java.util.Queue;

public class M0994_RottingOranges {
	public int orangesRotting(int[][] grid) {
		int fresh = 0;
		int minutes = 0;
		Queue<int[]> que = new LinkedList<>();
		int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};
		for(int i = 0; i< grid.length; i++){
			for(int j = 0; j<grid[0].length; j++){
				if(grid[i][j] == 1) fresh++;
				if(grid[i][j] == 2) que.add(new int[]{i,j});
			}
		}
		if(fresh == 0) return 0;
		while(!que.isEmpty() && fresh > 0) {
			int size = que.size();
			for (int i = 0; i < size; i++) {
				int[] current = que.poll();
				for (int[] d : dir) {
					int x = current[0] + d[0];
					int y = current[1] + d[1];
					if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0 || grid[x][y] == 2)
						continue;
					else {
						grid[x][y] = 2;
						que.add(new int[]{x, y});
						fresh--;
					}
				}
			}
			minutes++;
		}
		return fresh == 0 ? minutes : -1;
	}
	public static void main(String[] args) {
		int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
		M0994_RottingOranges cl = new M0994_RottingOranges();
		System.out.println(cl.orangesRotting(grid));
	}
}
