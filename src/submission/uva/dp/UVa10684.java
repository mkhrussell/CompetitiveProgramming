package submission.uva.dp;

import java.util.Scanner;

public class UVa10684 {

	public static void main(String[] args) {
		UVa10684 sol = new UVa10684();
		sol.solve();
	}

	Scanner sc = null;
	
	int N;
	private void solve() {
		sc = new Scanner(System.in);
				
		while((N = sc.nextInt()) > 0) {
			// Kadane's Algorithm
			int sum = 0, maxSum = Integer.MIN_VALUE;
			for(int i = 0; i < N; i++) {
				sum += sc.nextInt();
				if(sum > maxSum) {
					maxSum = sum;
				}
				
				if(sum < 0) {
					sum = 0;
				}
			}
			
			if(maxSum > 0)
				System.out.printf("The maximum winning streak is %d.%n", maxSum);
			else
				System.out.println("Losing streak.");
		}
	}

}
