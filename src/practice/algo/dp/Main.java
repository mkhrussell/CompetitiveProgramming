package practice.algo.dp;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main prob = new Main();
		prob.solve();
	}

	private Scanner sc = null;
	private int T;	
	
	private void solve() {
//		try {
//			System.setIn(new FileInputStream("UVa11450_in.txt"));
//		}catch(Exception e) {}
		
		sc = new Scanner(System.in);
		
		T = sc.nextInt();		
		while(T-- > 0) {
			readCase();
			findSolution();
		}
	}
	
	private int[][] garments = null;
	private int M, C;
	
	private void readCase() {
		M = sc.nextInt();
		C = sc.nextInt();
		
		garments = new int[C][20];
		for(int i = 0; i < C; i++) {
			
			int k = sc.nextInt();
			int[] models = new int[k];
			
			for(int j = 0; j < k; j++) {
				models[j] = sc.nextInt();
			}
			garments[i] = models;
		}
	}
	
	private void findSolution() {
		
		// Initialize DP memo
		memo = new int[M + 1][C + 1];
		for(int i = 0; i < memo.length; i++) {
			Arrays.fill(memo[i], -1);
		}
		
		int reqrdMoney = shop(); // Call shop function
		
		if(reqrdMoney < 0) {
			System.out.println("no solution");
		}else {
			System.out.println("" + reqrdMoney);
		}
	}
	
	private int[][] memo = null;
	
	private int shop() {
		
		for (int money = 0; money <= M; money++) {
			
			memo[money][0] = 0; // base case solution
			
			for (int c = 1; c <= C; c++) {
				for(int i = 0; i < garments[c - 1].length; i++) {
					
					int ci = garments[c - 1][i]; // the price of model ci of garment c-1
					
					if (money >= ci && memo[money - ci][c - 1] != -1) {  // if this model can produce a solution
						if (memo[money - ci][c - 1] + ci > memo[money][c]) {
							memo[money][c] = memo[money - ci][c - 1] + ci; // update the maximum money spent
						}
					}
				}
			}
		}
		
		return memo[M][C];
	}
}
