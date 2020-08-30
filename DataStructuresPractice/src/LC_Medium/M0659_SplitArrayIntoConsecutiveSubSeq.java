package LC_Medium;

import java.util.HashMap;
import java.util.Map;
/*
https://www.youtube.com/watch?v=uJ8BAQ8lASE

brilliant idea!
Took me a while to understand "appendfreq"
Here is how I see "appendfreq"

eg: [1,2,3,4, 5]
// i =1
we fall in 3 case "start of a new subsequence"
we make 2, 3 freq 0
and put <4, 1> in appendfreq, this mean I have 1 subsequence can continue from 4

//i =2, 3
we continue

//i = 4
we fall in 2 case since <4, 1> is in appendfreq
now this subsequence should end in 5
so we decreace <4, 1> to <4, 0> since we no longer have subsequence can continue from 4
and we put <5, 1> in appendfreq since now we have a subsequence can continue from 5

Hope this help
 */
public class M0659_SplitArrayIntoConsecutiveSubSeq {
	public boolean isPossible(int[] nums) {
        Map<Integer,Integer> freq = new HashMap(), appendFreq = new HashMap<>();
        for(int num : nums){
        	freq.put(num,freq.getOrDefault(num,0)+1);
		}
        for(int num : nums){
        	if(freq.get(num) == 0) continue;
        	else if(appendFreq.getOrDefault(num,0)>0){
        		appendFreq.put(num,appendFreq.get(num)-1);
        		appendFreq.put(num+1, appendFreq.getOrDefault(num+1,0)+1);
			}else if(freq.getOrDefault(num+1,0)>0 && freq.getOrDefault(num+2,0)>0){
        		freq.put(num+1,freq.get(num+1)-1);
        		freq.put(num+2, freq.get(num+2)-1);
        		appendFreq.put(num+3,appendFreq.getOrDefault(num+3,0)+1);
			}
        	else
        		return false;
        	
        	freq.put(num,freq.get(num)-1);
		}
        return true;
    }
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		M0659_SplitArrayIntoConsecutiveSubSeq  cl = new M0659_SplitArrayIntoConsecutiveSubSeq();
		System.out.println(cl.isPossible(arr));
		
	}
}
