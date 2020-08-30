package LC_Medium;

import java.util.HashMap;
import java.util.*;

public class M0743_NetworkDelayTime {
	/********* Floyd Warshal  Ford TC = O(N*N*N)  SC = O(N^2) ***********************/
	public int networkDelayTimeFW(int[][] times, int N, int K) {
		double[][] AO = new double[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(AO[i], Double.POSITIVE_INFINITY);
		}
		
		for (int i = 0; i < N; i++) {
			AO[i][i] = 0;
		}
		
		for (int[] edge : times) {
			AO[edge[0] - 1][edge[1] - 1] = edge[2];
		}
		
		//A[i][j] => weight of edge from i to j
		// check if A[i][j] > A[i][k] + A[k][j] where k is intermediate vertex selected
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (AO[i][j] > AO[i][k] + AO[k][j])
						AO[i][j] = AO[i][k] + AO[k][j];
				}
			}
		}
		
		double max = Double.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			if (AO[K - 1][i] == Double.POSITIVE_INFINITY) return -1;  //if any node is not reachable by k(starting node given in question) then return -1
			max = Math.max(max, AO[K - 1][i]);  //check max distance from node k
		}
		
		return (int) max;
	}
		
		
		/********* Bellman Ford TC = O(N * E) SC = O(N) ***********************/
		public int networkDelayTimeBF(int[][] times, int N, int K) {
			double[] distanceTo = new double[N];
			Arrays.fill(distanceTo,Double.POSITIVE_INFINITY);
			distanceTo[K-1] = 0; //starting node hence distance = 0
			
			for(int i = 1; i<N; i++){
				for(int[] edge : times){
					int u = edge[0]-1, v = edge[1]-1, w = edge[2];  //edge -1 because of array index
					distanceTo[v] = Math.min(distanceTo[v],distanceTo[u] + w);  //shortest distance from each node
				}
			}
			
			double res = Double.MIN_VALUE;
			for(double x: distanceTo){
				res = Math.max(res,x);  // max because we want to find out the farthest node time taken so that it reaches everywhere
			}
			
			return res == Double.POSITIVE_INFINITY ? -1 : (int)res;
		}
		
		/********* Dijkastra TC = O(V Log E)***********************/
		public int networkDelayTime(int[][] times, int N, int K) {
			Map<Integer, Map<Integer,Integer>> map = new HashMap<>();
			for(int[] time : times){
				map.putIfAbsent(time[0], new HashMap<>());
				map.get(time[0]).put(time[1], time[2]);
			}
			
			//distance, node into pq
			Queue<int[]> pq = new PriorityQueue<>((a,b) -> (a[0] - b[0]));
			pq.add(new int[]{0, K});
			boolean[] visited = new boolean[N+1];
			int res = 0;
			
			while(!pq.isEmpty()) {
				int[] cur = pq.remove();
				int curNode = cur[1];
				int curDist = cur[0];
				if (visited[curNode]) continue;
				
				visited[curNode] = true;
				res = curDist;
				N--;
				if(N == 0) return res;
				if(map.containsKey(curNode)){
					for(int next : map.get(curNode).keySet()){
						pq.add(new int[]{curDist + map.get(curNode).get(next), next});
					}
				}
			}
			return N == 0 ? res : -1;
		}
		public static void main(String[] args) {
			int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
			int N = 4;
			int k = 2;
			M0743_NetworkDelayTime cl = new M0743_NetworkDelayTime();
			System.out.println(cl.networkDelayTimeFW(times,N,k));
		}
	}
