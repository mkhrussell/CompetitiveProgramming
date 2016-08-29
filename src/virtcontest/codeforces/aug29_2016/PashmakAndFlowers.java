package virtcontest.codeforces.aug29_2016;

/*
 * http://codeforces.com/contest/459/problem/B
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class PashmakAndFlowers {

	public static void main(String[] args) {
		PashmakAndFlowers prob = new PashmakAndFlowers();
		prob.solve();
	}

	private Scanner sc = null;	
	private void solve() {
		try {
			System.setIn(new FileInputStream("PashmakAndFlowers_in.txt"));
		}catch(Exception e) {
			// Do nothing
		}
		
		sc = new Scanner(System.in);
		long N = sc.nextLong();
		
		long min = Long.MAX_VALUE;
		long max = 0;
				
		long countMin = 0;
		long countMax = 0;
		
		long currentNum;
		for(long i = 0; i < N; i++) {
			currentNum = sc.nextLong();
			
			if(currentNum > max) {
				max = currentNum;
				countMax = 1;
			} else if(currentNum == max) {
				countMax++;
			}
			
			if(currentNum < min) {
				min = currentNum;
				countMin = 1;
			}else if(currentNum == min) {
				countMin++;
			}
		}
		
		long maxDiff = max - min;
		long numWays = countMin * countMax;
		if(min == max) {
			numWays = N * (N - 1) / 2;
		}
		
		System.out.println("" + maxDiff + " " + numWays);
		
		sc.close();				
	}

}
