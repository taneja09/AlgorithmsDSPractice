package LC_Medium;

public class M0670_MaximumSwap {
	public int maximumSwap(int num) {
		char[] digits = Integer.toString(num).toCharArray();
		
		int[] buckets = new int[10];
		for (int i = 0; i < digits.length; i++) {
			buckets[digits[i] - '0'] = i;
		}
		//digits [2,7,3,6]
		//buckets [0,0,0,2,0,4,1,0,0]
		
		for (int i = 0; i < digits.length; i++) {
			for (int k = 9; k > digits[i] - '0'; k--) {
				if (buckets[k] > i) {
					char tmp = digits[i];
					digits[i] = digits[buckets[k]];
					digits[buckets[k]] = tmp;
					return Integer.valueOf(new String(digits));
				}
			}
		}
		
		return num;
	}
	
	public static void main(String[] args) {
		int s = 2736;
		M0670_MaximumSwap cl = new M0670_MaximumSwap();
		System.out.println(cl.maximumSwap(s));
	}
}
