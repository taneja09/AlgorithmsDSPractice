package LC_Medium;

public class M1143_LongestCommonSubSequence {
	
	/************Top Down Approach *****************/
	public int longestCommonSubsequence(String text1, String text2) {
		int m = text1.length();
		int n = text2.length();
		int[][] dp = new int[m][n];
		
		//Fill the 2D array with -1 values
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				dp[i][j] = -1;
			}
		}
		return longestCommonSubsequence(text1, text2, 0, 0, dp);
	}
	//if the  character  is same -> length of output is incremented
	// else we check for i+1 with j or i and j+1 to find the max match and return result;
	private int longestCommonSubsequence(String text1, String text2, int i ,int j, int[][] dp){
		if(i == text1.length() || j == text2.length()) return 0;
		if(dp[i][j] != -1) return dp[i][j];
		if(text1.charAt(i) == text2.charAt(j)) return dp[i][j] = 1 + longestCommonSubsequence(text1, text2, i + 1, j + 1, dp);
		else return dp[i][j] = Math.max(longestCommonSubsequence(text1, text2, i + 1, j, dp), longestCommonSubsequence(text1, text2, i, j + 1, dp));
	}
	
	/************** Bottom Up approach ****************/
	public int longestCommonSubsequenceBTU(String text1, String text2){
		if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0)return 0;
		int m = text1.length();
		int n = text2.length();
		int[][] dp = new int[m + 1][n + 1];
		
		for(int i = m; i >= 0; i--){
			for(int j = n; j >= 0; j--) {
				if(i == m || j == n){
					dp[i][j] = 0; // end points return 0;
				}
				else if(text1.charAt(i) == text2.charAt(j)){
					dp[i][j] = dp[i + 1][j + 1] + 1;  //we are filling dp array from back so i+1;
				}else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
				}
			}
		}
		return dp[0][0];
	}
	
	public static void main(String[] args) {
		String text1 = "abcde";
		String text2 = "ace";
		M1143_LongestCommonSubSequence cl = new M1143_LongestCommonSubSequence();
		System.out.println(cl.longestCommonSubsequence(text1,text2));
		System.out.println(cl.longestCommonSubsequenceBTU(text1,text2));
	}
}


/*

LTE
public int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0){
            return 0;
        }
        
        return lcs(text1, text2, 0, 0);
    }
    
    int lcs(String text1, String text2, int i, int j){
        if(i == text1.length() || j == text2.length()){
            return 0;
        }
        
        if(text1.charAt(i) == text2.charAt(j)){
            return lcs(text1, text2, i + 1, j + 1) + 1;
        }
        else{
            return Math.max(lcs(text1, text2, i + 1, j), lcs(text1, text2, i, j + 1));
        }
    }
    
    
          					lcs(0, 0)(b,c)
                       /                      \
                      /                        \
           lcs(1, 0)  (c,c)                      lcs(0, 1)(b,e)
         /          \                           /              \
        /            \                         /                \
    lcs(2, 0)     lcs(1, 1)                lcs(1, 1)        lcs(0, 2)
    (d,c)			(c,e)					(c,e)				No Choice
    
 */