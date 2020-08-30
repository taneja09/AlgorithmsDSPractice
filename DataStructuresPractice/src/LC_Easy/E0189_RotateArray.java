package LC_Easy;
import java.util.Arrays;
public class E0189_RotateArray {
	
	/**************** Reversing TC = O(n) SC = O(1) ***************/
	/*
	1. After reversing all numbers = 7 6 5 4 3 2 1
	2. reversing first k numbers = 5 6 7 4 3 2 1
	3. Reversing n-k numbers = 5 6 7 1 2 3 4
	 */
	public void rotateConstantSpace(int[] nums, int k) {
		int len = nums.length;
		k%=len;  //in case of [1,2] , 3 => k > len 3/2 = 1 rotate by 1 not by 3
		reverse(nums,0, len-1);
		reverse(nums,0, k-1);
		reverse(nums,k, len-1);
	}
	
	private void reverse(int[] nums, int start, int end){
		while(start < end){
			int temp = nums[start];
			nums[start] = nums[end];
			nums[end] = temp;
			start++;
			end--;
		}
	}
	/**************** extra Space TC = O(n) SC = O(n) ***************/
	public void rotate(int[] nums, int k) {
		int[] a = new int[nums.length];
		int len = nums.length;
		for(int i = 0; i<nums.length && k >= 0; i++){
			a[(k+i)%len] = nums[i];
		}
		
		for(int i = 0; i< nums.length; i++)
			nums[i] = a[i];
	}
	
	
	public static void main(String[] args) {
		int[] array = {1,2,3,4,5,6,7};
		E0189_RotateArray cl = new E0189_RotateArray();
		//cl.rotate(array,3);
		cl.rotateConstantSpace(array,3);
		System.out.println(Arrays.toString(array));
	}
}
