package LC_Medium;
/*
Assign values to val1 & val2
1. Assign 1 to val1 as 1 < maxvalue
2. If next is also 0 then val1 be reassigned to 0 as 0<1 .. the idea is to assign minimum-est value to val1
3. then next value will be greater than val1 and assign to val2
4. if incoming value is greater than val1 and val2 both first two conditions wll be bypassed and true is returned
 */

/**
 
 Time Complexity - O(n)
 Reason - traversal required for complete array
 
 Space Complexity - O(1)
 Reason - Constant space
*/

public class M0334_IncreasingTripletSeq {
	public boolean increasingTriplet(int[] nums) {
		
		int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
		for (int n : nums) {
			if (n <= small) { small = n; } // update small if n is smaller than both
			else if (n <= big) { big = n; } // update big only if greater than small but smaller than big i.e lies between small and big small number ** Int_max value
			else return true; // return if you find a number bigger than both
			
		}
		return false;
	}
	
	public boolean increasingTripletEasy(int[] nums) {
		if (nums ==  null || nums.length < 3 ){
           return false;
       }
       int min = Integer.MAX_VALUE;
       int secondMin = Integer.MAX_VALUE;
       
       for (int i = 0; i < nums.length; i++){
           if (nums[i] <= min){
               min = nums[i];
           }
           else if (nums[i] <= secondMin){
               secondMin = nums[i];
           }
           else {
               return true;
           }
       }
       return false;
    }
	public static void main(String[] args) {
		int[] array = {5,4,3,2,1};
		M0334_IncreasingTripletSeq cl = new M0334_IncreasingTripletSeq();
		//boolean res = cl.increasingTriplet(array);
		boolean res = cl.increasingTripletEasy(array);
		System.out.println(res);
		
	}
}
