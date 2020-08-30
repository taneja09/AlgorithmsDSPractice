package LC_Medium;
import java.util.*;

public class M0399_EvaluateDivision {
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		Map<String,Map<String, Double>> graph = buildGraph(equations,values);
		double[] result = new double[queries.size()];
		for (int i = 0; i < queries.size(); i++) {
			result[i] = dfs(queries.get(i).get(0),queries.get(i).get(1),new HashSet<>(),graph);
		}
		return result;
	}
	
	private double dfs(String from, String to,Set<String> visited,Map<String, Map<String, Double>> graph){
		// Rejection case. No source code exists to start from
		if(!graph.containsKey(from))
			return -1.0;
		
		// if there is a direct edge from source and destination return its weight
		if(graph.get(from).containsKey(to))
			return graph.get(from).get(to);
		
		// add to visited set to avoid cycle
		visited.add(from);
		
		//DFS over graph to check neighbor nodes for path and return product of paths  a-->b-->c ..finding a to c has to go through b and thus ab * bc
		for(Map.Entry<String, Double> neighbour : graph.get(from).entrySet()) {
			if(!visited.contains(neighbour.getKey())) {
				double productWeight = dfs(neighbour.getKey(), to, visited, graph);
				if (productWeight != -1.0)
					return neighbour.getValue() * productWeight;
			}
		}
		return -1.0;
	}
	
	private  Map<String,Map<String, Double>>  buildGraph(List<List<String>> equations,double[] values){
		Map<String, Map<String, Double>> graph = new HashMap<>();  // first String is start , second string is end and double wight
		String from, to; // vertices
		
		for (int i = 0; i < equations.size(); i++) {
			from = equations.get(i).get(0);
			to = equations.get(i).get(1);
			
			// edge from a --> b and its value
			graph.putIfAbsent(from,new HashMap<String,Double>());
			graph.get(from).put(to,values[i]);
			
			// edge from b --> a and its value ie 1/(value)
			graph.putIfAbsent(to,new HashMap<String,Double>());
			graph.get(to).put(from,1/values[i]);
		}
		
		return graph;
	}
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		List<List<String>> equations = new ArrayList();
		ArrayList al = new ArrayList();
		al.add("a");
		al.add("b");
		equations.add(al);
		ArrayList al1 = new ArrayList();
		al1.add("b");
		al1.add("c");
		equations.add(al1);
		
		double values[] = {2.0,3.0};
		
		List<List<String>> queries = new ArrayList();
		
		ArrayList al2 = new ArrayList();
		al2.add("a");
		al2.add("c");
		
		ArrayList al3 = new ArrayList();
		al3.add("b");
		al3.add("a");
		
		ArrayList al4 = new ArrayList();
		al4.add("a");
		al4.add("e");
		
		ArrayList al5 = new ArrayList();
		al5.add("a");
		al5.add("e");
		
		queries.add(al2);
		queries.add(al3);
		queries.add(al4);
		queries.add(al5);
		
		M0399_EvaluateDivision cl = new M0399_EvaluateDivision();
		System.out.println(Arrays.toString(cl.calcEquation(equations,values,queries)));
	}
	
}
