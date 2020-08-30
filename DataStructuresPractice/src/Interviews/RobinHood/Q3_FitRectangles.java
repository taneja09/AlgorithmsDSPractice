package Interviews.RobinHood;

import java.util.*;

public class Q3_FitRectangles {
	public Boolean[] checkFitRectangles(int[][] queries){
		ArrayList<Integer> rectangleAreas = new ArrayList<>();
		
		int zeroOps = 0;
		for(int[] x : queries){
			if(x[0] == 0){
				rectangleAreas.add(x[1] * x[2]);
				zeroOps++;
			}
		}
		
		int queriesCount = queries.length - zeroOps;
		int j = 0;
		Boolean[] res  = new Boolean[queriesCount];
		for(int[] x : queries) {
			if (x[0] == 1) {
				for (int rec : rectangleAreas) {
					if (x[1] * x[2] < rec){
						res[j] = false;
						break;
					}
				}
				if(res[j] == null) res[j] = true;
				j++;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[][] queries = {{0,1,3},{0,4,2},{1,3,4},{1,3,2}};
		Q3_FitRectangles cl = new Q3_FitRectangles();
		System.out.println(Arrays.toString(cl.checkFitRectangles(queries)));
	}
}
