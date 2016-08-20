package submission.uva.toposort;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class UVa11686 {

	public static void main(String[] args) {
		UVa11686 problem = new UVa11686();
		problem.solve();
	}
	
	private Scanner sc = null;	
	private void solve() {
//		try {
//			System.setIn(new FileInputStream("UVa11686_in.txt"));
//		}catch(Exception e) {}
		
		sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			if(N == 0 && M == 0)
				break; // End of case
			
			if(M == 0) { // Just print the numbers
				for(int n = N; n > 0; n--){
					System.out.println("" + n);
				}
			}else { // Run the algorithm
				readCase();
				findSolution();
			}			
		}
	}
	
	private int N, M;
	private ArrayList<Integer>[] adjList;
	private int[] indegree;
	private int[] outdegree;
	
	@SuppressWarnings("unchecked")
	private void readCase() {
		indegree = new int[N + 1];
		outdegree = new int[N + 1];
		adjList = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			adjList[i]  = new ArrayList<Integer>();			
		}
		
		int u, v;
		for(int i = 1; i <= M; i++) {
			u = sc.nextInt();
			v = sc.nextInt();
			
			adjList[u].add(v);
			outdegree[u]++;
			indegree[v]++;
		}
	}
	
	private void findSolution() {
		
		// Kahn's Algo
		KQueue vq = new KQueue();
		for(int u = 1; u <= N; u++) {
			if(indegree[u] == 0) {
				vq.push(u);
			}
		}
		
		int count = 0;		
		Vector<Integer> order = new Vector<>();
		while(!vq.isEmpty()) {
			int u = vq.front();
			vq.pop();
			order.add(u);
			
			for(int v : adjList[u]) {
				if(--indegree[v] == 0) {
					vq.push(v);
				}
			}
			
			count++;
		}
		
		if(count != N) { // Not a DAG, has a cycle
			System.out.println("IMPOSSIBLE");
		}else {
			for(int n : order) {
				System.out.println("" + n);
			}
		}
	}
	
	class KQueue {
		class Node {
			Node next = null;
			int value;
			public Node (int value) {
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
			tmpNode = null;
		}
		
		public boolean isEmpty() {
			if(first == null)
				return true;
			
			return false;
		}
		
		public void pop() {
			if(!isEmpty()) {
				first = first.next;
			}
		}
		
		public int front() {			
			return first.value;
		}
	}
}
