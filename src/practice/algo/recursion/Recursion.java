package practice.algo.recursion;

public class Recursion {

	public static void main(String[] args) {
		Recursion recursion = new Recursion();
		recursion.solve();		
	}
	
	int N = 3;
	
	public void solve() {
		backtrack(0, N, new int[N]);
	}
	
	private void printVisited(int[] visited) {
		for(int i = 0; i < visited.length; i++) {
			System.out.printf("%d", visited[i]);
		}
		System.out.println();
	}
	
	private void backtrack(int i, int N, int[] visited) {
		if(i == N) {
			printVisited(visited);			
			return;
		}
		
		visited[i] = 0;
		backtrack(i + 1, N, visited);
		
		visited[i] = 1;
		backtrack(i + 1, N, visited);
	}
}
