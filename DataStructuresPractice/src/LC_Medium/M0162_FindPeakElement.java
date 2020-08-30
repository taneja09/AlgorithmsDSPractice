package LC_Medium;

public class M0162_FindPeakElement {
	/*=================Linear Scan TC O(n) =========================*/
	public int findPeakElement(int[] nums) {
		if(nums == null || nums.length ==0 ) return 0;
		if(nums.length == 1) return 0;
		int len = nums.length-1;
		for(int i = 0; i<nums.length; i++){
			if(i == 0 && nums[i] > nums[i+1]) return i;
			if(i == len && nums[i] > nums[i-1]) return i;
			else if(nums[i] > nums[i+1] && nums[i] > nums[i+1]) return i;
		}
		return -1;
	}
	
	/*================= Linear Scan TC O(n) =========================*/
	public int findPeakElementEasy(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] > nums[i + 1])
				return i;
		}
		return nums.length - 1;
	}
	/*================= TC O(log n) SC O(log n) =========================*/
	public int findPeakElementBinary(int[] nums) {
		int start = 0;
		int end = nums.length -1;
		
		return getResult(nums, start, end);
	}
	
	private int getResult(int[] nums, int start, int end){
		if(start == end) return start;
		int mid = start + (end - start)/2;
		if (nums[mid] > nums[mid + 1])  //
			return getResult(nums,start, mid);
		
		else return getResult(nums,mid+1, end);
	}
	
	/*================= TC O(log n) SC O(1) =========================*/
	public int findPeakElementBinaryIterative(int[] nums) {
		int start = 0, end = nums.length-1;
		
		while(start < end){
			int mid = start + (end+start)/2;
			if (nums[mid] > nums[mid + 1]) //check at the left hand side as well
				end = mid;
			else start = mid+1;  //check at right hand side
		}
		return start;
	}
	
	public static void main(String[] args) {
		int[] array = {4,5,3,1};
		M0162_FindPeakElement cl = new M0162_FindPeakElement();
		System.out.println(cl.findPeakElement(array));
		System.out.println(cl.findPeakElementEasy(array));
		System.out.println(cl.findPeakElementBinary(array));
		System.out.println(cl.findPeakElementBinaryIterative(array));
	}
}

/**
 Three options - Binary search
 
 1. decsending slope
 nums[i] > nums[i+1] hence return peak as nums[0]
 
 2. Ascending slope
 nums[i] < nums[i+1]  hence return peak as nums[length-1]
 
 3. where we actually have peak somewhere within array and not at edges /\
 hence keep on scanning as soon as nums[i] > nums[i+1] return nums[i] because after that sequence decreases
 */