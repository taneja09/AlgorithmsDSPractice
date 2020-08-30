package LC_Hard;

import java.util.Arrays;
import java.util.HashSet;

public class H0128_LongestConsecutiveSequence {
	/******* Optimized O (n)  SC = O(n) ******************/
	public int longestConsecutive(int[] nums) {
		HashSet<Integer> hs = new HashSet<>();
		for(int num : nums){
			hs.add(num);
		}
		
		int longestStreak = 0;
		
		for(int num : nums) {
			if (!hs.contains(num - 1)) {   //This is accomplished by first ensuring that the number that would immediately
				// precede the current number in a sequence is not present, as that number would necessarily be part of a longer sequence.
				int currentStreak = 1;
				while (hs.contains(num + 1)) {
					currentStreak += 1;
					num += 1;
				}
				longestStreak = Math.max(longestStreak, currentStreak);
			}
		}
		return longestStreak;
	}
	
	
	
	/******* Sorting O (nlogn) ******************/
	public int longestConsecutiveSort(int[] nums) {
		if(nums.length == 0 || nums == null) return 0;
		Arrays.sort(nums);
		int longestStreak = 1;
		int currStreak = 1;
		
		for(int i = 1; i< nums.length; i++){
			if(nums[i] != nums[i-1] ){
				if(nums[i] == nums[i-1]+1) currStreak++;
				else {
					longestStreak = Math.max(longestStreak, currStreak);
					currStreak = 1;
				}
			}
		}
		
		return Math.max(longestStreak, currStreak);
	}

	/******* TLE O(n^3)******************/
	public int longestConsecutiveBF(int[] nums) {
		int longestStreak = 0;
		
		for(int num : nums){
			int currentStreak = 1;
			int currentNum = num;
			while (arrayContains(nums, currentNum + 1)) {
				currentStreak += 1;
				currentNum += 1;
			}
			longestStreak = Math.max(longestStreak,currentStreak);
		}
		return longestStreak;
	}
	
	private boolean arrayContains(int nums[] , int num){
		for(int x: nums){
			if(x == num) return true;
		}
		return false;
	}
	public static void main(String[] args) {
		int[] arr = {100,4,200,1,3,2};
		H0128_LongestConsecutiveSequence cl = new H0128_LongestConsecutiveSequence();
		System.out.println(cl.longestConsecutive(arr));
	}
}
