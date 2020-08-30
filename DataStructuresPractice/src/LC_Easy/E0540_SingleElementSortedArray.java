package LC_Easy;

public class E0540_SingleElementSortedArray {
//    public int singleNonDuplicate(int[] nums) {
//        if(nums == null || nums.length == 0) return 0;
//        int res = nums[0];
//        for(int i = 1; i< nums.length; i++){
//            res = res^nums[i];
//        }
//
//        return res;
//    }
    
    public int singleNonDuplicate(int[] nums) {
        int l=0;
              int r=nums.length-1;
              int index=binarySearch(nums,l,r);
              if(index<0) throw new IllegalArgumentException();
              return nums[index];
    }
    
    private int binarySearch(int[] nums, int l, int r){
           if(l>r) return -1;
           if(l==r) return l;
           int mid=l+(r-l)/2;
           
           //mid element is single element
           if(nums[mid]>nums[mid-1] && nums[mid]<nums[mid+1]) return mid;
           
           //mid element is the same as the mid+1 element
           if(nums[mid]==nums[mid+1]) {
               //if there are even number of elements on the right including the mid+1 element
               //then the single element must be on the right side
               if((r-mid)%2==0) return binarySearch(nums,mid,r);
               //if there are odd number of elements on the right including the mid+1 element
               //then the single element mus be on the left side
               return binarySearch(nums,l,mid-1);
           }
           
           //mid element is the same as the mid-1 element
           //if there are even number of elements on the left including the mid-1
           //then the single element must be on the left side
           if((mid-l)%2==0) return binarySearch(nums,l,mid);
           //if there are odd number elements on the left including the mid-1 element
           //then the single element must be on the right
           return binarySearch(nums,mid+1,r);
       }
        
        public static void main(String[] args) {
            int[] array  = {3,3,7,7,10,11,11};
            E0540_SingleElementSortedArray cl = new E0540_SingleElementSortedArray();
            int res = cl.singleNonDuplicate(array);
            System.out.println(res);
            
        }
    }
