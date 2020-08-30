package LC_Medium;
/*
https://www.youtube.com/watch?v=rKQaZuoUR4M
 */
import java.util.*;

public class M0207_CourseSchedulePart1 {
    
    /******************* DFS TC = O(V+E) SC = (V + E) **********************   V = number of vertices & E = number of edges ***/
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        
        //for every vertex there will be neighbour vertex list hence initializing an adjacency list
        List<Integer>[] graph = new List[numCourses];
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList();

        //to keep track of visited vertices
        int[] state = new int[numCourses];
        
        //populate adjacency list
        for(int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            graph[from].add(to);
        }

        for(int i=0; i<numCourses; i++){
            if(!dfs(graph,state,i))  // if the dfs return false , return false because there is cycle
                return false;
        }
        return true;
    }

    private boolean dfs(List<Integer>[] graph, int[] state, int course){
        if(state[course] == 2) return true;  //course is finished
        if(state[course] == 1) return false; // Course is visited
        state[course] = 1;
        
        for(int nextCource : graph[course]){
            if(!dfs(graph,state,nextCource)) return false;
        }
        
        state[course] = 2;
        return true;

    }
    /*
        BFS
        //    queue: unexamined nodes with no incoming edges
        //    Decrease count of incoming edges for the target nodes
        //    Get the next nodes to be examined
        //
        //  When to stop?
        //    When BFS stops, there should be no unexamined edges
        //    Or all nodes get examined
        //
        //  Follow-up: what if there are duplicate edges?
     */
    
    /******************* BFS TC = O(V+E) SC = (V + E) **********************   V = number of vertices & E = number of edges ***/
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        int[] InDegree = new int[numCourses];   //check the indegree at a vertex
        Queue queue = new LinkedList();  //for BFS

        int count = 0;  //number of courses I need to complete if the count doesn't match with given course count - then false

        for (int i = 0; i < numCourses; i++)
            graph[i] = new ArrayList();

        for(int[] edge : prerequisites){
            int from = edge[1], to = edge[0];
            graph[from].add(to);
            InDegree[to]++;
        }
        
        for (int i = 0; i < InDegree.length; i++) {
            if (InDegree[i] == 0) {  //add the course to queue if its independent and doesnt have any pre-req
                queue.add(i);
            }

        }
        
        while(!queue.isEmpty()){
            int course = (int) queue.poll();
            count++;
            for(int next : graph[course]){
                InDegree[next]--;
                if(InDegree[next] == 0){  //If its pre-requisites are over then only treat it as independent course and add to queue
                    queue.add(next);
                }
            }
        }

        if(count == numCourses)
            return true;

        else
            return false;
    }

    public static void main(String[] args) {
        M0207_CourseSchedulePart1 cl = new M0207_CourseSchedulePart1();
        //int numCourses = 2;
        //int[][]  prerequisites = {{1,0}};
        //int[][]  prerequisites = {{1,0},{0,1}};

        int numCourses = 3;
        int[][]  prerequisites = {{1,0},{0,2}};
        Boolean res = cl.canFinishDFS(numCourses,prerequisites);
        System.out.print(res);
    }
}
