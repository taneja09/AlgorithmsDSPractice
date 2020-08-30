package LC_Easy;

public class E1524_SubArraySum {
	int sum =0;
	public int numOfSubarrays(int[] arr) {
		int count = 0;
		gerHelperMethod(count, arr, 0);
		return sum;
	}
	
	private void gerHelperMethod(int count, int[] arr, int index){
		if(count%2 != 0){
			sum++;
		}
		for (int i = index; i < arr.length; i++) {
			gerHelperMethod(count + arr[i],arr, i + 1);
		}
	}
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7};
		E1524_SubArraySum cl = new E1524_SubArraySum();
		System.out.println(cl.numOfSubarrays(arr));
	}
}
