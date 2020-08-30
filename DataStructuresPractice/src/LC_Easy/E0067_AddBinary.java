package LC_Easy;

public class E0067_AddBinary {
	public String addBinary(String a, String b) {
		StringBuilder sb = new StringBuilder();
		
//		if(a.length() == 0 && b.length() == 0) return new String();
//		if(a.length() == 0 || b.length() == 0) return a.length() == 0 ? b : a;
		
		int i = a.length()-1, j = b.length()-1;
		
		int carry = 0;
		while(i >= 0 || j >= 0){
			int sum = carry;
			if(j>= 0 ) sum+= b.charAt(j--)-'0';
			if(i>= 0 ) sum+= a.charAt(i--)-'0';
			sb.insert(0,sum % 2);  //if is 0 or 2 the actual digit should be 0 because 1 + 1 = 0 and 0 +0 = 0
			carry = sum/2; //if sum is = 2 , carry is 1 else no carry bcz 1/2 = 0  & 0/2 = 0
		}
		//remaining carry
		if(carry != 0) sb.insert(0,carry);
		return sb.toString();
	}
	public static void main(String[] args) {
		String a = "11";
		String b = "1";
		E0067_AddBinary cl = new E0067_AddBinary();
		System.out.println(cl.addBinary(a,b));
	}
}
