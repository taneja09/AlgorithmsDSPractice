package LC_Hard;

import java.util.*;
/*
This question is simply based on finding the Bridge edge of the graph which is critical.
Use Tarjan's simple DFS to find the Bridge Edge of the given graph
 */
public class H1192_CriticalConnectionsOfNW {
	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		List<Integer>[] graph = new ArrayList[n];
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
		
		for (List<Integer> e : connections) {
			int from = e.get(0);
			int to = e.get(1);
			graph[from].add(to);
			graph[to].add(from);
		}
		
		int[] low = new int[n];    //lowest index array
		int[] disc = new int[n];   //discovery array
		
		dfs(disc, low, 0, -1, graph, res);  // start with node 0 and its parent as -1
		return res;
	}
	
	int time = 0;
	private void dfs(int[] disc, int[] low, int u, int parent, List<Integer>[] graph, List<List<Integer>> res){
		if (disc[u] > 0) return;  //if already discovered just return
		disc[u] = low[u] = ++time;
		
		for (int v : graph[u]) {
			if (v == parent) continue;  //if equals to parent .. don't do anything
			if (disc[v] == 0) dfs(disc, low, v, u, graph, res);  // if not discovered ..send it back to function for discovery
			/*
			Case1 (Tree Edge): If node v is not visited already, then after DFS of v is complete, then minimum of low[u] and low[v] will be updated to low[u].
							low[u] = min(low[u], low[v]);
			Case 2 (Back Edge): When child v is already visited, then minimum of low[u] and Disc[v] will be updated to low[u].
							low[u] = min(low[u], disc[v]);
				For visited nodes we should compare with their start time and not low time as there may not be a path from u to low[v]
				
			In this problem, we just compare with low[v] because back edge is Tree edge only as its undirected graph
			 */
			low[u] = Math.min(low[u], low[v]);
		}
		
		/*	low[v] > disc[u] means that v has no backward edge that can
	    	link v to a vertex that is "lower" (being discovered earlier) than u
	    	hence, `(u,v)` is a critical connection
	    */
		
		if (u != 0 && low[u] > disc[parent]) {
			res.add(Arrays.asList(parent, u));
		}
	}
	public static void main(String[] args) {
		List<List<Integer>> list = new ArrayList();
		list.add(Arrays.asList(0,1));
		list.add(Arrays.asList(1,2));
		list.add(Arrays.asList(2,0));
		list.add(Arrays.asList(1,3));
		
		H1192_CriticalConnectionsOfNW obj = new H1192_CriticalConnectionsOfNW();
		List<List<Integer>>  result = obj.criticalConnections(4,list);
		for(List<Integer> l : result) System.out.println(l);
	}
}
