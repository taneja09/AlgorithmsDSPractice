package LC_Easy;
import java.util.Arrays;
/**
 
 Time Complexity - O(m+n)
 Reason - total number of elements traversal
 
 Space Complexity - O(1)
 Reason -  constant space
*/
public class E0088_MergeSortedArray {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int k = m+n-1;
		m = m-1;
		n = n-1;
		while(m>=0 && n>= 0){
			if(nums1[m] >= nums2[n]) {
				nums1[k] = nums1[m];
				k--;
				m--;
			}else {
				nums1[k] = nums2[n];
				n--;
				k--;
			}
		} while(n>=0){
			nums1[k] = nums2[n];
			k--; n--;
		}
	}
	
	public static void main(String[] args) {
		int[] nums1 = {2,0};
		int[] nums2 = {1};
		int m = 1, n=1;
		E0088_MergeSortedArray cl = new E0088_MergeSortedArray();
		cl.merge(nums1,m,nums2,n);
		System.out.println(Arrays.toString(nums1));
	}
}
