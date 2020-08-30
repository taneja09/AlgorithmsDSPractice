package LC_Hard;

import java.util.Stack;

/*
https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/452612/Thinking-Process-for-Stack-Solution
https://www.youtube.com/watch?v=VNbkzsnllsU

index of right boundary = i-1
index of left boundary = stack.peek()+1

width of rectangle = right boundary - left boundary + 1
					= i -1 - (st.peek() +1) +1;
					= i - st.peek -1
 */
public class H0084_LargestRectangleInHistogram {
	public int largestRectangleArea(int[] heights) {
		int len = heights.length;
		Stack<Integer> s = new Stack<>();
		int maxArea = 0;
		for (int i = 0; i <= len; i++){
			int h = i == len ? 0 : heights[i];
			if (s.isEmpty() || h >= heights[s.peek()]) {
				s.push(i);
			}else{
				int top = s.pop();
				maxArea = Math.max(maxArea, heights[top] * (s.isEmpty() ? i : i - 1 - s.peek()));
				i--;
			}
		}
		return maxArea;
	}
	
	/************** Brute Force O(n^2) ********************/
	public int largestRectangleAreaBF(int[] heights) {
		int len = heights.length, maxArea = 0;
		for (int i = 0; i < len; i++) {
			if (i + 1 < len && heights[i] <= heights[i + 1]) {  //if next height is greater keep on moving
				continue;
			}
			int minHeight = Integer.MAX_VALUE;
			// found local maximum height
			for (int j = i; j >= 0; j--) {  //from current index towards the left... i= right index & j is left index
				minHeight = Math.min(minHeight, heights[j]);
				maxArea = Math.max(minHeight * (i - j + 1), maxArea);   //i-j+1 width
			}
		}
		
		return maxArea;
	}
		public static void main(String[] args) {
			int[] histogram = {2,1,5,6,2,3};
			H0084_LargestRectangleInHistogram cl = new H0084_LargestRectangleInHistogram();
			System.out.println(cl.largestRectangleArea(histogram));
		}
	}
