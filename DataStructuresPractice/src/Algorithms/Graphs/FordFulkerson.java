package Algorithms.Graphs;

import java.util.*;

public class FordFulkerson {
	public int fordFulkerson(int graph[][], int s, int t) {
		int u, v;
		int V = graph.length;
		
		int rGraph[][] = new int[V][V]; //Residual graph with initial capacity as given graph
		
		for (u = 0; u < V; u++)
			for (v = 0; v < V; v++)
				rGraph[u][v] = graph[u][v];
		
		// This array is filled by BFS and to store path
		int parent[] = new int[V];
		int max_flow = 0;
		
		/// Augment the flow while there is path from source to sink
		while (bfs(rGraph, s, t, parent, V)) {
			int path_flow = Integer.MAX_VALUE;
			for (v = t; v != s; v = parent[v]) {  //start from sink and keep on traversing towards its parent
				u = parent[v];  // get the parent of current vertex
				path_flow = Math.min(path_flow, rGraph[u][v]);  //take minimum of flow because its the only flow we can pass through path 4,3,5, = 3 can flow
			}
			
			// update residual capacities of the edges and
			// reverse edges along the path
			for (v = t; v != s; v = parent[v]) {
				u = parent[v];
				rGraph[u][v] -= path_flow;    // decrease weight for edge as the flow has taken place
				rGraph[v][u] += path_flow;    //increase weight to reverse edge
			}
			
			max_flow += path_flow;
		}
		
		return max_flow;
	}
	
	
	/* Returns true if there is a path from source 's' to sink 't' in residual graph. Also fills parent[] to store the path */
	
	public  boolean bfs(int rGraph[][], int s, int t, int parent[], int V) {
		boolean visited[] = new boolean[V];  // to mark visited vertex
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(s);
		visited[s] = true;
		parent[s]=-1;
		
		while (queue.size()!=0) {
			int u = queue.poll();
			for (int v = 0; v < V; v++) {
				if (visited[v]==false && rGraph[u][v] > 0)  // check if next vertex is not already visited and there is an edge from u to v
				{
					queue.add(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}
		
		return visited[t] == true;   //if we successfully reached the sink node then there is a path
	}
	public static void main(String[] args) {
		// or create graph with given edges and capacity here 0-1 edge flow capacity is 16
		int graph[][] =
			{ {0, 16, 13, 0, 0, 0},
				{0, 0, 10, 12, 0, 0},
				{0, 4, 0, 0, 14, 0},
				{0, 0, 9, 0, 0, 20},
				{0, 0, 0, 7, 0, 4},
				{0, 0, 0, 0, 0, 0}
			};
		FordFulkerson obj = new FordFulkerson();
		System.out.println(obj.fordFulkerson(graph, 0, 5));  // source = 0 and sink is 5
		
	}
}
