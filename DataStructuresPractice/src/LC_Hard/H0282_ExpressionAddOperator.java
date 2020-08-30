package LC_Hard;
/*
This problem has a lot of edge cases to be considered:

overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
a little trick is that we should save the value that is to be multiplied in the next recursion.
 */
import java.util.*;

public class H0282_ExpressionAddOperator {
	public List<String> addOperators(String num, int target) {
		List<String> rst = new ArrayList<String>();
		if(num == null || num.length() == 0) return rst;
		StringBuilder sb = new StringBuilder();
		helper(rst, sb, num, target, 0, 0, 0);
		return rst;
	}
	
	private void helper(List<String> rst, StringBuilder path, String num, int target, int pos, long prev, long multi){
		if(pos == num.length()){
			if(target == prev)
				rst.add(path.toString());
			return;
		}
		
		for(int i = pos; i < num.length(); i++){
			/*corner case: if current position is 0, we can only use it as a single digit number, should be 0
	        if it is not a single digit number with leading 0, it should be considered as an invalid number */
			if(i != pos && num.charAt(pos) == '0') break;
			long cur = Long.parseLong(num.substring(pos, i + 1));
			int len = path.length();
			if(pos == 0){
				helper(rst, path.append(cur), num, target, i + 1, cur, cur);
				path.setLength(len);
			}else{
				helper(rst, path.append("+").append(cur), num, target, i + 1, prev + cur , cur);
				path.setLength(len);
				helper(rst, path.append("-").append(cur), num, target, i + 1, prev - cur, -cur);
				path.setLength(len);
				helper(rst, path.append("*").append(cur), num, target, i + 1, prev - multi + multi * cur, multi * cur );
				path.setLength(len);
			}
		}
	}
	public static void main(String[] args) {
		String s = "123";
		int i = 6;
		H0282_ExpressionAddOperator cl = new H0282_ExpressionAddOperator();
		System.out.println(cl.addOperators(s,i));
	}
}
