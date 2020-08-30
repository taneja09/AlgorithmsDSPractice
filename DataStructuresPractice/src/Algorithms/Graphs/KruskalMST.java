package Algorithms.Graphs;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class KruskalMST {
	int vertices;
	ArrayList<Edge> listOfEdges = new ArrayList<>();  //its not adjacency list, its just a list of edges
	
	public KruskalMST(int vertices) {
		this.vertices = vertices;
	}
	
	public void addEgde(int source, int destination, int weight) {
		Edge edge = new Edge(source, destination, weight);
		listOfEdges.add(edge); //add to total edges
	}
	
	public void calculateKruskalMST(){
		PriorityQueue<Edge> pq = new PriorityQueue<>((a,b)->a.weight-b.weight);
		//add all the edges to priority queue, //sort the edges on weights
		for (int i = 0; i <listOfEdges.size() ; i++) {
			pq.add(listOfEdges.get(i));
		}
		
		UnionFind uf = new UnionFind(vertices);
		ArrayList<Edge> mst = new ArrayList<>();  // Minimum spanning Tree List
		
		while(!pq.isEmpty()){
			Edge edge = pq.remove();
			//check if adding this edge creates a cycle
			int x_set = uf.find(edge.source);
			int y_set = uf.find(edge.destination);
			
			if(x_set==y_set){
				continue; //ignore, will create cycle
			}else {
				//add it to our final result
				mst.add(edge);
				uf.union(x_set,y_set);
			}
		}
		System.out.println("Minimum Spanning Tree: ");
		printGraph(mst);
	}

	
	public void printGraph(ArrayList<Edge> edgeList){
		for (int i = 0; i <edgeList.size() ; i++) {
			Edge edge = edgeList.get(i);
			System.out.println("Edge-" + i +
				" source: " + edge.source +
				" destination: " + edge.destination +
				" weight: " + edge.weight);
		}
	}
	
	public static void main(String[] args) {
		int vertices = 6;
		KruskalMST graph = new KruskalMST(vertices);
		graph.addEgde(0, 1, 4);
		graph.addEgde(0, 2, 3);
		graph.addEgde(1, 2, 1);
		graph.addEgde(1, 3, 2);
		graph.addEgde(2, 3, 4);
		graph.addEgde(3, 4, 2);
		graph.addEgde(4, 5, 6);
		graph.calculateKruskalMST();
	}
}

class Edge {
	int source;
	int destination;
	int weight;
	
	public Edge(int source, int destination, int weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
}