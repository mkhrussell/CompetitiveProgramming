package submission.uva.toposort;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;

public class UVa11060 {

	public static void main(String[] args) {
		UVa11060 prob = new UVa11060();
		prob.solve();
	}

	private Scanner sc = null;	
	private int nCase = 1;

	private void solve() {
		sc = new Scanner(System.in);
		
		while(sc.hasNextLine()) {
			readCase();
			
			findSolution();
			nCase++;
		}
	}
	
	/*
	 * Kahn’s algorithm: It looks like a ‘modified BFS’.
	 * 
	 * UVa 11060 - Beverages, requires this Kahn’s algorithm to produce the
	 * required topological sort instead of the DFS-based algorithm shown earlier.
	 * 
	 * 1. enqueue vertices with zero incoming degree into a (priority) queue Q;
	 * 2. while (Q is not empty) {
	 * 		vertex u = Q.dequeue();
	 * 		put vertex u into a topological sort list;
	 * 		remove this vertex u and all outgoing edges from this vertex;
	 * 		if such removal causes vertex v to have zero incoming degree
	 * 		Q.enqueue(v);
	 * }
	 * 
	 */
	
	private void findSolution() {		
		System.out.printf("Case #%d: ", nCase);
		System.out.print("Dilbert should drink beverages in this order:");
		
		// Kahn’s algorithm
		PriorityQueue<Integer> vq = new PriorityQueue<>();		
		for(int i = 0; i < V; i++) {
			if(indegree[i] == 0)
				vq.add(i);				
		}

		Vector<Integer> topoOrder = new Vector<>();
		while(!vq.isEmpty()){
			int u = vq.poll();
			topoOrder.add(u);
			
			for(int v = 0; v < V; v++) {
				if(graph[u][v] == 1) {
					if(--indegree[v] == 0) {
						vq.add(v);
					}
				}
			}
		}

		for(int i : topoOrder) {
			System.out.print(" " + bevList[i]);
		}
		 // End Kahn’s algorithm
		
		System.out.println(".");
		System.out.println();
	}
	
	private int V;
	private int E;
	private String[] bevList = null;
	private int[][] graph = null;
	private int[] indegree = null; // Incoming edge
	
	private void readCase() {
		V = sc.nextInt();
		bevList = new String[V];
		sc.nextLine(); // Ignore		
		for(int i = 0; i < V; i++) {
			bevList[i] = sc.nextLine();
		}
		
		E = sc.nextInt();
		sc.nextLine(); // Ignore
		
		graph = new int[V][V];
		indegree = new int[V];
		
		for(int j = 0; j < E; j++) {
			String uStr = sc.next();
			String vStr = sc.next();
			if(sc.hasNextLine())
				sc.nextLine(); // Ignore
			
			int u = getIndex(uStr);
			int v = getIndex(vStr);
			
			if(graph[u][v] == 0){
				graph[u][v] = 1;
				indegree[v]++; // Increase for destination vertex
			}
		}
		
		if(sc.hasNextLine())
			sc.nextLine();
	}
	
	private int getIndex(String input) {
		for(int i = 0; i < V; i++) {
			if(input.equals(bevList[i]))
				return i;
		}
		
		return -1;
	}
}