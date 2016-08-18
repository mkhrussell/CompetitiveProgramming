package practice.algo.subset;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main problem = new Main();
		problem.solve();
	}

	private Scanner sc = null;
	private int[] numbers;	
		
	private void solve() {
//		try {
//			System.setIn(new FileInputStream("UVa10344_in.txt"));
//		}catch (Exception e) {}
		
		sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			numbers = new int[5];
			readCase();
			if(isEndOfInput())
				break;
			
			flag = false;
			
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
		permuteNumbers(0, new boolean[5], numbers.clone());
	}
	
	boolean flag = false;
	private void calculate(int n, int sum, int[] numCombination) {
		if(flag)
			return;
		
		if(n == 5) {
			if(sum == 23) {
				flag = true;
			}			
			return;
		}
		
		calculate(n + 1, sum + numCombination[n], numCombination);
		calculate(n + 1, sum - numCombination[n], numCombination);
		calculate(n + 1, sum * numCombination[n], numCombination);
	}
	
	private void permuteNumbers(int n, boolean[] visited, int[] combination) {
		if(flag)
			return;
		
		if(n == 5) {
			calculate(1, combination[0], combination.clone());
			return;
		}
		
		for(int i = 0; i < 5; i++) {
			if(!visited[i]) {
				boolean[] newVisited = visited.clone();
				newVisited[i] = true;
				int[] newCombination = combination.clone();
				newCombination[i] = numbers[n];
				permuteNumbers(n + 1, newVisited, newCombination);
			}			
		}
	}

	private void printSolution() {
		if(flag) {
			System.out.println("Possible");
		}else {
			System.out.println("Impossible");
		}
	}
}
