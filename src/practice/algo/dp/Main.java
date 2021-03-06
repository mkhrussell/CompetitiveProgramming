package practice.algo.dp;

//import java.io.FileInputStream;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main prob = new Main();
		prob.run();
	}

	Scanner sc = null;	
	
	void run() {
//		try {
//			System.setIn(new FileInputStream("UVa00108_in.txt"));
//		}catch(Exception e) {}
		
		sc = new Scanner(System.in);
		generateSumMatrix();
		findMaxSubRectSum();
	}
	
	int N;
	int[][] sumMatrix;
	
	void generateSumMatrix() {
		N = sc.nextInt();
		sumMatrix = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sumMatrix[i][j] = sc.nextInt();
				
				if(i > 0) sumMatrix[i][j] += sumMatrix[i - 1][j];
				if(j > 0) sumMatrix[i][j] += sumMatrix[i][j - 1];
				if(i > 0 && j > 0) sumMatrix[i][j] -= sumMatrix[i - 1][j - 1];
			}
		}
	}
	
	int maxSubRectSum;
	
	void findMaxSubRectSum() {
		maxSubRectSum = -127*100*100;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				
				for(int k = i; k < N; k++) {
					for(int l = j; l < N; l++) {
						int subRectSum = sumMatrix[k][l];
						if(i > 0) subRectSum -= sumMatrix[i - 1][l];
						if(j > 0) subRectSum -= sumMatrix[k][j - 1];						
						if(i > 0 && j > 0) subRectSum += sumMatrix[i - 1][j - 1];
						
						maxSubRectSum = Math.max(maxSubRectSum, subRectSum);
					}
				}
				
			}
		} // i-loop
		
		System.out.println("" + maxSubRectSum);
	}
	
}
