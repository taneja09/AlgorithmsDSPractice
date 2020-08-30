package LC_Medium;

import java.util.*;

/*
Our goal is trying to use two colors to color the graph and see if there are any adjacent nodes having the same color.
Initialize a color[] array for each node. Here are three states for colors[] array:

For each node,
If it hasn't been colored, use a color to color it. Then use the other color to color all its adjacent nodes (DFS).
If it has been colored, check if the current color is the same as the color that is going to be used to color it.
 */
public class M0785_isGraphBiPartite {
	/************* BFS O(2 ^ m+n) *************************/
	public boolean isBipartiteBFS(int[][] graph) {
		int len = graph.length;
		int[] colors = new int[len];
		for (int i = 0; i < len; i++) {
			if (colors[i] != 0) continue;
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(i);
			colors[i] = 1;   // Blue: 1; Red: -1.
			
			while (!queue.isEmpty()) {
				int cur = queue.poll();
				for (int next : graph[cur]) {
					if (colors[next] == 0) {          // If this node hasn't been colored;
						colors[next] = -colors[cur];  // Color it with a different color;
						queue.offer(next);
					} else if (colors[next] != -colors[cur]) {   // If it is colored and its color is not different, return false;
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	
	/************* DFS O(2 ^ m+n) *************************/
	public boolean isBipartite(int[][] graph) {
		int[] colors = new int[graph.length];
		for(int i = 0;i< graph.length;i++){
			if(colors[i] != 0) continue;
			colors[i] = 1;                  //no color, then color = 1;
			if(backtrack(graph,colors,i) == false) return false;
		}
		return true;
	}
	private boolean backtrack(int[][] graph, int[] colors,int curr){
		for(int j = 0;j< graph[curr].length;j++){
			int next = graph[curr][j];
			if(colors[next] != 0 ) {                // child has color, compare with current color
				if(colors[next] != -colors[curr]) {
					return false;
				}
			}else {
				colors[next] = - colors[curr];      // no color, then paint opposite color
				if( !backtrack(graph,colors,next) ) return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		int[][] graph = {{1,3},{0,2},{1,3},{0,2}};
		M0785_isGraphBiPartite cl = new M0785_isGraphBiPartite();
		System.out.println(cl.isBipartite(graph));
	}
}
