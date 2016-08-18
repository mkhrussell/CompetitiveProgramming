package practice.algo.floodfill;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main problem = new Main();
		problem.solve();
	}
	
	private Scanner sc = null;
	public void solve() {
//		try {
//			System.setIn(new FileInputStream("UVa11094_in.txt"));
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
		sc = new Scanner(System.in);
		// SOLVE
		while(sc.hasNextLine()) {
			readCase();
		
			findSolution();
			printSolution();
		}
		// END SOLVE
	}
		
	private void printSolution() {
		
	}
	
	private void findSolution() {
		
	}

	private void readCase() {

	}
}
