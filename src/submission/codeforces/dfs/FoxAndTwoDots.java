package submission.codeforces.dfs;

import java.util.Scanner;

public class FoxAndTwoDots {

	public static void main(String[] args) {
		FoxAndTwoDots fatd = new FoxAndTwoDots();
		fatd.run();
	}
	
	Scanner in;
		
	void run() {
		in = new Scanner(System.in);
		readGrid();
		findSolution();		
	}

	int N, M;
	char[][] charGrid;	
	
	void readGrid() {
		N = in.nextInt();
		M = in.nextInt();
		charGrid = new char[N][M];		
		
		for(int i = 0; i < N; i++) {
			String line = in.next();
			for(int j = 0; j < M; j++) {
				charGrid[i][j] = line.charAt(j);
			}
		}
	}
	
	int[] dx = {-1, +1,  0,  0};
	int[] dy = { 0,  0, -1, +1};
		
	int buildingCount;
	boolean cycleFound;
	
	int[][] visited;
		
	void findSolution() {
		visited = new int[N][M];
		
		outer:
		for(int x = 0; x < N; x++) {
			for(int y = 0; y < M; y++) {
				cycleFound = false;
				buildingCount = 0;
				dfs(x, y, -1, -1, charGrid[x][y]);
				if(cycleFound)
					break outer;
			}
		}
		
		if(cycleFound) {
			System.out.println("Yes");
		}else {
			System.out.println("No");
		}
	}	
	
	void dfs(int x, int y, int parentX, int parentY, char color) {
		if(!isAdjacent(x, y, color)){ // Not adjacent
			return;
		}
		
		if(visited[x][y] != 0) // Visited
			return;
		
		visited[x][y] = ++buildingCount;
		for(int i = 0; i < 4; i++) {
			int childX = x + dx[i];
			int childY = y + dy[i];
			
			// Cycle detection
			if(isAdjacent(childX, childY, color)) {
				if(childX != parentX && childY != parentY) {
					if(visited[childX][childY] != 0) {
						if(buildingCount >= 4) {
							cycleFound = true;
						}						
					}
				}
			}
			
			dfs(childX, childY, x, y, color); // DFS
		}
	}
	
	boolean isAdjacent(int x, int y, char color) {
		return (x >= 0 && x < N) && (y >= 0 && y < M) && (charGrid[x][y] == color); 
	}
}