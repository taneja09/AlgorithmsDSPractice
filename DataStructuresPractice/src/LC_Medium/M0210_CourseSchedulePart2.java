package LC_Medium;

import java.util.*;

public class M0210_CourseSchedulePart2 {
    /******************* BFS TC = O(V+E) SC = (V + E) **********************   V = number of vertices & E = number of edges ***/
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph= new List[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[numCourses];
        int[] Indegree = new int[numCourses];
        
        for(int i = 0; i< numCourses; i++)
            graph[i] = new ArrayList();
        
        for(int[] edge: prerequisites){
            int from = edge[1], to = edge[0];
            graph[from].add(to);
            Indegree[to]++;  //hw many pre-requisites are required
        }
        
        for(int i = 0; i< numCourses; i++){
            if(Indegree[i] == 0)
                queue.add(i);
        }
        
        int resIndex = 0;
        while(!queue.isEmpty()){
            int curr = queue.poll();
            result[resIndex] = curr;
            resIndex++;
            for(int next : graph[curr] ){
                Indegree[next]--;
                if(Indegree[next] == 0) queue.add(next);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        M0210_CourseSchedulePart2 cl = new M0210_CourseSchedulePart2();
        //int numCourses = 2;
        //int[][]  prerequisites = {{1,0}};
        //int[][]  prerequisites = {{1,0},{0,1}};
        int numCourses = 2;
        int[][]  prerequisites = {{1,0}};
        int[] res = cl.findOrder(numCourses,prerequisites);
        System.out.print(Arrays.toString(res));
    }
}
