package submission.uva.intro;

import java.util.Scanner;

public class UVa11470 {

	public static void main(String[] args) {
		UVa11470 sol = new UVa11470();
		sol.run();
	}
	
	int numCase;
	Scanner sc;
	
	void run() {
		sc = new Scanner(System.in);		
		for(numCase = 1; (N = sc.nextInt()) > 0; numCase++) {
			takeInput();
			findSolution();
			printSolution();
		}
		sc.close();
	}
	
	int N;
	int[][] square;
	
	void takeInput() {
		square = new int[N][N];
		for(int row = 0; row < N; row++) {
			for(int column = 0; column < N; column++) {
				square[row][column] = sc.nextInt();
			}
		}
	}
	
	int numSquareSums;
	int[] squareSums;
	
	void findSolution() {
		if(N % 2 == 0)
			numSquareSums = N/2;
		else
			numSquareSums = N/2 + 1;
		
		squareSums = new int[numSquareSums];
		
		for(int ssIndex = 0; ssIndex <= numSquareSums; ssIndex++) {
			for(int i = ssIndex; i <= N - 1 - ssIndex; i++) {
				squareSums[ssIndex] +=  square[ssIndex][i];
				square[ssIndex][i] = 0;
				
				squareSums[ssIndex] +=  square[N - 1 - ssIndex][i];
				square[N - 1 - ssIndex][i] = 0;
				
				squareSums[ssIndex] +=  square[i][ssIndex];
				square[i][ssIndex] = 0;				
				
				squareSums[ssIndex] +=  square[i][N - 1 - ssIndex];
				square[i][N - 1 - ssIndex] = 0;
			}
		}
	}
	
	void printSolution() {
		System.out.printf("Case %d:", numCase);
		for(int i = 0; i < numSquareSums; i++) {
			System.out.printf(" %d", squareSums[i]);
		}
		System.out.println();
	}
}
