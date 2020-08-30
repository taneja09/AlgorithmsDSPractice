package Interviews.Palantir;
import Algorithms.Graphs.UnionFind;

import java.util.*;

public class FloodMap {
	public ArrayList<Integer> getBasinsCount(int[][] map){
		int m = map.length;
		int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
		UnionFind set = new UnionFind(m*m );
		
		for(int i = 0; i < m; i++){
			for(int j = 0; j < m; j++){
				int lowestX = i;
				int lowestY = j;
				for(int[] d : dir){
					int x =  d[0] + i;
					int y =  d[1] + j;
					if(x >= 0 && x < m && y >= 0 && y < m && map[x][y] < map[lowestX][lowestY]){
						lowestX = x;
						lowestY = y;
					}
				}
				int id1 = i * m + j;
				int id2 = lowestX * m + lowestY;
				if(id1 != id2) set.union(id1, id2);
			}
		}
		
		HashMap<Integer, Integer> hm = new HashMap<>(); //key and count
		for (int i = 0; i < set.parents.length; i++) {
			 int key = set.find(set.parents[i]);
			 hm.put(key,hm.getOrDefault(key,0)+1);
		}
		
		ArrayList<Integer>  al = new ArrayList<>();
		for(int val : hm.values()) al.add(val);
		
		return al;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		
		int[][] map = new int[size][size];
		for(int i = 0 ; i< size; i++){
			for(int j = 0; j<size; j++){
				map[i][j] = sc.nextInt();
			}
		}
		
		FloodMap obj = new FloodMap();
		System.out.println(obj.getBasinsCount(map));
		
	}
}
