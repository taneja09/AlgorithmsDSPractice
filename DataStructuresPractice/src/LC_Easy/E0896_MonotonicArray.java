package LC_Easy;

public class E0896_MonotonicArray {
	public boolean isMonotonic(int[] A) {
		return increasing(A) || decreasing(A);
	}
	
	public boolean increasing(int[] A) {
		for (int i = 0; i < A.length - 1; ++i)
			if (A[i] > A[i+1]) return false;
		return true;
	}
	
	public boolean decreasing(int[] A) {
		for (int i = 0; i < A.length - 1; ++i)
			if (A[i] < A[i+1]) return false;
		return true;
	}

	/* ========================================================= */
	public boolean isMonotonicEasy(int[] A) {
       boolean increasing = true;
       boolean decreasing = true;
       for (int i = 0; i < A.length - 1; ++i) {   // if its monotonic either increase or decrease variable has to be left true
           if (A[i] > A[i+1])
               increasing = false;
           if (A[i] < A[i+1])
               decreasing = false;
       }

       return increasing || decreasing;
   }
	
	
	public static void main(String[] args) {
		int[] arr= {1,1,1};
		E0896_MonotonicArray cl = new E0896_MonotonicArray();
		System.out.println(cl.isMonotonic(arr));
	}
}
