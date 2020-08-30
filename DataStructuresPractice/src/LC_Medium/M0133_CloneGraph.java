package LC_Medium;
/**
 Time Complexity -  O(VE)
 Reason - v = number of nodes  E = number of edges
 
 Space Complexity - O(V)
 Reason -  number of vertexes stores in hashmap & queue
 */
import java.util.*;


public class M0133_CloneGraph {
	class Node {
		public int val;
		public List<Node> neighbors;
		
		public Node() {
			val = 0;
			neighbors = new ArrayList<Node>();
		}
		
		public Node(int _val) {
			val = _val;
			neighbors = new ArrayList<Node>();
		}
		
		public Node(int _val, ArrayList<Node> _neighbors) {
			val = _val;
			neighbors = _neighbors;
		}
	}
	
	
	public Node cloneGraph(Node node) {
		HashMap<Integer, Node> map = new HashMap<Integer, Node>();
		return dfs(node, map);
	}
	
	private Node dfs(Node node, HashMap<Integer, Node> map) {
		if (node == null) return null;
		if (map.containsKey(node.val)) {
			return map.get(node.val);
		} else {
			Node clone = new Node(node.val);
			map.put(node.val, clone);
			for (Node Child : node.neighbors) {
				clone.neighbors.add(dfs(Child, map));
			}
			return clone;
		}
	}
	
	/******************* BFS Solution ********************************/
	public Node cloneGraphBFS(Node node) {
			HashMap<Node, Node> map = new HashMap(); //Key - original node , value is copied node
			Node clone = new Node(node.val, new ArrayList<>());
			map.put(node,clone);
			Queue<Node> que = new LinkedList();
			que.add(node); //stored original node
			
			while(!que.isEmpty()){
				Node currOrginialNode = que.poll();
				for(Node orgNeighbor: currOrginialNode.neighbors) {
					if(!map.containsKey(orgNeighbor)){
						Node newneighbor = new Node(orgNeighbor.val, new ArrayList<>());
						map.put(orgNeighbor, newneighbor);
						que.add(orgNeighbor);  //add original neighbour to queue for processing
					}
					map.get(currOrginialNode).neighbors.add(map.get(orgNeighbor));
				}
			}
			return clone;
	}
}