package LC_Medium;

import java.util.Arrays;
import java.util.HashSet;

public class M0593_ValidSquare {
	/*
	TC = O(1)
	SC = O(1)
	 */
	//*********************** Hash Solution ****************************/
	public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
		HashSet<Integer> set = new HashSet<>();
		if(isSame(p1,p2) || isSame(p2,p3) || isSame(p3,p4) || isSame(p4,p1) ||  isSame(p1,p3) || isSame(p2,p4)) return false;
		
		int y12 = p2[1]-p1[1]; int x12 = p2[0]-p1[0];
		int y23 = p3[1]-p2[1]; int x23 = p3[0]-p2[0];
		int y34 = p4[1]-p3[1]; int x34 = p4[0]-p3[0];
		int y41 = p1[1]-p4[1]; int x41 = p1[0]-p4[0];
		int y13 = p3[1]-p1[1]; int x13 = p3[0]-p1[0];
		int y24 = p4[1]-p2[1]; int x24 = p4[0]-p2[0];
		
		int dp12 =  y12 * y12 + x12 * x12;
		int dp23 =  y23 * y23 + x23 * x23;
		int dp34 =  y34 * y34 + x34 * x34;
		int dp41 =  y41 * y41 + x41 * x41;
		int dp24 =  y24 * y24 + x24 * x24;
		int dp13 =  y13 * y13 + x13 * x13;
		
		
		set.add(dp12);
		set.add(dp23);
		set.add(dp34);
		set.add(dp41);
		set.add(dp24);
		set.add(dp13);
		
		return set.size() == 2;
	}
	
	private boolean isSame(int[] p1, int[] p2){
		return p1[0] == p2[0] && p1[1] == p2[1];
	}
	
	/*
	TC = O(logn)
	SC = O(1)
	 */
	public boolean validSquareEasy(int[] p1, int[] p2, int[] p3, int[] p4) {
		int[][] p = {p1,p2,p3,p4};
		Arrays.sort(p, (a, b) -> a[0] == b[0] ? a[1]-b[1] : a[0]-b[0]);
		return dist(p[0],p[1]) > 0 && dist(p[0],p[1]) == dist(p[1],p[3]) &&  dist(p[1],p[2]) == dist(p[0],p[3]);
	}
	int dist(int[] p1, int[] p2){
		return (p1[1]-p2[1])*(p1[1]-p2[1]) + (p1[0]-p2[0])*(p1[0]-p2[0]);
	}
	
	public static void main(String[] args) {
		int[] p1 = {0,0};
		int[] p2 = {1,1};
		int[] p3 = {1,0};
		int[] p4 = {0,1};
		M0593_ValidSquare cl = new M0593_ValidSquare();
		System.out.println(cl.validSquare(p1,p2,p3,p4));
	}
}
