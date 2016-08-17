package submission.uva.recursion;

import java.util.Arrays;
import java.util.Scanner;

public class UVa00750 {
	
	public static void main(String[] args) {
		UVa00750 problem = new UVa00750();
		problem.solve();
	}
	
	private Scanner sc = null;
	
	private int T;
	private int X, Y;
	
	private void readCase() {
		X = sc.nextInt();
		Y = sc.nextInt();
	}
	
	public void solve() {
		sc = new Scanner(System.in);
		T = sc.nextInt();		
		while(T-- > 0) {
			readCase();			
			findSolution();			
		}
	}

	private void findSolution() {
		Arrays.fill(pos, -1);
		pos[Y] = X; // Given position
		
		solNum = 1;
		
		System.out.println("SOLN       COLUMN");
		System.out.println(" #      1 2 3 4 5 6 7 8");
		System.out.println();
		
		backtrack(1);
		
		if(T > 0)
			System.out.println();
	}
	
	private int[] pos = new int[9]; // 0th position will be ignored
	private int solNum;
	
	// Backtrack column-wise
	private void backtrack(int col) {
		if(col == 9) {
			if(pos[Y] == X) {
				
				if(solNum < 10)
					System.out.print(" " + (solNum++) + "     ");
				else if(solNum >= 10)
					System.out.print((solNum++) + "     ");
				
				for(int i = 1; i < 9; i++) {
					System.out.print(" " + pos[i]);
				}
				
				System.out.println();
			}
			
			return;
		}
		
		for(int row = 1; row < 9; row++) {
			if(isValidMove(row, col)) {
				pos[col] = row;
				backtrack(col + 1);
			}
		}
	}
	
	boolean isValidMove(int x, int y) {
		for(int i = 1; i < y; i++) {
			if(pos[i] == x || Math.abs(x - pos[i]) == Math.abs(y - i))
				return false;
		}
		return true;
	}
}
