package LC_Medium;

import java.util.*;

public class M0254_FactorCombination {
	public List<List<Integer>> getFactors(int n) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> sublist = new ArrayList<>();
		helperMethod(res,sublist,n ,2);
		return res;
	}
	
	private void helperMethod(List<List<Integer>> res ,List<Integer> sublist,int n, int start) {
		if (n <= 1) {
			if(sublist.size()>1)
			res.add(new ArrayList<>(sublist));
			
			return;
		}
		for (int i = start; i <= n; i++) {
			if (n % i == 0) {
				sublist.add(i);
				helperMethod(res, sublist, n / i, i); /// start can be repeated
				sublist.remove(sublist.size() - 1);
			}
		}
	}
	
	public static void main(String[] args) {
		M0254_FactorCombination cl = new M0254_FactorCombination();
		List<List<Integer>> res = cl.getFactors(16);
		for(List<Integer> x : res) System.out.println(x);
	}
}
