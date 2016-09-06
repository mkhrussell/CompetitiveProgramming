package submission.uva.recursion;

import java.util.Scanner;

/*
 * http://uva.onlinejudge.org/external/6/628.pdf
 */

public class UVa00628 {
	public static void main(String[] args) {
		UVa00628 prob = new UVa00628();
		prob.solve();
	}

	private Scanner sc = null;
	
	private int N, M;
	private String[] words = null;
	private String[] rules = null;
	
	private void solve() {
		sc = new Scanner(System.in);		
		while(sc.hasNextLine()) {
			readCase();			
			System.out.println("--");
			findSolution();
		}		
	}

	private void readCase() {
		N = sc.nextInt();
		sc.nextLine(); // Ignore
		
		words = new String[N];
		for(int i = 0; i < N; i++) {
			words[i] = sc.nextLine().trim();
		}
		
		M = sc.nextInt();
		sc.nextLine(); // Ignore
		
		rules = new String[M];			
		for(int i = 0; i < M; i++) {
			rules[i] = sc.nextLine().trim();
		}
	}
	
	private void findSolution() {
		for(String rule : rules) {
			takeNext(0, rule, "");
		}	
	}

	private void takeNext(int i, String rule, String pass) {
		if(i == rule.length()) {
			System.out.println(pass);
			return;
		}
		
		if(rule.charAt(i) == '0') {
			for(int j = 0; j < 10; j++) {
				takeNext(i + 1, rule, pass + j);
			}
		}else {
			for(int k = 0; k < words.length; k++) {
				takeNext(i + 1, rule, pass + words[k]);
			}
		}
	}	
}
