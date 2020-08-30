package LC_Medium;
import java.util.Arrays;
/**
 Time Complexity - O(n)
 Reason - Accessing all elements in one pass
 
 Space Complexity - O(1)
 Reason - No extra space
 */

public class M0075_SortColours {
	
	public void sortColors(int[] nums) {
		int r = 0, b = nums.length-1;
		int i  = 0;
		while(i <= b){
			if (nums[i] == 0) {
				int temp = nums[r];
				nums[r] = nums[i];
				nums[i] = temp;
				r++;
				i++;
			}else if(nums[i] == 2){
				int temp = nums[b];
				nums[b] = nums[i];
				nums[i] = temp;
				b--;
			}else i++;
		}
	}
	
	/******* Counting Sort - Brute Force TC - O(n+k) where k is range 3 (0-2 in this case) SC = O(n+k) *****/
	public void sortColorsBrute(int[] nums) {
		int red = 0, white =0, blue =0;
		
		for(int i : nums){
			if(i==0) red++;
			if(i==1) white++;
			if(i==2) blue++;
		}
		
		int k = 0;
		while(k<nums.length){
			if(red>0) {nums[k] = 0; red--;}
			else if( white>0) {nums[k] = 1; white--;}
			else {nums[k] =2; blue--;}
			k++;
		}
	}
	public static void main(String[] args) {
		int[] array = {1,0,2,1,1,0};
		M0075_SortColours cl = new M0075_SortColours();
		//cl.sortColorsBrute(array);
		cl.sortColors(array);
		System.out.println(Arrays.toString(array));
	}
}
