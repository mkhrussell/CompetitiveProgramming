package submission.uva.dfs;

import java.util.Scanner;

public class UVa00539 {

	public static void main(String[] args) {
		UVa00539 sol = new UVa00539();
		sol.run();
	}
	
	Scanner sc;
	
	int numNodes;
	int numEdges;
		
	void run() {
		sc = new Scanner(System.in);
		
		while((numNodes = sc.nextInt()) > 0 && (numEdges = sc.nextInt()) > 0) {
			readGraph();
			findSolution();
		}
		sc.close();
	}

	int[][] graph;
	
	void readGraph() {
		graph = new int[numNodes][numNodes];
		
		for(int edgeIndex = 0; edgeIndex < numEdges; edgeIndex++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			graph[u][v] = 1;
			graph[v][u] = 1;
		}
	}
	
	int maxRoadLength;
		
	void findSolution() {
		maxRoadLength = 0;
		
		for(int u = 0; u < numNodes; u++) {
			dfs(u, 0);
		}
		
		System.out.printf("%d%n", maxRoadLength);
	}

	void dfs(int u, int length) {
		if(length > maxRoadLength) {
			maxRoadLength = length;
		}
		
		for(int v = 0; v < numNodes; v++) {
			if(graph[u][v] == 1) {
				graph[u][v] = 0;
				graph[v][u] = 0;
				
				dfs(v, length + 1);
				
				graph[u][v] = 1;
				graph[v][u] = 1;
			}
		}
	}
}
