package submission.uva.recursion;

import java.util.Scanner;

public class UVa00562 {

	public static void main(String[] args) {
		UVa00562 sol = new UVa00562();
		sol.run();
	}
	
	Scanner sc;
	int T;
	
	void run() {
		sc = new Scanner(System.in);
		T = sc.nextInt();
		
		while(T-- > 0) {
			takeInput();			
			findSolution();
		}
	}

	int minDiff;
	
	void findSolution() {
		minDiff = Integer.MAX_VALUE;
		divide(0, 0);
		System.out.println("" + minDiff);
	}
	
	int M;
	int[] coins;
	int superSum;
	
	void takeInput() {
		M = sc.nextInt();
		coins = new int[M];
		superSum = 0;
		for(int i = 0; i < M; i++) {
			coins[i] = sc.nextInt();
			superSum += coins[i];
		}
	}

	void divide(int n, int sum) {
		if(n == M) {
			if(abs((superSum - sum) - sum) < minDiff) {
				minDiff = abs((superSum - sum) - sum);
			}
			return;
		}
		
		divide(n + 1, sum + coins[n]);
		divide(n + 1, sum);
	}
	
	int abs(int num) {
		return num >= 0 ? num : -num;
	}
}
