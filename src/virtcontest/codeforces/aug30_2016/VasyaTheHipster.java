package virtcontest.codeforces.aug30_2016;

/*
 * http://codeforces.com/contest/581/problem/A
 */

import java.util.Scanner;

public class VasyaTheHipster {

	public static void main(String[] args) {
		VasyaTheHipster prob = new VasyaTheHipster();
		prob.solve();		
	}

	private Scanner sc = null;
	
	private void solve() {
		sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		int daysForBothColor = 0;
		int daysForSameColor = 0;
		if(a >= b) {
			daysForBothColor = b;
			daysForSameColor = (a - b) / 2;			
		}else {
			daysForBothColor = a;
			daysForSameColor = (b - a) / 2;
		}
		
		System.out.println(daysForBothColor + " " + daysForSameColor);
	}	
}
