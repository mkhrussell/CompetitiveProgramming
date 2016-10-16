package submission.codeforces.adhoc;

/*
 * http://codeforces.com/contest/658/problem/A
 */

import java.util.Scanner;

public class BearAndReverseRadewoosh {

	public static void main(String[] args) {
		BearAndReverseRadewoosh sol = new BearAndReverseRadewoosh();
		sol.run();
	}

	private void run() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int c = in.nextInt();
		int[] points = new int[n];
		for(int pi = 0; pi < n; pi++) {
			points[pi] = in.nextInt();			
		}
		
		int[] times = new int[n];
		for(int ti = 0; ti < n; ti++) {
			times[ti] = in.nextInt();
		}
		in.close();
		
		int limakScore = 0;
		int radewooshScore = 0;
		int timeSpentByLimak = 0;
		int timeSpentByRadewoosh = 0;
		
		for(int li = 0, ri = n - 1; li < n; li++, ri--) {
			timeSpentByLimak += times[li];
			timeSpentByRadewoosh += times[ri];
			
			limakScore += Math.max(0, points[li] - timeSpentByLimak * c);
			radewooshScore += Math.max(0, points[ri] - timeSpentByRadewoosh * c);
		}
		
		String ans = "";
		if(limakScore == radewooshScore) {
			ans = "Tie";
		}else if(limakScore > radewooshScore) {
			ans = "Limak";
		}else {
			ans = "Radewoosh";
		}
		
		System.out.println(ans);
	}

}
