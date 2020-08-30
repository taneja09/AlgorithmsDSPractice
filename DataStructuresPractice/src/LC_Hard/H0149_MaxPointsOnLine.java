package LC_Hard;

import java.util.*;

public class H0149_MaxPointsOnLine {
	public int maxPoints(int[][] points) {
		// <x, <y, count>>  where x and y are gcd ,
		// we cant store double for slope hence we calculate gcd of x and y and make it as small as possible
		//then we save that into the hashmap and if again that x,y value comes , we increase the count in hashmap
		HashMap<Integer, Map<Integer,Integer>> map = new HashMap<>();
		int result=0;
		for(int i = 0; i<points.length; i++) {  //calculate slope from 1 point to all others hence 2 for loops
			int overlap = 0, max = 0;
			map.clear();
			for (int j = i + 1; j < points.length; j++) {
				int y = points[j][1] - points[i][1];
				int x = points[j][0] - points[i][0];
				if (x == 0 && y == 0) {  // for duplicate points [0,0][0,0] they lie on same spot and we account for that at the end
					overlap++;
					continue;
				}
				int gcd = generateGCD(x, y);
				if (gcd != 0) {
					x /= gcd;
					y /= gcd;
				}
				if (map.containsKey(x)) {
					if (map.get(x).containsKey(y)) {
						map.get(x).put(y, map.get(x).get(y) + 1);
					} else {
						map.get(x).put(y, 1);
					}
				} else {
					Map<Integer, Integer> m = new HashMap<Integer, Integer>();
					m.put(y, 1);
					map.put(x, m);
				}
				max = Math.max(max, map.get(x).get(y));
			}
			result = Math.max(result, max + overlap + 1);  // maximum points with same slope + current point(1) + overlap (same points)
		}
		return result;
	}
	
	private int generateGCD(int a,int b){
		if (b==0) return a;
		else return generateGCD(b,a%b);
	}
	
	public static void main(String[] args) {
		int[][] points = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
		H0149_MaxPointsOnLine cl = new H0149_MaxPointsOnLine();
		System.out.println(cl.maxPoints(points));
		
	}
}
