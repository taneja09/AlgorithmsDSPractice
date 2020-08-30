package LC_Medium;

import java.util.NoSuchElementException;
import java.util.Stack;

public class M0251_Flatten2DVector {
	Stack<Integer> st = new Stack<>();
	int[][] vec ;
	private int inner = 0;  //inside particular column
 	private int outer = 0;  //  row
	/************** 2 Pointer TC = O(n) and SC = O(1) *******************/
	public M0251_Flatten2DVector(int[][] v) {
		vec = v;
	}
	private void advanceToNext() {
		//as soon as columns crosses length start from next row
		while (outer < vec.length && inner == vec[outer].length) {
            inner = 0;
            outer++;
        }
	}
	public int next() {
		if (!hasNext()) throw new NoSuchElementException();
		return vec[outer][inner++];
	}
	
	public boolean hasNext() {
		advanceToNext();
		return outer < vec.length;
	}
	
	
	/************** Brute Force TC = O(n) and SC = O(n) *******************/
	public void M0251_Flatten2DVector(int[][] v) {
		this.vec = v;
		resolveVectorBF(vec);
	}
	
	private void resolveVectorBF(int[][] vec){
		for (int i = vec.length-1; i>=0; i--) {
			if (vec[i] != null) {
				for (int j = vec[i].length - 1; j >= 0; j--)
					st.push(vec[i][j]);
			}
		}
	}
	
	public int nextBF() {
		return st.isEmpty() ? -1 : st.pop();
	}
	
	public boolean hasNextBF() {
		return !st.isEmpty();
	}
	
	public static void main(String[] args) {
		int[][] vector = {{},{},{-1}};
		M0251_Flatten2DVector cl = new M0251_Flatten2DVector(vector);
		System.out.println(cl.next());
		System.out.println(cl.next());
		System.out.println(cl.next());
		System.out.println(cl.hasNext());
		System.out.println(cl.hasNext());
		System.out.println(cl.next());
		System.out.println(cl.hasNext());
	}
}
