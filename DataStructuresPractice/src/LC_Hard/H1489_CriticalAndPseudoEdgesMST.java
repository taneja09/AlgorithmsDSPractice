package LC_Hard;


import Algorithms.Graphs.UnionFind_count;

import java.util.*;

/*
https://leetcode.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/discuss/698311/Python-UnionFind-%2B-Kruskal-solution-with-detail-explanation-from-O(E2)-to-O(ElogE)
 */
public class H1489_CriticalAndPseudoEdgesMST {
	public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
		List<Integer> criticals = new ArrayList<>();
		List<Integer> pseduos = new ArrayList<>();
		
		Map<int[], Integer> map = new HashMap<>();   ///edges and index of edge
		for (int i = 0; i < edges.length; i++) {
			map.put(edges[i], i);
		}
		
		Arrays.sort(edges, (e1, e2) -> Integer.compare(e1[2], e2[2]));
		int minCost = buildMST(n, edges, null, null);
		
		for (int i = 0; i < edges.length; i++) {
			int[] edge = edges[i];
			int index = map.get(edge);
			int costWithout = buildMST(n, edges, null, edge);
			if (costWithout > minCost) {
				criticals.add(index);
			} else {
				int costWith = buildMST(n, edges, edge, null);
				if (costWith == minCost) {
					pseduos.add(index);
				}
			}
		}
		
		return Arrays.asList(criticals, pseduos);
	}
		
		private int buildMST(int n, int[][] edges, int[] pick, int[] skip){
			UnionFind_count uf = new UnionFind_count(n);
			int cost = 0;
			
			if(pick != null){
				uf.union(pick[0], pick[1]);
				cost += pick[2];
			}
			
			for(int[] edge : edges){
				if(edge != skip && uf.union(edge[0], edge[1])){
					cost += edge[2];
				}
			}
			
			return uf.count == 1? cost : Integer.MAX_VALUE;
			//uf.count means all the vertices have been joined and count reduced to 1 ..before it was total n
			//keep in mind n is not edges , its vertices because we need to count vertices.
			// If we count edges can be redundant also and then we wont reach count as 1 because we ll never exhaust all edges
		}
		
		
		public static void main(String[] args) {
			int[][] edges = {{0,1,1},{1,2,1},{2,3,2},{0,3,2},{0,4,3},{3,4,3},{1,4,6}};
			H1489_CriticalAndPseudoEdgesMST obj = new H1489_CriticalAndPseudoEdgesMST();
			List<List<Integer>>  list = obj.findCriticalAndPseudoCriticalEdges(5,edges);
			for(List<Integer> l : list) System.out.println(l);
		}
	}
