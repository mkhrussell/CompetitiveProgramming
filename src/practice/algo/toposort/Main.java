package practice.algo.toposort;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main prob = new Main();
		prob.solve();
	}
	
	private Scanner sc = null;	
	private int nCase = 1;

	private void solve() {
		try {
			System.setIn(new FileInputStream("UVa11060_in.txt"));
		}catch(Exception e) {}
		
		sc = new Scanner(System.in);
		
		while(sc.hasNextLine()) {
			readCase();
			//if(nCase > 1)
			//	System.out.println();
			
			findSolution();
			nCase++;
		}
	}

	private void findSolution() {
		System.out.printf("Case #%d: ", nCase);
		System.out.print("Dilbert should drink beverages in this order:");
		
		orderList = new int[V];
		orderIndex = 0;
		
		visited = new boolean[V];		
		
		int u = findNextPriority();
		while(u >= 0) {
			dfs(u);
			u = findNextPriority();
		}
		
		for(int i = V - 1; i >= 0; i--) {
			System.out.print(" " + bevList[orderList[i]]);
		}
		
		System.out.println(".");
		System.out.println();
	}
	
	private int[] orderList;
	private int orderIndex; 
	private boolean[] visited;
	private void dfs(int u) {
		if(visited[u])
			return;
		
		visited[u] = true;
		
		int v = findNextPriority(u);
		while(v >= 0) {
			dfs(v);
			v = findNextPriority(u);
		}
		
		orderList[orderIndex++] = u;
	}

	private int V;
	private int E;
	private String[] bevList = null;
	private int[][] graph = null;
	private int[] priority = null;
	
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
		priority = new int[V];
		
		for(int j = 0; j < E; j++) {
			String uStr = sc.next();
			String vStr = sc.next();
			sc.nextLine(); // Ignore
			
			int u = getIndex(uStr);
			int v = getIndex(vStr);
			
			graph[u][v] = 1;
			priority[v]++;
		}
		
		if(sc.hasNextLine())
			sc.nextLine();
	}
	
	private int findNextPriority() {
		int index = -1, tmpMax = -1;		
		for(int i = 0; i < V; i++) {
			if(!visited[i] && priority[i] > tmpMax) {
				tmpMax = priority[i];
				index = i;
			}
		}
		
		if(index >= 0)
			priority[index]--;
		
		return index;
	}
	
	private int findNextPriority(int u) {
		int index = -1, tmpMax = -1;		
		for(int i = 0; i < V; i++) {
			if(graph[u][i] == 1) {
				if(!visited[i] && priority[i] > tmpMax) {
					tmpMax = priority[i];
					index = i;
				}
			}
		}
		
		if(index >= 0)
			priority[index]--;
		
		return index;
	}
	
	private int getIndex(String input) {
		for(int i = 0; i < V; i++) {
			if(input.equals(bevList[i]))
				return i;
		}
		
		return -1;
	}
}
