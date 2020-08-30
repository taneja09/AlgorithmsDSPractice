package LC_Hard;

public class H0045_JumpGameII {
	public int jump(int[] nums) {
		int jumps = 0, curEnd = 0, curFarthest = 0;
		for (int i = 0; i < nums.length-1; i++) {   //length-1 because if we reach there we can always reach end its given in assumption
				curFarthest = Math.max(curFarthest, i + nums[i]);
				if (i == curEnd) {
					jumps++;
					curEnd = curFarthest;
				}
			}
			return jumps;
	}
	public static void main(String[] args) {
		int[] array = {2,3,1,1,4};
		H0045_JumpGameII cl = new H0045_JumpGameII();
		System.out.println(cl.jump(array));
	}
}
/*
Example - [1,2,1,1,4]
curFarthest is the pointer that can be reached from the current pointer
at position 0 value is 1 ==> curFarthest = Math.max(curFarthest, i + nums[i]); => (0, 0+1) =1  which is true because with value 1 you can reach only index 1

Next we check currEnd == i that means am i standing at the currentFarthest point if yes then we need to take another jump and set the new curfarthest point
as the max index which can be reached from my current position

if (i == curEnd)
{
	jumps++;
	curEnd = curFarthest;
}

now at index 1 , curFarthest = 3 because from nums[1] = 2 you can reach index 3
in next iteration i != curEnd 2 != 3 and we increment i untill i == curEnd.. that means processing all elements in the queue
it act as a level because from index 1 I can reach index 3 thats why i didnt increase my jump level , i am jumping only when i reach the farthest point which is curend

===========================================
This algorithm can also be considered as a BFS method, take [2,3,1,1,4] for example:

2    // level 0
3 1  // level 1
1 4  // level 2
It doesn't record the path. To print the path, we can use DFS to record:

     2
    / \
   3   1
  /|\  |
 1 1 4 1
 
 This is an implicit bfs solution. i == curEnd means you visited all the items on the current level. Incrementing jumps++ is like
 incrementing the level you are on.
 And curEnd = curFarthest is like getting the queue size (level size) for the next level you are traversing.
 ==============================



*/
