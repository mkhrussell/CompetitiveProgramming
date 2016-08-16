package practice.algo.subset;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main problem = new Main();
		problem.solve();
	}

	private Scanner sc = null;
	private int[] numbers;	
	private char[] operators = new char[] {'+', '-', '*'};
	
	private void solve() {
		try {
			System.setIn(new FileInputStream("UVa10344_in.txt"));
		}catch (Exception e) {}
		
		sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			numbers = new int[5];
			readCase();
			if(isEndOfInput())
				break;
			
			findSolution();
			printSolution();						
		}
	}

	private void readCase() {
		for(int i = 0; i < 5; i++) {
			numbers[i] = sc.nextInt();			
		}
	}
	
	private boolean isEndOfInput() {
		boolean ret = numbers[0] == 0;
		for(int i = 1; i < 5; i++) {
			ret &= numbers[i] == 0;
		}
		return ret;
	}

	private void findSolution() {
		//System.out.println(Arrays.toString(numbers));
		int[] path = new int[4];
		Arrays.fill(path, -1);
		permutation(0, path.clone());		
	}
	
	private void permutation(int n, int[] path) {
		if(n == 4) {
			System.out.println(Arrays.toString(path));			
			for(int i = 0; i < 4; i++) {
				System.out.print(operators[path[i]] + " ");
			}
			System.out.println();
			
			
		
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			int[] newPath = path.clone();
			newPath[n] = i;
			permutation(n + 1, newPath);
		}
	}
	
	private void calculate(int n, boolean[] visited) {
		
	}

	private void printSolution() {
		
	}
}
