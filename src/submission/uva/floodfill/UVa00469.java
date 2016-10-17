package submission.uva.floodfill;

import java.util.Scanner;

public class UVa00469 {

	public static void main(String[] args) {
		UVa00469 sol = new UVa00469();
		sol.run();
	}

	Scanner in;
	int N, M;
	char[][] grid = new char[105][105];
	
	private void run() {
		in = new Scanner(System.in);
		int T = in.nextInt();
		String line = "";
		
		while(T-- > 0) {
			if(in.hasNext()) {
				int row = 0;
				int column = 0;
				for(row = 0; (line = in.next()).charAt(0) == 'L' || line.charAt(0) == 'W'; row++) {
					for(column = 0; column < line.length(); column++) {
						grid[row][column] = line.charAt(column);
					}
				}
				
				N = row;
				M = column;
				
				int r = Integer.parseInt(line);
				int c = in.nextInt();
				while(true) {
					calculateArea(r - 1, c - 1); // Area Calculation
					if(!in.hasNextInt())
						break;
					
					r = in.nextInt();
					c = in.nextInt();
				}
				
				if(T != 0) {
					System.out.println();
				}
			}
		}
	}
	
	/* dx[]          dy[]
	 * -1 -1 -1      -1  0 +1
	 *  0  0  0      -1  0 +1
	 * +1 +1 +1      -1  0 +1
	 */
	
	int[] dx = {-1,-1,-1, 0, 0, 0, 1, 1, 1};
	int[] dy = {-1, 0, 1,-1, 0, 1,-1, 0, 1};
	
	boolean isValid(int x, int y) {
		return (x >= 0 && y >= 0 && x < N && y < M) && grid[x][y] == 'W' && !visited[x][y];
	}
	
	boolean[][] visited;

	private void calculateArea(int x, int y) {
		visited = new boolean[N][M];
		System.out.printf("%d%n", dfs(x, y));
	}	
	
	private int dfs(int x, int y) {
		if(!isValid(x, y)) {
			return 0;
		}
		
		visited[x][y] = true;
		int result = 1;
		
		for(int di = 0; di < dx.length; di++) {
			int r = x + dx[di];
			int c = y + dy[di];
			result += dfs(r, c);
		}
		return result;
	}

}
