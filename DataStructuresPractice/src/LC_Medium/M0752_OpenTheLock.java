package LC_Medium;
/*
https://efficientcodeblog.wordpress.com/2017/12/13/bidirectional-search-two-end-bfs/
*/

import java.util.*;

public class M0752_OpenTheLock {
	
	/***************** Bidirectional  BFS  TC = O(b^d/2) b = nodes d depth *****************/
	public int openLockBi(String[] deadends, String target) {
		Set<String> begin = new HashSet<>();
		Set<String> end = new HashSet<>();
		Set<String> deads = new HashSet<>(Arrays.asList(deadends));
		begin.add("0000");
		end.add(target);
		int level = 0;
		
		while (!begin.isEmpty() && !end.isEmpty()) {
			Set<String> temp = new HashSet<>();
			for (String s : begin) {
				if (end.contains(s)) return level;
				if (deads.contains(s)) continue;
				deads.add(s);
				StringBuilder sb = new StringBuilder(s);
				
				for (int i = 0; i < 4; i++) {
					char c = sb.charAt(i);
					String s1 = sb.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(i + 1);
					String s2 = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i + 1);
					
					if(end.contains(s1) || end.contains(s2))
						return level+1;  //early exit
					
					if (!deads.contains(s1))
						temp.add(s1);
					if (!deads.contains(s2))
						temp.add(s2);
					
				}
			}
			level++;
			begin = end;
			end = temp;
		}
		return -1;
	}
	
	/***************** Single direction BFS  TC = O(b^d) b = nodes d depth *****************/
	public int openLock(String[] deadends, String target) {
		Set<String> visitSet = new HashSet<>();
		for(String dead : deadends) visitSet.add(dead);
		
		Queue<String> que = new LinkedList<>();
		que.add("0000");
		int turns = 0;
		while(!que.isEmpty()) {
			int queSize = que.size();
			for (int i = 0; i < queSize; i++) {
				String cur = que.poll();
				if (visitSet.contains(cur)) continue;
				if (cur.equals(target)) return turns;
				for (int j = 0; j < 4; j++) {
					int next = (cur.charAt(j) - '0' + 1) % 10;     //amazing way to rotate  curr 0 will give next = 1
					int prev = (10 + cur.charAt(j) - '0' - 1) % 10; //curr 0 will give prev = 9
					que.offer(cur.substring(0, j) + next + cur.substring(j + 1));  // for 0000 -> 1000 will be added
					que.offer(cur.substring(0, j) + prev + cur.substring(j + 1));  // for 0000 -> 9000 will be added
				}
				visitSet.add(cur);
			}
			turns++;
		}
		return -1;
	}
	public static void main(String[] args) {
		String[] deadends = {"0201","0101","0102","1212","2002"};
		String target = "0202";
		M0752_OpenTheLock cl = new M0752_OpenTheLock();
		System.out.println(cl.openLock(deadends,target));
	}
}
