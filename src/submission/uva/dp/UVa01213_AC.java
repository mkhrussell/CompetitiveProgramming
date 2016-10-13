package submission.uva.dp;

import java.util.Scanner;

public class UVa01213_AC {

	public static void main(String[] args) {
		UVa01213_AC sol = new UVa01213_AC();
		sol.run();
	}
	
	int[] primes = new int[200];
	int numPrimes;
	
	void generatePrimes() {
		primes[0] = 2;
		numPrimes = 1;
		
		for(int i = 3; i <= 1125; i++) {
			boolean isPrime = true;
			for(int j = 0; j < numPrimes; j++) {
				if(i % primes[j] == 0) {
					isPrime = false;
				}
			}
			
			if(isPrime) {
				primes[numPrimes++] = i;
			}
		}
	}
	
	int[][] memo = new int [1121][15];
	
	void buildMemo() {
		memo[0][0] = 1;
		for(int primeIndex = 0; primeIndex < numPrimes; primeIndex++) {
			for(int num = 1120; num >= primes[primeIndex]; num--) {
				for(int k = 14; k >= 1; k--) {
					memo[num][k] += memo[num - primes[primeIndex]][k - 1];
				}
			}
		}
	}

	Scanner in;
	int N, K;
	
	void run() {
		generatePrimes(); // Generate Primes
		buildMemo(); // Generate Answers
		
		in = new Scanner(System.in);
		while(true) {
			N = in.nextInt();
			K = in.nextInt();
			if(N == 0 && K == 0)
				break;
			
			System.out.printf("%d%n", memo[N][K]);
		}
	}	

}
