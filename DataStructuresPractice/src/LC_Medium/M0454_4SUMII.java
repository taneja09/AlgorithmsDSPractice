    package LC_Medium;

/**
 * It doesn't matter which pair of arrays you choose to store in the hash map (A, B), (B, D), etc.
 * Think about the solution where you have 4 for-loops to compare all the sums.
 *
 *
 *   for (int a = 0; a < A.size(); a++)
 *         for (int b = 0; b < B.size(); b++)
 *             for (int c = 0; c < C.size(); c++)
 *                 for (int d = 0; d < D.size(); d++)
 *                      if (A[a] + B[b] + C[c] + D[d] == 0)
 *                         ...
 *
 *  We can replace that inner if-statement with something like:
 *   if (A[a] + B[b] == C[c] + D[d])
 *
 *   A[a] + B[b] == -1 *(C[c]+ D[d])  for 0 target
 *   that's what we  wre doing using hashmap & everytime we find result we count it and return
 *
 */
    import java.util.HashMap;

    public class M0454_4SUMII {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        HashMap<Integer, Integer> hm = new HashMap<>();

        for(int i = 0; i<A.length; i++){
            for(int j = 0; j<B.length ; j++){
                int sum = A[i]+B[j];
                hm.put(sum, hm.getOrDefault(sum,0)+1);
            }
        }

        int result = 0;
        for(int i = 0; i<C.length; i++){
            for(int j = 0; j<D.length ; j++){
                result +=  hm.getOrDefault(-1 * (C[i]+D[j]),0);
            }
        }
         return result;
        }
        public static void main(String[] args) {
          int[] A = {1,2};
          int[] B = {-2,-1};
          int[] C = {-1,2};
          int[] D = {0,2};
          M0454_4SUMII cl = new M0454_4SUMII();
          int res = cl.fourSumCount(A,B,C,D);
          System.out.println(res);

        }

    }
