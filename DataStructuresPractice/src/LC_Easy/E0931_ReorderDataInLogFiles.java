package LC_Easy;

import java.util.*;

public class E0931_ReorderDataInLogFiles {
	public String[] reorderLogFiles(String[] logs) {
		List<String[]> logger = new ArrayList<>();
		List<String> letterList = new ArrayList<>();
		List<String> digitList = new ArrayList<>();
		for (String s : logs)
			logger.add(s.split(" "));
		
		int i = 0;
		for (String[] arr : logger) {
			if (Character.isDigit(arr[1].charAt(0))) digitList.add(logs[i]);
			if (Character.isLetter(arr[1].charAt(0))) letterList.add(logs[i]);
			i++;
		}
		
		letterList.sort(new CustomComparator());
		String[] result = new String[logs.length];
		int k = 0;
		for(String s : letterList){
			result[k] = s;
			k++;
		}
		for(String s : digitList) {
			result[k] = s;
			k++;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		String[] arr = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
		E0931_ReorderDataInLogFiles cl = new E0931_ReorderDataInLogFiles();
		System.out.println(Arrays.toString(cl.reorderLogFiles(arr)));
	}
}
 class CustomComparator implements Comparator<String> {
	@Override
	 public int compare(String o1, String o2) {
		String[] o1A = o1.split(" ");
		String[] o1B = o2.split(" ");
		int i = 1;
		int j = 1;
		int ret = 0;
		while (i < o1A.length && j < o1B.length) {
			ret = o1A[i].compareTo(o1B[j]);
			if (ret != 0) {
				return ret;
			}
			i++;
			j++;
		}
		return o1A[0].compareTo(o1B[0]);
	}
 }
