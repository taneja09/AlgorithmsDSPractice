package LC_Hard;

public class H0072_EditDistance {
	public int minDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
		
		int[][] DP = new int[len2+1][len1+1];
		
		DP[0][0] = 0;
		for(int i = 1; i< DP.length; i++){
			DP[i][0] = i;
		}
		
		for(int i = 1; i< DP[0].length; i++){
			DP[0][i] = i;
		}
		
		for(int i = 1; i<DP.length; i++){
			for(int j = 1; j< DP[0].length; j++){
				if(word2.charAt(i-1) == word1.charAt(j-1))
					DP[i][j] = DP[i-1][j-1];
				else DP[i][j] = Math.min(Math.min(DP[i-1][j],DP[i][j-1]), DP[i-1][j-1]) + 1;
			}
		}
		
//		for(int[] x : DP)
//			System.out.println(Arrays.toString(x));
		return DP[len2][len1];
	}
	public static void main(String[] args) {
		String s1 = "horse";
		String s2 = "ros";
		H0072_EditDistance cl = new H0072_EditDistance();
		System.out.println(cl.minDistance(s1,s2));
		
	}
}
