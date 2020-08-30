package Algorithms.Graphs;
/* https://algorithms.tutorialhorizon.com/prims-minimum-spanning-tree-mst-using-adjacency-list-and-priority-queue-without-decrease-key-in-oelogv/
*/
/* STEPS
	1. Create Edge class with start, end and weight
	2. Create Adjacency List of type Edge --> add both sides of edges as its an undirected graph
	3. create a boolean array for duplicate/loop check ---> boolean[] mst
	4. Create ResultSet[] with 2 Values:
			> First the parent vertex means from which vertex you can visit this vertex
			> Second weight of edge u-v
			
	5. Key[] array to store the current weight of the vertex. Initialize this with MAX_VALUE
	6. Create priority queue to store Pair<Key, Vertex> and initialize with 0 key weight and 0 vertex
	7. loop queue, pop the vertex and check its adjacent vertex from adjacency list
			> If that is not present in mst[] array  && its weight in Key[adjacent vertex] < its current weight THEN DO THE FOLLOWING
					  1) replace its value in Key table = Key[adjacent vertex] = weight
					  2) Update the result set with current polled vertex because that is the parent of this adjacent vertex with its weight
					  3) Finally add this vertex to the priority queue
 */

import javafx.util.Pair;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PrimMST {
	int vertices;
	LinkedList<Edge>[] adjacencylist;
	
	public PrimMST(int vertices) {
		this.vertices = vertices;
		adjacencylist = new LinkedList[vertices];
		//initialize adjacency lists for all the vertices
		for (int i = 0; i < vertices; i++) {
			adjacencylist[i] = new LinkedList<>();
		}
	}
	
	public void addEgde(int source, int destination, int weight) {
		Edge edge = new Edge(source, destination, weight);
		adjacencylist[source].addFirst(edge);  // source to destination
		
		edge = new Edge(destination, source, weight);
		adjacencylist[destination].addFirst(edge); //destination to source  because its an undirected graph
	}
	
	public void calculatePrimMST() {
		boolean[] mst = new boolean[vertices];
		ResultSet[] resultSet = new ResultSet[vertices];
		int[] key = new int[vertices];  //keys used to store the weight to know whether priority queue update is required
		
		//Initialize all the keys to infinity and
		//initialize resultSet for all the vertices
		for (int i = 0; i < vertices; i++) {
			key[i] = Integer.MAX_VALUE;
			resultSet[i] = new ResultSet();
		}
	
		/*Initialize priority queue
		override the comparator to do the sorting based keys */
		PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices, new Comparator<Pair<Integer, Integer>>() {
			@Override
			public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
				//sort using key values
				int key1 = p1.getKey();
				int key2 = p2.getKey();
				return key1 - key2;
			}
		});
		
		//create the pair for for the first index, 0 key 0 index
		key[0] = 0;
		Pair<Integer, Integer> p0 = new Pair<>(key[0], 0);
		//add it to pq
		pq.offer(p0);
		
		
		resultSet[0] = new ResultSet();
		resultSet[0].parent = -1;
		
		
		//while priority queue is not empty
		while (!pq.isEmpty()) {
			//extract the min
			Pair<Integer, Integer> extractedPair = pq.poll();
			
			//extracted vertex
			int extractedVertex = extractedPair.getValue();
			mst[extractedVertex] = true;
			
			//iterate through all the adjacent vertices and update the keys
			LinkedList<Edge> list = adjacencylist[extractedVertex];
			for (int i = 0; i <list.size() ; i++) {
				Edge edge = list.get(i);
				//only if edge destination is not present in mst
				if(mst[edge.destination]==false) {
					int destination = edge.destination;
					int newKey = edge.weight;
					//check if updated key < existing key, if yes, update if
					if(key[destination]>newKey) {
						//add it to the priority queue
						Pair<Integer, Integer> p = new Pair<>(newKey, destination);
						pq.offer(p);
						//update the resultSet for destination vertex
						resultSet[destination].parent = extractedVertex;
						resultSet[destination].weight = newKey;
						//update the key[]
						key[destination] = newKey;
					}
				}
			}
		}
		printMST(resultSet);
	}
	
	public void printMST(ResultSet[] resultSet){
		int total_min_weight = 0;
		System.out.println("Minimum Spanning Tree: ");
		for (int i = 1; i <vertices ; i++) {
			System.out.println("Edge: " + i + " - " + resultSet[i].parent +
				" key: " + resultSet[i].weight);
			total_min_weight += resultSet[i].weight;
		}
		System.out.println("Total minimum key: " + total_min_weight);
	}
	
	public static void main(String[] args) {
		int vertices = 6;
		PrimMST graph = new PrimMST(vertices);
		graph.addEgde(0, 1, 4);
		graph.addEgde(0, 2, 3);
		graph.addEgde(1, 2, 1);
		graph.addEgde(1, 3, 2);
		graph.addEgde(2, 3, 4);
		graph.addEgde(3, 4, 2);
		graph.addEgde(4, 5, 6);
		graph.calculatePrimMST();
	}
}
class ResultSet {
	int parent;
	int weight;
}