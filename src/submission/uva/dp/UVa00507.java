package submission.uva.dp;

import java.util.Scanner;

public class UVa00507 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int nCase = 1; nCase <= T; nCase++) {
			int N = sc.nextInt();
			
			N = N - 1;
			
			int[] nums = new int[N];
			
			boolean allValuesAreNegative = true;
			for(int i = 0; i < N; i++) {
				nums[i] = sc.nextInt();				
				allValuesAreNegative &= (nums[i] < 0); // Negative
			}
						
			if(allValuesAreNegative) { // Avoid running algorithm
				System.out.printf("Route %d has no nice parts%n", nCase);
			}else { // Run Kadane's algorithm which needs at least one positive number	
				
				int maxSum = Integer.MIN_VALUE;
				int maxStart = 0, maxEnd = 0;
				
				int sum = 0;
				int start = 0;
				for(int i = 0; i < N; i++) {
					sum += nums[i];
					if(sum < 0) {
						start = i + 1;
						sum = 0;						
					}
					
					if(sum > maxSum || (sum == maxSum && (i - start > maxEnd - maxStart))) {
						maxSum = sum;
						maxStart = start;
						maxEnd = i;
					}
					
				}

				if(maxSum > 0) {
					System.out.printf("The nicest part of route %d is between stops %d and %d%n", nCase, (maxStart + 1), (maxEnd + 2));
				}else {
					System.out.printf("Route %d has no nice parts%n", nCase);
				}
			}
		}
		sc.close();
	}

}

/* 1: 
   0
   2:
   1
   3:
   2
   4:
   3
   5:

3
3
-1
 6

10
 4
-5
 4
-3
 4
 4
-4
 4
-5

4
-2
-3
-4

*/