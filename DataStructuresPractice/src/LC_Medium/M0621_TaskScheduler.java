package LC_Medium;
/**
 
 Time Complexity - O(n)
 Reason -
 
 Space Complexity - O(1)
 Reason -
*/
//https://leetcode.com/problems/task-scheduler/discuss/104500/Java-O(n)-time-O(1)-space-1-pass-no-sorting-solution-with-detailed-explanation
public class M0621_TaskScheduler {
	public int leastInterval(char[] tasks, int n) {
		int[] hash  = new int[26];
		int max = 0;
		int maxCount = 0;   // if there are more than one task with most frequency, count all those characters
		for(char c : tasks){
			hash[c-'A']++;
			if(max == hash[c - 'A']) {
				maxCount++;
			}else if(max < hash[c - 'A']) {
				max = hash[c - 'A'];
				maxCount = 1;
			}
		}
		
		return Math.max(tasks.length, (max - 1)*(n + 1) + maxCount);

		/*
		int partCount = maxn - 1; //number of slots between maxmimum fre element A _ _ A _ _ A  because n == 2 hence most freq elements are at gap2
		int partLength = n - (maxCount - 1);
		int emptySlots = partCount * partLength;
		int availableTasks = tasks.length - max * maxCount;
		int idles = Math.max(0, emptySlots - availableTasks);
		return tasks.length + idles;
		
		partCount = count(A) - 1
		emptySlots = partCount * (n - (count of tasks with most frequency - 1))
		availableTasks = tasks.length - count(A) * count of tasks with most frenquency
		idles = max(0, emptySlots - availableTasks)
		result = tasks.length + idles
		
		AAABBBCCD n =2
		
		Step 1: Count max frequency for given character array -  => max = 3
		Step 2: Count number of character with max frequency -A and B both have 3 frequency => maxCount = 2
		Step 3: count the empty parts in arrangement , as we know that A/B has max freqency and we need space of 3(n) the arrangement gets divided like below
				A _ _ _ A _ _ _ A  => two parts by Count(A)-1 => 3-1 = 2
		Step 4: Count number of empty slots as =  parts * (n - (counts of mmost freq numbers -1)) => 2 * (3-(2-1)) = 4
				A B _ _ A B _ _ AB
		Step 5: Number of task available which we need to fill as = totalTasks - maxFrequency * count of maxFreq Element
				TotalTasks = length of whole array - 3(maxFreq) * 2(AB) => 9 -3*2 = 3 tasks are pending to be filled
		Step 6: Idle positions = max(0, EmptySpace - task pending to be filled) => 4-3 = 1
		Step 7: return no. of tasks + idle spaces
		
		
		*/
	
	}
	public static void main(String[] args) {
		char[] time ={'A','A','A','B','B','B'};
		M0621_TaskScheduler cl = new M0621_TaskScheduler();
		System.out.println(cl.leastInterval(time,2));
	}
}
