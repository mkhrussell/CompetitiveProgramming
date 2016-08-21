package submission.uva.bfs;

import java.util.Arrays;
import java.util.Scanner;

public class UVa00336 {

	public static void main(String[] args) {
		UVa00336 prob = new UVa00336();
		prob.solve();		
	}
	
	private long[] nodes = null;
	private int nodeIndex;
	
	private int getIndex(long uValue) {
		for(int i = 0; i < nodes.length; i++) {
			if(nodes[i] == uValue)
				return i;
		}
		
		return -1;
	}

	private Scanner sc = null;
	private int nCase = 1; 
	
	private void solve() {
//		try {
//			System.setIn(new FileInputStream("UVa00336_in.txt"));
//		}catch(Exception e) {}
		
		sc = new Scanner(System.in);
		while(sc.hasNextLong()) {
			readCase();
		}
	}

	private final int NC_EXTRA = 50;
	private int NC;
	private int[][] graph = null;
	
	private void readCase() {
		NC = sc.nextInt();
		
		nodes = new long[NC + NC_EXTRA];
		
		// Fill with non-node-indicating numbers to avoid getting wrong index value from getIndex().
		Arrays.fill(nodes, -1l);
		
		graph = new int[NC + NC_EXTRA][NC + NC_EXTRA];
		nodeIndex = 0;
		
		long x, y;
		for(int i = 0; i < NC; i++) {
			x = sc.nextLong();
			y = sc.nextLong();
			
			int u = getIndex(x);
			if(u < 0) {
				u = nodeIndex++;
				nodes[u] = x;
			}
			
			int v = getIndex(y);
			if(v < 0) {
				v = nodeIndex++;
				nodes[v] = y;
			}
			
			// Both way connected
			graph[u][v] = 1;
			graph[v][u] = 1;
		}
		
		while(sc.hasNextLong()) {
			long startNode = sc.nextLong();
			int TTL = sc.nextInt();
			
			if(startNode == 0) {
				break;
			}
			
			int startIndex = getIndex(startNode);
			
			// Individual node, not in the network. All nodes are unreachable.
			if(startIndex < 0) {
				System.out.printf("Case %d: %d nodes not reachable from node %d with TTL = %d.",
					nCase++, nodeIndex, startNode, TTL);
				System.out.println();
				
				continue; // No need further processing. Go to next query.
			}

			// Run BFS for the given source.
			bfs(startIndex, TTL);
		}
	}
	
	private void bfs(int startIndex, int maxDistance) {
		
		boolean[] visited = new boolean[NC + NC_EXTRA];
		int[] dist = new int[NC + NC_EXTRA];
		
		int u = startIndex;
		
		// BFS
		Queue que = new Queue();
		que.push(u);
		dist[u] = 0;
		visited[u] = true;
		
		while(!que.isEmpty()) {
			u = que.front();						
			que.pop();

			for(int v = 0; v < nodeIndex; v++) {
				if(!visited[v]) {
					if(graph[u][v] == 1) {
						que.push(v);
						
						visited[v] = true;
						dist[v] += dist[u] + 1;
					}
				}
			}
		}
		// End BFS
		
		// Visited nodes with the distance of less or equal to TTL are reachable
		int reachableCount = 0;
		for(int i = 0; i < nodeIndex; i++) {
			if(visited[i] && dist[i] <= maxDistance)
				reachableCount++;
		}
		
		// No reachable = (Total Nodes - Reachable Nodes)
		System.out.printf("Case %d: %d nodes not reachable from node %d with TTL = %d.",
				nCase++, (nodeIndex - reachableCount), nodes[startIndex], maxDistance);
		System.out.println();
	}
	
	class Queue {
		
		class Node {
			int value;
			Node next = null;
			public Node(int value) {
				this.value = value;
			}
		}
		
		private Node first = null;
		private Node last = null;
		
		public void push(int value) {
			Node tmpNode = new Node(value);
			if(first == null) {
				first = last = tmpNode;
				return;
			}
			
			last.next = tmpNode;
			last = last.next;
		}
		
		public void pop() {
			if(!isEmpty()) {
				first = first.next;
			}				
		}
		
		public boolean isEmpty() {
			if(first == null)
				return true;
			
			return false;
		}
		
		public int front() {
			return first.value;
		}
	}
}
