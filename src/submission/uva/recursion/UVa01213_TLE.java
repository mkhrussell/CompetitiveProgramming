package submission.uva.recursion;

import java.util.Scanner;

public class UVa01213_TLE {

	public static void main(String[] args) {
		UVa01213_TLE sol = new UVa01213_TLE();
		sol.run();
	}
	
	int[] primes = new int[200];
	int numPrimes = 0;
	
	void generatePrimes() {
		primes[0] = 2;
		for(int i = 3; i <= 1125; i++) {
			boolean isPrime = true;
			for(int j = 0; j < numPrimes; j++) {
				if(i % primes[j] == 0) {
					isPrime = false;
				}
			}
			
			if(isPrime) {
				primes[++numPrimes] = i;
			}
		}
	}

	Scanner in;
	int N, K;
	
	void run() {
		generatePrimes(); // Generate Primes
		
		in = new Scanner(System.in);
		while(true) {
			N = in.nextInt();
			K = in.nextInt();
			if(N == 0 && K == 0)
				break;
			
			findSolution();
		}
	}
	
	int sumOfDiffPrimeCount;
	
	void findSolution() {		
		sumOfDiffPrimeCount = 0;
		countSum(0, N, K);
		System.out.println("" + sumOfDiffPrimeCount);
	}
	
	void countSum(int n, int remN, int remK) {		
		if(remN < 0 || remK < 0) {
			return;
		}
		
		if(n == numPrimes) {
			if(remN == 0 && remK == 0) {
				sumOfDiffPrimeCount++;
			}
			return;
		}
		
		if(remN == 0 && remK == 0) {
			sumOfDiffPrimeCount++;
			return;
		}
		
		countSum(n + 1, remN - primes[n], remK - 1);
		countSum(n + 1, remN, remK);
	}	

}
