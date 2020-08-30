package LC_Medium;
/**
 
 Time Complexity - O(m)
 Reason - traversal required for whole array
 
 Space Complexity - O(1)
 Reason - Constant space
*/
/* This question can also be solved using Hash and Sorting algorithms */
public class M0287_FindDuplicates {

    public int findDuplicate(int[] nums) {

        int slowPointer = nums[0];
        int fastPointer = nums[0];

        do{
            slowPointer = nums[slowPointer];
            fastPointer = nums[nums[fastPointer]];
        } while(slowPointer!=fastPointer);

        fastPointer = nums[0];
        
        while(slowPointer != fastPointer){
            slowPointer = nums[slowPointer];
            fastPointer = nums[fastPointer];
        }

        return slowPointer;
    }

    public static void main(String[] args) {

        M0287_FindDuplicates cl = new M0287_FindDuplicates();
        int[] array = {1,3,4,2,2};
        int res = cl.findDuplicate(array);
        System.out.print(res);
    }
}
/*
   index =   [0,1,2,3,4]
   Example = [1,3,4,2,2]

 cycle diagram : start from nums[0] = 1 , from 1 index -> 3, from index 3 --> 2 , from index 2 --> 4, from index 4 -> 2 hence there is cycle in 3 2 4

 1 ===>  3 ===> 2 ===>4 ===> 2  cycle between  2-4-2

slowPointer = 1
fastPointer = 1

Do-while loop
 slowPointer = nums[slowPointer]; = 1, 3, 2  (index here was 3 which ultimately went to 2)
 fastPointer = nums[nums[fastPointer]]; = 1, 2, 2   (index here was 4 which ultimately went to 2)

 slowPointer = nums[0]; = 1
 fastPointer = 2

 while loop
  slowPointer = nums[slowPointer]; 1,3,2
  fastPointer = nums[fastPointer]; 2,4,2   cycle starts at 2 and hence that's the duplicate

 **** why the first intersection is not the ans?
 intersection might not be the correct answer, only the entry of the loop means that there are at least 2 pointers point to it.

 */