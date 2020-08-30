package LC_Easy;

import java.util.HashMap;

/**
 * Moore’s Voting Algorithm.
 * https://www.geeksforgeeks.org/majority-element/
 *
 * Step 1: Find candidate:
 * Basic idea of the algorithm is that if each occurrence of an element e
 * can be cancelled with all the other elements that are different from e then e will exist till end
 * if it is a majority element.
 *
 * Step 2: Check if the element obtained in step 1 is majority element or not.
 * Traverse through the array and check if the count of the element found is greater than half the size of the array,
 * then print the answer else print “No majority element”.
 */
public class E0169_MajorityElement {

    /********** Boyer-Moore Voting Algorithm ************************/
   	/*
   	[7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
   	take first 7 at index 0 as candidate
   	+1 for every 7 and -1 if its not 7
   	once it becomes 0 (here at 1 index 5 count become 0 ) , then take next element as candidate and start counting again
   	 */
   	public int majorityElementBMV(int[] nums) {
   		int count = 0;
   		Integer candidate = null;
   		
   		for(int num : nums){
   			if(count == 0){
   				candidate = num;
   			}
   			count+= num == candidate ? 1 : -1;
   		}
   		
   		return candidate;
   	}
    
    /***************** HashMap TC -O(n) SC -O(n) *************/
   	public int majorityElement(int[] nums) {
   		int len = nums.length/2;
   		HashMap<Integer,Integer> hm = new HashMap<>();
   		for(int num: nums){
   			if(hm.containsKey(num)){
   				int count = hm.get(num);
   				count++;
   				if(count > len) return num;
   				else hm.put(num,count);
   			}else
   				hm.put(num,1);
   		}
   		
   		for(int key : hm.keySet()){
   			if(hm.get(key) > len/2) return key;
   		}
   		
   		return -1;
   	}

    public static void main(String[] args) {

        E0169_MajorityElement cl = new E0169_MajorityElement();
        int[] array = {2,2,1,1,1,2,2};
        int res = cl.majorityElement(array);
        System.out.println(res);
        System.out.println(cl.majorityElementBMV(array));
    }
}
