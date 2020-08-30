package LC_Medium;

import java.util.Arrays;

public class M0957_PrisonCellsAfterNDays {
	
	/*********** Optimized *******************/
	public int[] prisonAfterNDays(int[] cells, int N) {
		N = (N-1)%14 + 1;
		for(int i = 0; i<N; i++){
      	cells = getState(cells);
   		}
      return cells;
	
	}
	
	/*********** TLE *******************/
	public int[] prisonAfterNDaysTLE(int[] cells, int N) {
       for(int i = 0; i<N; i++){
       	cells = getState(cells);
	   }
       return cells;
   	}
   	
   	private int[] getState(int[] cells){
		int[] next = new int[cells.length];
		for(int i = 1; i<cells.length-1; i++){
			next[i] = cells[i-1] == cells[i+1] ? 1: 0;
		}
		
		return next;
	}
	public static void main(String[] args) {
		int[] prison = {0,1,0,1,1,0,0,1};
		M0957_PrisonCellsAfterNDays cl = new M0957_PrisonCellsAfterNDays();
		System.out.println(Arrays.toString(cl.prisonAfterNDays(prison,7)));
	}
}
