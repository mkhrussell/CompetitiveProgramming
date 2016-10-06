package submission.uva.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class UVa10653 {
	
	public static void main(String args[]) {
		UVa10653 sol = new UVa10653();
		sol.run();		
	}
	
	class Point {
		int x, y;
		int dist = Integer.MAX_VALUE;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return new String(x + " " + y);
		}		
	}

    Scanner sc;
	int R, C;
	
	void run() {
		sc = new Scanner(System.in);		
		while((R = sc.nextInt()) > 0 && (C = sc.nextInt()) > 0) {
			readGraph();
			findSolution();
		}
	}
	
	int[][] grid;
	
	Point start, end;
	
	void readGraph() {		
		// Read graph
		grid = new int[R][C];
		int numRowsWithHoles = sc.nextInt();
		while(numRowsWithHoles-- > 0) {
			int row = sc.nextInt();
			int numColumnsWithHoles = sc.nextInt();
			while(numColumnsWithHoles-- > 0) {
				int col = sc.nextInt();
				grid[row][col] = 1;
			}
		}
		
		int sourceX = sc.nextInt();
		int sourceY = sc.nextInt();
		start = new Point(sourceX, sourceY);
		
		int destX = sc.nextInt();
		int destY = sc.nextInt();
		end = new Point(destX, destY);
	}
	
	void findSolution() {
		goToDestination(start); // Run BFS
		System.out.println("" + end.dist);
	}
	
	private int[] dx = {-1, 1, 0, 0};
	private int[] dy = {0, 0, -1, 1};
	
	private boolean isValid(int r, int c) {
		return r < R && r >= 0 && c < C && c >= 0 && grid[r][c] == 0;
	}
	
	Queue<Point> queue = new LinkedList<>();
	
	void goToDestination(Point source) { // BFS
		queue.clear();
		
		source.dist = 0;
		grid[source.x][source.y] = 1;
		queue.add(source);

		boolean destinationFound = false;
		
		while(!queue.isEmpty() && !destinationFound) {
			Point u = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int r = u.x + dx[i];
				int c = u.y + dy[i];
				
				if(isValid(r, c)) {
					grid[r][c] = 1;
					
					Point next = new Point(r, c);
					next.dist = u.dist + 1;
					if(next.x == end.x && next.y == end.y) { // Reached to destination
						destinationFound = true;
						end.dist = next.dist;
						break;
					}					
					queue.add(next);
				}
			}
		}
		
	}	
}