package LC_Medium;

import java.util.Arrays;

public class M0324_WiggleSortII {
    public void wiggleSort(int[] nums) {
        if(nums == null || nums.length==0) return;

        int[] copy = Arrays.copyOf(nums,nums.length);
        Arrays.sort(copy);

        int index = copy.length-1;
        //for odd positions have all the maximum array which are at the end of copy array
        for(int i = 1; i<nums.length ; i+=2) nums[i] = copy[index--]; // fill all large values

        for(int i = 0; i<nums.length ; i+=2) nums[i] = copy[index--]; // fill all small values

    }
    public static void main(String[] args) {
        int[] arr = {1,2,2,1,2,1,1,1,1,2,2,2};
        M0324_WiggleSortII cl = new M0324_WiggleSortII();
        cl.wiggleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
