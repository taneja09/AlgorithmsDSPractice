package LC_Easy;

/**
 *  Time Complexity - O(n)
 *  Reason - only one time traversal
 *
 *  Space Complexity - O(1)
 *  Reason - No extra space required
 */

import java.util.ArrayList;
import java.util.List;

/*
Pascal Triangle
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
 
 think of this as and then solve

 [1]
 [1, 1]
 [1, 2, 1]
 [1, 3, 3, 1]
 [1, 4, 6, 4, 1]
 
 */
public class E0118_PascalTriangle {
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList();
		
		if (numRows == 0) {
			return res;
		}
		
		// Second base case; first row is always [1].
		res.add(new ArrayList<>());
		res.get(0).add(1);
		
		for(int i = 1; i<numRows; i++){
			List<Integer> row = new ArrayList<>();
			List<Integer> prevRow = res.get(i-1);
			row.add(1); //first element
			
			for (int j = 1; j < i; j++)
				row.add(prevRow.get(j-1) + prevRow.get(j));
			
			row.add(1); //last element
			res.add(row);
		}
		
		return res;
	}
	public static void main(String[] args) {
		int row = 5;
		E0118_PascalTriangle cl = new E0118_PascalTriangle();
		List<List<Integer>> result = cl.generate(row);
		for(List<Integer> y : result)
			System.out.println(y);cl.generate(row);
	}
}
