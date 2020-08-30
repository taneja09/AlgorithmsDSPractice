package LC_Medium;
/*
// if p1 and p2 can be diagonal
                    // see if rectangle can be formed, that is, p3 and p4 exists as below:
                    // p1 -- p3
                    //  |    |
                    // p4 -- p2
                    //
                    // p3 and p4 needs to satisfy:
                    //      p3.x = p2.x, p3.y = p1.y
                    //      p4.x = p1.x, p4.y = p2.y
 */
import java.util.*;

public class M0939_MinimumAreaRectangle {
	public int minAreaRect(int[][] points) {
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int[] p : points) {
			if (!map.containsKey(p[0])) {
				map.put(p[0], new HashSet<>());
			}
			map.get(p[0]).add(p[1]);
		}
		int min = Integer.MAX_VALUE;
		
		for (int[] p1 : points) {
			for (int[] p2 : points) {
				if (p1[0] == p2[0] || p1[1] == p2[1]) { // if have the same x or y
					continue;
				}
				if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) { // find other two points
                    min = Math.min(min, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                }
			}
		}
		return min == Integer.MAX_VALUE ? 0 : min;
	}
	public static void main(String[] args) {
		int[][] points = {{1,1},{1,3},{3,1},{3,3},{2,2}};
		M0939_MinimumAreaRectangle cl = new M0939_MinimumAreaRectangle();
		System.out.println(cl.minAreaRect(points));
	}
}
