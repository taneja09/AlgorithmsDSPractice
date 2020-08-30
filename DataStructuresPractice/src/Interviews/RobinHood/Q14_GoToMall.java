package Interviews.RobinHood;

import java.util.LinkedList;
import java.util.*;

public class Q14_GoToMall {
	private int movingDiagonally(int n, int m , int x1, int y1, int x2, int y2){
		int move = 0;
		int[] dir = {1,1};
		Queue<int[]> q= new LinkedList<>();
		Set<Point> set = new HashSet();
		set.add(new Point(x1,y1,dir[0], dir[1]));
		q.add(new int[] {x1,y1});
		
		while(!q.isEmpty()){
			int[] arr = q.poll();
			int x = arr[0] + dir[0];
			int y = arr[1] + dir[1];
			
			if(x >= n || x < 0 ){
				dir[0] =  -1;
				q.add(new int[] {x,y});
				move += 1;
				continue;
			}else if(y >= m || y < 0){
				dir[1] = -1;
				q.add(new int[]{x,y});
				move += 1;
				continue;
			}
			
			move += 1;
			
			Point temp = new Point(x1,y1,dir[0], dir[1]);
			if(set.contains(temp)) return -1;
			set.add(temp);
			
			//check if we reach target
			if(x == x2 && y == y2) return move;
			q.add(new int[]{x,y});
		}
	
		return -1;
	}
	
class Point {
		int x, y, dx, dy;

		Point(int x, int y, int dx, int dy) {
			this.x = x;
			this.y = y;
			this.dx = dx;
			this.dy = dy;
		}

		public boolean equals(Object o) {
			if (o instanceof Point) {
				Point p = (Point) o;
				return this.x == p.x && this.y == p.y && this.dx == p.dx && this.dy == p.dy;
			}
			return false;
		}

		public int hashCode() {
			return Objects.hash(x, y, dx, dy);
		}
	}
	public static void main(String[] args) {
		Q14_GoToMall cl = new Q14_GoToMall();
		System.out.println(cl.movingDiagonally(5, 3, 2, 0, 3, 2));
	}
}
