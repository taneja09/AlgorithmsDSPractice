package LC_Medium;

import java.util.*;

public class M0261_GraphIsValidTree {
	public boolean validTree(int n, int[][] edges) {
		if (edges.length != n - 1)
			return false;
		
		ArrayList<Integer>[] graph = new ArrayList[n];
		Set<Integer> seen = new HashSet<>();
		
		for (int i = 0; i < n; i++)
			graph[i] = new ArrayList();
		
		for (int[] edge : edges) {
			int to = edge[1];
			int from = edge[0];
			graph[from].add(to);
			graph[to].add(from);
		}
		
		return dfs(0,-1,seen,graph) && seen.size() == n;

	}
	private boolean dfs(int node, int parent, Set<Integer> seen ,ArrayList<Integer>[] graph ){
		if(seen.contains(node))return false; //completed
		seen.add(node);
		
		for(int neighbor : graph[node]){
			if( parent != neighbor){
				if(!dfs(neighbor,node,seen,graph)) return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int[][] edges = {{1,0}};
		int node = 2;
		M0261_GraphIsValidTree cl = new M0261_GraphIsValidTree();
		System.out.println(cl.validTree(node,edges));
	}
}
