package LC_Hard;
/*
Numbers greater then n can be ignored because the missing integer must be in the range 1..n+1

If each cell in the array were to contain positive integers only, we can use the negative of the stored number
as a flag to mark something
(in this case the flag indicates this index was found in some cell of the array)
 */
public class H0041_SmallestMissingPositiveNumber {
	public int firstMissingPositive(int[] nums) {
		int n = nums.length;
		
		//mark numbers (num < 0) and (num > n) with a special marker number (n+1)
		// (we can ignore those because if all number are > n then we'll simply return 1)
		for (int i = 0; i < n; i++)
			if (nums[i] <= 0 || nums[i] > n) nums[i] = n + 1;
		
		//mark each cell appearing in the array, by converting the index for that number to negative
		for (int i = 0; i < n; i++) {
			int val = Math.abs(nums[i]) - 1;
			if (val >= n) continue;
			if (nums[val] > 0) nums[val] = -1 * nums[val];
		}
		// 3. find the first cell which isn't negative (doesn't appear in the array)
		for (int i = 0; i < n; i++) {
			if (nums[i] >= 0) {
				return i + 1;
			}
		}
		// no positive numbers were found, which means the array contains all numbers 1..n
		return n + 1;
	}
	public static void main(String[] args) {
		int[] arr = {7,8,9,11,12};
		H0041_SmallestMissingPositiveNumber cl = new H0041_SmallestMissingPositiveNumber();
		System.out.println(cl.firstMissingPositive(arr));
	}
}
