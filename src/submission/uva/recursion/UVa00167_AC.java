package submission.uva.recursion;

import java.util.Arrays;
import java.util.Scanner;

public class UVa00167_AC {

	public static void main(String[] args) {
		UVa00167_AC problem = new UVa00167_AC();
		problem.solve();
	}
		
	private Scanner sc = null;
	private int T;
	private int[][] board = null;

	private void solve() {
		sc = new Scanner(System.in);
		T = sc.nextInt();
		while(T-- > 0) {
			readCase();
			
			finalSum = 0;
			Arrays.fill(pos, -1);
			
			backtrack(0,0);
			
			printSolution();
		}		
	}
	
	private void readCase() {
		board = new int[8][8];			
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = sc.nextInt();
			}
		}
	}
	
	private void printSolution() {
		String resString = String.format("%05d", finalSum);		
		char[] resChars = resString.toCharArray();
		for(int i = 0; i < resChars.length; i++) {
			if(resChars[i] == '0'){
				resChars[i] = ' ';
			}else {
				break;
			}
		}
		resString = new String(resChars);
		System.out.println(resString);
	}
	
	private int[] pos = new int[8];
	private boolean validMove(int x, int y) {
		for(int i = 0; i < x; i++) {
			if(pos[i] == y || Math.abs(y - pos[i]) == Math.abs(x - i))
				return false;
		}
		
		return true;
	}
	
	private int finalSum = 0;
	
	private void backtrack(int n, int sum) {		
		if(n == 8) {
			if(sum > finalSum)
				finalSum = sum;
			return;
		}
		
		for(int i = 0; i < 8; i++) {
			if(validMove(n, i)) {
				pos[n] = i;
				backtrack(n + 1, sum + board[n][i]);				
			}
		}
	}
}
