package LC_Medium;
/**
 
 Time Complexity - O(n)
 Reason - traversal required for complete array
 
 Space Complexity - O(n)
 Reason -  space for result
*/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/*
Pick out tallest group of people and sort them in a subarray (S). Since there's no other groups of people taller than them,
therefore each guy's index will be just as same as his k value.

For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.

input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
subarray after step 1: [[7,0], [7,1], [6,1], [5,0], [5,2], [4,4]]

subarray after step 2: [[7,0], [6,1], [7,1]] //look at first three element of step1 array , while adding [6,1] it goes to p[1] index = 1
subarray after step 2: [[5,0], [7,0], [6,1], [7,1]]  added [5,0] at index p[1] = 0
subarray after step 2: [[5,0], [7,0], [5,2] [6,1], [7,1]] added [5,2] at index p[1] = 2
subarray after step 2: [[5,0], [7,0], [5,2] [6,1], [4,4], [7,1]] added [4,4] at index p[1] index 4
 */


public class M0406_QueueReconstructionByHeight {
	public int[][] reconstructQueue(int[][] people) {
    	 Arrays.sort(people,(a,b)->a[0] == b[0] ? a[1]-b[1]: b[0]-a[0]);
		 List<int[]> list = new LinkedList<>();
		for (int[] p : people) {
			list.add(p[1], p);
		}
		return list.toArray(new int[list.size()][]);
    }
	public static void main(String[] args) {
		M0406_QueueReconstructionByHeight cl = new M0406_QueueReconstructionByHeight();
		int[][] arr = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
		int[][] res = cl.reconstructQueue(arr);
		for(int[] p : res)
		System.out.println(Arrays.toString(p));
	}
}
