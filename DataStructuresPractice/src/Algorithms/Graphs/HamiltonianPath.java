package Algorithms.Graphs;

import java.util.Arrays;

public class HamiltonianPath {
	private int V, pathCount;
	private int[] path;
	private int[][] graph;
	
	public void findHamiltonian(int vertex, int[][] g) {
		int[][] adjacencyMatrix = new int[vertex][vertex];
		
		for(int[] edge : g){
			int from = edge[0];
			int to = edge[1];
			adjacencyMatrix[from][to] = 1;
			adjacencyMatrix[to][from] = 1;
		}
		
		graph = adjacencyMatrix;
		V = adjacencyMatrix.length;
		path = new int[adjacencyMatrix.length];
		
		
		for(int i = 0; i< path.length; i++) path[i] = -1;
		path[0] = 0; // initialize path from 0
		
		if(findFeasibleSolution(1))  //check solution from position 1
			printHamiltonianPath();
		
		else
			System.out.println("No Hamiltonian Path found");
		
	}
	
	private boolean findFeasibleSolution(int position) {
		if (position == V	) {  // check if its last vertex
			if (graph[path[position - 1]][path[0]] == 1) {  // there is an edge which takes my last point back to first vertex
				return true;
			} else {
				return false;
			}
		}
		/*
		 	If next vertex which we are going to join to hamiltonian path is not last vertex
		 	then check its feasibility if that can be attached to current path.
		 */
		for(int vertex = 1; vertex < V; vertex++){   /* Check if any graph vertex can be attached to given position */
			if(isFeasible(vertex,position)){
				path[position] = vertex;  // if vertex already doesnt exit in result path and there is edge then add it to hamiltonian path
				if(findFeasibleSolution(position+1)) return true; // recursively check for all vertex
				else path[position] = 0;  // if the path is not successful , reset it and explore other vertex
			}
		}
		return false;
	}
	
	private boolean isFeasible(int vertex, int position){
		if(graph[path[position-1]][vertex] == 0) return false;  //Check if this vertex is an adjacent vertex of the previously added vertex.
		
		/* Check if the vertex has already been included. This step can be optimized by creating an array of size V */
		for(int i = 0; i < position; i++){
			if(path[i] == vertex) return false;
		}
		return true;
	}
	
	private void printHamiltonianPath(){
		System.out.println(Arrays.toString(path));
	}
	public static void main(String[] args) {
		HamiltonianPath hc = new HamiltonianPath();
		int[][] edges =  {{0,1},{0,3},{0,4},{1,2},{1,5},{2,3},{2,6},{3,7},{4,5},{4,7},{5,6},{6,7}};
		hc.findHamiltonian(8, edges);
	}
}
