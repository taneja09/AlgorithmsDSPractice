package LC_Hard;
/*
https://www.youtube.com/watch?v=LPFhl65R7ww
https://www.youtube.com/watch?v=MHNTl_NvOj0
*/
public class H0004_MedianOfSortedArrays {
	
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		// make sure m <= n
		if (m > n)
			return findMedianSortedArrays(nums2, nums1);  // always apply binary shifts to shorter array that's y we take first array as shorter one
		int imin = 0, imax = m;
		
		while (imin <= imax) {
			int i = imin + (imax - imin) / 2;  //middle element
			int j = (m + n + 1) / 2 - i;  //because i+j = (m+n+1)/2  // partition x + partition y = (m+n+1)/2
			
			int nums1_left = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
			int nums1_right = i == m ? Integer.MAX_VALUE : nums1[i];
			int nums2_left = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
			int nums2_right = j == n ? Integer.MAX_VALUE : nums2[j];
			
			if (nums1_left > nums2_right) {
				imax = i - 1;    //shift towards left
			} else if (nums2_left > nums1_right) {
				imin = i + 1;
			} else {
				int max_left = nums1_left > nums2_left ? nums1_left : nums2_left;
				int min_right = nums1_right > nums2_right ? nums2_right : nums1_right;
				if ((m + n) % 2 == 1)  return max_left; //total length is odd
				else return (max_left + min_right) / 2.0;  //total length is even
			}
		}
		
		return -1;
	}
			public static void main(String[] args) {
				int[] x = {1,1,3};
				int[] y = {2};
				H0004_MedianOfSortedArrays cl = new H0004_MedianOfSortedArrays();
				System.out.println(cl.findMedianSortedArrays(x,y));
				
			}
		}
