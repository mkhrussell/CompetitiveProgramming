package practice.algo.recursion;

//import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class UVa10261_DP2 {
	
	public static void main(String[] args) {
		UVa10261_DP2 sol = new UVa10261_DP2();
		sol.run();		
	}
	
	Scanner sc;
	
	void run() {
//		try {
//			System.setIn(new FileInputStream("UVa10261_Ferry_Loading_in.txt"));
//			//System.setOut(new PrintStream("UVa10261_Ferry_Loading_out.txt"));
//		}catch(Exception e) {}
		
		sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int nCase = 1; nCase <= T; nCase++) {
			if (nCase > 1) {
				System.out.println();
			}
	        
			takeInput(); // Input	        
			findSolution();
			printSolution();
		}		
	}
	
	int ferryLength;
	int totalNumCars;
	
	int[] carLengths = new int[2005];
	int[] carLengthSums = new int[2005];
	
	private void takeInput() {
		ferryLength = sc.nextInt();
		ferryLength *= 100;
		
		carLengths[0] = 0;
	    carLengthSums[0] = 0;		
		totalNumCars = 1;
		while((carLengths[totalNumCars] = sc.nextInt()) != 0 ) {
			carLengthSums[totalNumCars] = carLengthSums[totalNumCars - 1] + carLengths[totalNumCars] ;
			totalNumCars++ ;
		}
	}

	int maxLoadableNumCars;
	int maxLength;	
	int[][] dp = new int[2005][10005];
	
	private void findSolution() {
		// Reset
		maxLoadableNumCars = 0;
		maxLength = 0;
		
		dp[0][0] = 0;
		for(int numCar = 0; numCar <= totalNumCars; numCar++) {
			for(int carLength = 0; carLength <= ferryLength; carLength++) {
				dp[numCar][carLength] = 0;
			}
		}

		// Prepare DP array
		dp[0][0] = 1;
		for (int numCar = 1; numCar <= totalNumCars - 1; numCar++){
			for ( int carLength = 0; carLength <= ferryLength; carLength ++ ){
				if ((carLength >= carLengths[numCar] && dp[numCar - 1][carLength - carLengths[numCar]] == 1) ||
					(carLengthSums[numCar] - carLength <= ferryLength && dp[numCar - 1][carLength] == 1) ) {
					
					maxLoadableNumCars = numCar;
					maxLength = carLength;
					dp[numCar][carLength] = 1;
				}
			}
		}
		
		// Recursive Call
		topdown(maxLoadableNumCars, maxLength);		
	}
	
	Queue <Integer> resultQueue = new LinkedList<>();
		
	void topdown(int numCar , int carLength) {
		if (numCar - 1 < 0)
			return;
	        
		if(dp[numCar - 1][carLength] == 1 && carLengthSums[numCar] - carLength <= ferryLength) {
			topdown(numCar - 1, carLength);
			resultQueue.add(1);
		} else if(carLength >= carLengths[numCar] && dp[numCar - 1][carLength - carLengths[numCar]] == 1) {
			topdown(numCar - 1, carLength - carLengths[numCar]);
			resultQueue.add(0);
		}
	}

	private void printSolution() {
		System.out.println("" + maxLoadableNumCars);
		
		int first = 0;
		if (!resultQueue.isEmpty()) {
			first = resultQueue.peek();
		}
		
		while (!resultQueue.isEmpty()) {
			int element = resultQueue.peek();
			
			if ( element == first )
				System.out.println("port");
			else
				System.out.println("starboard");
			
			resultQueue.poll();
		}
	}	
}
