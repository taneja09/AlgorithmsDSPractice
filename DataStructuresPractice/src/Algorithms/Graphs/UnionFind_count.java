package Algorithms.Graphs;

public class UnionFind_count {
	final int[] parents;
	public int[] ranks;
	public int count;
	
	public UnionFind_count(int n){
		this.parents = new int[n];
		ranks = new int[n];   //takes care of size
		count = n;
		for(int i =0;i<parents.length;i++){
			parents[i] = i;
		}
		
	}
	
	public int find(int x) {
		if(x != parents[x]) x = find(parents[x]);
		return parents[x];
	}
	
	public boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if(px == py) return false;
		if(ranks[px] > ranks[py]) {   // if py has less size put it under larger branch px
			parents[py] = px;   //make px of larger length as parent of py
			ranks[px] ++;    //size of px increase
		} else{
			parents[px] = py;
			ranks[py] ++;
		}
		count--;
		return true;
	}
}
