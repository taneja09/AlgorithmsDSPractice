package LC_Hard;

import java.util.Stack;

/*
Very similar to LC 84
calculate height row by row , to find largest rectangle
for each row => matrix[row][i] == '1'. H[i] +=1, or reset the H[i] to zero.
 */
public class H0085_MaximalRectangle {
	public int maximalRectangle(char[][] matrix) {
		int rLen = matrix.length;
		int cLen = rLen == 0 ? 0 : matrix[0].length, max = 0;
		// height array
		int[] h = new int[cLen+1];
		
		for (int row=0;row<rLen;row++) {
			Stack<Integer> s = new Stack<Integer>();
			s.push(-1);
			for (int i=0; i <= cLen; i++) {
				if (i<cLen && matrix[row][i]=='1') h[i]+=1;
				else h[i]=0;
				
				while(s.peek() != -1 && h[i] < h[s.peek()] ){
					int top = s.pop();
					max = Math.max(max, h[top]* (i-s.peek()-1));  //similar to LC 84
				}
				s.push(i);
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		char[][] matrix = {
			{'1', '0', '1', '0', '0'},
			{'1', '0', '1', '1', '1'},
			{'1', '1', '1', '1', '1'},
			{'1', '0', '0', '1', '0'}};
		H0085_MaximalRectangle cl = new H0085_MaximalRectangle();
		System.out.println(cl.maximalRectangle(matrix));
	}
}
