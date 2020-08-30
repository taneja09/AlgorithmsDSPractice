package Contests;

import java.math.BigInteger;

public class Q1 {
	public int getMaxLen(int[] nums) {
		BigInteger max = new BigInteger("0");
		int len = 0;
		BigInteger sum = new BigInteger("0");
		boolean pos = false;
		
		for(int i = 0; i< nums.length; i++){
			if(nums[i] == 0) continue;
			  sum = BigInteger.valueOf(nums[i]);
			  if(nums[i] > 0 && !pos) pos = true;
			for(int j = i+1; j< nums.length; j++) {
				sum = sum.multiply(BigInteger.valueOf(nums[j]));
				if(sum.compareTo(max) > 0 ){
					if(len < j - i + 1){
						len = j - i + 1;
						System.out.println("index" + " " + j + " " + i);
					}
					
				}
				System.out.println(sum + " " + len);
			}
		}
		return len > 0 ? len : ( pos  ? 1 : 0);
	}
	public static void main(String[] args) {
		Q1 obj = new Q1();
		int[] arr = {70,-18,75,-72,-69,-84,64,-65,0,-82,62,54,-63,-85,53,-60,-59,29,32,59,-54,-29,-45,
			0,-10,22,42,-37,-16,0,-7,-76,-34,37,-10,2,-59,-24,85,45,-81,56,86};
		System.out.println(obj.getMaxLen(arr));
	}
}
