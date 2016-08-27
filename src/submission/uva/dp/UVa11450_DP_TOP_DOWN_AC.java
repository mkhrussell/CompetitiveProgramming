package submission.uva.dp;

/*
 * This is a learning purpose solution.
 * Credit: http://www.algorithmist.com/index.php/UVa_11450
 */

import java.util.Arrays;
import java.util.Scanner;

public class UVa11450_DP_TOP_DOWN_AC {

	public static void main(String[] args) {
		UVa11450_DP_TOP_DOWN_AC prob = new UVa11450_DP_TOP_DOWN_AC();
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
		
		int reqrdMoney = shop(M, C); // Call shop function
		
		if(reqrdMoney < 0) {
			System.out.println("no solution");
		}else {
			System.out.println("" + reqrdMoney);
		}
	}
	
	private int[][] memo = null;
	
	private int shop(int money, int c) {
		if(money < 0) {
			return -1; // Negative money: no solution
		}
		
		if(c == 0) {
			return 0;
		}
		
		int ret = memo[money][c]; // Get memoized solution
		
		if( ret != -1) { // Check memo value is a solution or not
			return ret; // Return, no further processing required.
		}else {	// Memo value was not a solution. Then calculate solution.
			for(int i = 0; i < garments[c - 1].length; i++) {
				
				int ci = garments[c - 1][i]; // the price of model ci of garment c-1
				
				int tmp = shop(money - ci, c - 1); // Spent whole money deducting ci from current money: money - ci.
				
				if(tmp >= 0) {	// if this model can produce a solution for money = money - ci and c = c - 1
					if(tmp + ci > ret) {
						ret = tmp + ci; // update the maximum money spent
					}
				}			
			}
		}
		
		memo[money][c] = ret; // Save in memo
		
		return ret;
	}
}
