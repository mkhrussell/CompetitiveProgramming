package practice.algo.subset;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class UVa10261 {
	private Scanner sc = new Scanner(System.in);
	private int T;
	private int nCase;
	
	private final int MAX_INPUT_SIZE = 100;
	int minDiff = 999999;
	
	private int inputSize;
	private int lineSize;
	private int loadingSize;
	
	private int[] inputSet;
	private boolean[] setStatus;
	private boolean[] finalSetStatus;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("C:\\Users\\mkhrussell\\workspace\\Algo\\src\\org\\onlinejudge\\uva\\subset\\UVa10261_in.txt"));
		System.setOut(new PrintStream("C:\\Users\\mkhrussell\\workspace\\Algo\\src\\org\\onlinejudge\\uva\\subset\\UVa10261_out.txt"));
		
		UVa10261 problem = new UVa10261();
		problem.run();
	}
	
	public void run() {
		T = sc.nextInt();
		for(nCase = 1; nCase <= T; nCase++) {
			readCase();
			solveCase();
			printCase();
			
			//for(int i = 0; i < inputSize; i++) {
			//	System.out.println("" + inputSet[i]);
			//}
		}
	}
	
	private void readCase() {
		lineSize = sc.nextInt();
		lineSize *= 100;
		loadingSize = lineSize * 2;
		minDiff = 999999;
		
		inputSet = new int[MAX_INPUT_SIZE];
		int nextCarSize = 0;
		int totalSize = 0;
		for(inputSize = 0; sc.hasNextInt() & (totalSize <= loadingSize); inputSize++) {
			nextCarSize = sc.nextInt();
			if(nextCarSize == 0) /* End of case reached */
				break;
			
			if((totalSize + nextCarSize) <= loadingSize) {
				inputSet[inputSize] = nextCarSize;
				totalSize += nextCarSize;
			}
			else {
				while(sc.hasNextInt()) { /* Skip to end of case */
					nextCarSize = sc.nextInt();
					if(nextCarSize == 0)
						break;
				}
				break;
			}
		}
	}
	
	private void backtrack(int level, int N, int remainder) {
		if(level == N || remainder <= 0)
		{
			int sumOne = 0;
			int sumTwo = 0;
			for(int i = 0; i < inputSize; i++) {
				if(setStatus[i] == true)
					sumOne += inputSet[i];
				else
					sumTwo += inputSet[i];
			}
			
			if(sumOne > lineSize || sumTwo > lineSize)
				return;
			
			int diff = sumOne >= sumTwo ? sumOne - sumTwo : sumTwo - sumOne;
			if(diff < minDiff) {
				minDiff = diff;
				
				for(int i = 0; i < inputSize; i++) {
					finalSetStatus[i] = setStatus[i];					
				}
				
				System.out.printf("diff: %d\n", diff);
				System.out.printf("sumOne: %d\n", sumOne);
				System.out.printf("sumTwo: %d\n", sumTwo);
			}
			
			return;
		}
		
		if(remainder - inputSet[level] >= 0) {
			setStatus[level] = true;
			backtrack(level + 1, N, remainder - inputSet[level]);
		}
		
		setStatus[level] = false;
		backtrack(level + 1, N, remainder);
	}
	
	private void solveCase() {
		setStatus = new boolean[inputSize];
		finalSetStatus = new boolean[inputSize];
		backtrack(0, inputSize, lineSize);
	}
	
	private void printCase() {
		System.out.printf("%d\n", inputSize);
		for(int i = 0; i < inputSize; i++) {
			if(finalSetStatus[i] == true)
				System.out.println("port");
			else
				System.out.println("starboard");
		}
	}
}
