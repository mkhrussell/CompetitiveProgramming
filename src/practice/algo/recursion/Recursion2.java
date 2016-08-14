package practice.algo.recursion;

public class Recursion2 {

	public static void main(String[] args) {
		Recursion2 recursion = new Recursion2();
		recursion.solve();		
	}
	
	int N = 3;
	int[] inputSet = new int[] {2, 3, 5};
	public void solve() {
		backtrack(0, N, new int[N]);
	}
	
	private void printSubsets(int[] visited) {
		
		System.out.print("{");
		boolean flag = true;
		for(int i = 0; i < visited.length; i++) {
			if(visited[i] == 1) {
				if(flag) {
					System.out.printf(" %d", inputSet[i]);
					flag = false;
				}else {
					System.out.printf(", %d", inputSet[i]);
				}
			}
		}
		System.out.println(" }");
	}
	
	private void backtrack(int i, int N, int[] visited) {
		if(i == N) {
			printSubsets(visited);			
			return;
		}
		
		visited[i] = 0;
		backtrack(i + 1, N, visited);
		
		visited[i] = 1;
		backtrack(i + 1, N, visited);
	}
}
