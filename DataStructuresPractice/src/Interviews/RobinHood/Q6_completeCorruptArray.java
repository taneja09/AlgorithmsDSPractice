package Interviews.RobinHood;

import java.util.*;

public class Q6_completeCorruptArray {
	private List<Integer> getCompleteArray(int[][] arr) {
		int len = arr.length;
		List<Integer> result = new ArrayList<>();
		List<Integer>[] graph = new List[len*2];
		
		for(int i=0;i<=len+1;i++)
			graph[i] = new ArrayList();
		
		Set<Integer> visited = new HashSet<>();
		
		for(int[] edge : arr) {
			int from = edge[1], to = edge[0];
			graph[from].add(to);
			graph[to].add(from);
		}
		
		DFS(arr[0][0], graph, visited, result);
		
		return result;
	}
	
	public void DFS(int source, List<Integer> adjList [], Set<Integer> visited, List<Integer> res){
		
		//mark the vertex visited
		visited.add(source);
		res.add(source);
		//travel the neighbors
		for (int i = 0; i <adjList[source].size() ; i++) {
			int neighbor = adjList[source].get(i);
			if(!visited.contains(neighbor)){
				//make recursive call from neighbor
				DFS(neighbor, adjList, visited, res);
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] pairs = {{3,5},{1,4},{2,4},{1,5}};
		Q6_completeCorruptArray cl = new Q6_completeCorruptArray();
		List<Integer> res = cl.getCompleteArray(pairs);
		System.out.println(res);
		
	}
}
