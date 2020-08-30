package LC_Easy;

import java.util.Arrays;

public class E0283_MoveZeros {
	public void moveZeroes(int[] nums) {
		if(nums == null || nums.length == 0) return;
		int z = 0;  //index points to zero
		int nz = 0;  //index points to non-zero
		
		while (z < nums.length) {
			while (z < nums.length && nums[z] != 0) z++; // search 0 element
			nz = z; // search for next non-zero element after zero because we dont want to replace 0 with its previous non 0 element
			while (nz < nums.length && nums[nz] == 0) nz++;
			if (nz >= nums.length) return;  // shouldn't cross array
			nums[z] = nums[nz];  //replace 0 with non zero element
			nums[nz] = 0;
		}
	}
	public static void main(String[] args) {
		int[] arr = {0,1,0,3,12};
		E0283_MoveZeros cl = new E0283_MoveZeros();
		cl.moveZeroes(arr);
		System.out.println(Arrays.toString(arr));
	}
}
