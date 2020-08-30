package LC_Medium;
/*
abba
i ,i+1, ..........j-1,j
is a palindrome only if i+1, ..........j-1is a palindrome and a[i] == a[j]

 */
public class M0647_PalindromicSubStrings {
	
	//***************** Easy method ******************//
	
	//The outer for loop has to start from i - 1 (the end) why ? because the recursion is looking forward, referring to i+1.
	//The inner forward can run normally, but since we are only interested in one half of the diagonal, we start at i, everytime.
	
	public int countSubstringsCompact(String s) {
		int n = s.length();
		int res = 0;
		boolean[][] dp = new boolean[n][n];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i + 1 < 3 || dp[i + 1][j - 1]);  //j - i + 1 < 3 means length is less than 3
				if(dp[i][j]) ++res;
			}
		}
		return res;
	}
	
	//***************** another method ******************//
	public int countSubstrings(String s) {
		boolean[][] dp = new boolean[s.length()][s.length()];
		int count = 0;
		
		for (int i = 0; i < s.length(); i++) {
			// 1 char strings (always true)
			dp[i][i] = true;
			count++;
			// 2 char strings
			if (i+1 < s.length() && s.charAt(i) == s.charAt(i+1) ) {
				dp[i][i+1] = true;
				count++;
			}
		}
		
		// d is the distance from the current char, allows diagonal traversal
		for (int d = 2; d <= s.length(); d++) {
			for (int i = 0; i+d < s.length(); i++) {
				if (s.charAt(i) == s.charAt(i+d) && dp[i+1][i+d-1]) {
					dp[i][i+d] = true;
					count++;
				}
			}
		}
		
		//System.out.println("maxsub " + maxSub);
		
		return count;
	}
	public static void main(String[] args) {
		String str = "abaabc";
		M0647_PalindromicSubStrings cl = new M0647_PalindromicSubStrings();
		System.out.println(cl.countSubstrings(str));
		System.out.println(cl.countSubstringsCompact(str));
	}
}

/*
Creat a 2d array of m*m size  length m = 6
i = row and j = column
Note: here if a column j is True that means from i to j its a palindrome

aba  -- in first row i = 0 and j = 2 will both have true and hence left diagonal space will always be false
because we are not going from j to i (reverse string ) and fill all of them as false and we can fill whole left matrix with false;

Step 1: fill the diagonal with true because all single characters are palindromic and fill left side diagonal matrix to be false

		j==>>>>>
i		a		b		a		a		b		c
	a	T
	
	b	F		T
	
	a	F		F		T
	
	a	F		F		F		T
	
	b	F		F		F		F		T
	
	c	F		F		F		F		F		T
	
	
Step 2: substring with length = 2 abaabc all values such as => ab, ba, aa, ab, bc are checked for being palindromic filled diagonally
	
Matrix[i][j] where j = i+1 because length ois 2 now and we will keep on checking with j = i+1
	
		a			b			a			a			b			c
	a	T(a) 		F(ab)
	
	
	b	F			T(b) 		F(ba)
	
	
	a	F			F			T(a)		T(aa)
	
	
	a	F			F			F			T(a)		F(ab)
	
	
	b	F			F			F			F			T(b)		F(bc)
	
	
	c	F			F			F			F			F			T(c)
	
	
Step 3: Fill next right diagonal with substring length 3 from i to j
if (i-j>=2) that means length 3 onwards check str[i] = str[j] && matrix[i+1][j-1] == true
as matrix[i+1][j-1] is character which has already checked and comes after i and before j and we can pick up its value as is from matrix
Example aba , we can check if first and last character to be equal to be palindrome

			a			b			a			a			b			c
	a		T(a) 		F(ab)		T(aba)
	
	
	b		F			T(b) 		F(ba)		F(baa)
	
	
	a		F			F			T(a)		T(aa)		F(aab)
	
	
	a		F			F			F			T(a)		F(ab)		F(abc)
	
	
	b		F			F			F			F			T(b)		F(bc)
	
	
	c		F			F			F			F			F			T(c)
	
	
Step 4: fill next right diagonal with substring length = 4

			a			b			a			a			b			c
	a		T(a) 		F(ab)		T(aba)		F(abaa)
	
	
	b		F			T(b) 		F(ba)		F(baa)		T(baab)
	
	
	a		F			F			T(a)		T(aa)		F(aab)		F(aabc)
	
	
	a		F			F			F			T(a)		F(ab)		F(abc)
	
	
	b		F			F			F			F			T(b)		F(bc)
	
	
	c		F			F			F			F			F			T(c)


Step 5: fill next right diagonal with substring length = 5

			a			b			a			a			b			c
	a		T(a) 		F(ab)		T(aba)		F(abaa)		F(abaab)
	
	
	b		F			T(b) 		F(ba)		F(baa)		T(baab)		F(baabc)
	
	
	a		F			F			T(a)		T(aa)		F(aab)		F(aabc)
	
	
	a		F			F			F			T(a)		F(ab)		F(abc)
	
	
	b		F			F			F			F			T(b)		F(bc)
	
	
	c		F			F			F			F			F			T(c)
	

Step 6: fill next right diagonal with substring length = 6

			a			b			a			a			b			c
	a		T(a) 		F(ab)		T(aba)		F(abaa)		F(abaab)	F(abaabc)
	
	
	b		F			T(b) 		F(ba)		F(baa)		T(baab)		F(baabc)
	
	
	a		F			F			T(a)		T(aa)		F(aab)		F(aabc)
	
	
	a		F			F			F			T(a)		F(ab)		F(abc)
	

	b		F			F			F			F			T(b)		F(bc)
	
	
	c		F			F			F			F			F			T(c)
	
	
	keep track of count of substring and then return it
 */