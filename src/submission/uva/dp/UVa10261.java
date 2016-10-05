package submission.uva.dp;

import java.util.Arrays;
import java.util.Scanner;

public class UVa10261 {

	public static void main(String[] args) {
		UVa10261 problem = new UVa10261();
		problem.run();		
	}
	
	Scanner sc;
	int T, nCase;
		
	void run() {
		sc = new Scanner(System.in);
		T = sc.nextInt();
		
		boolean flag = false;
		
		for(nCase = 1; nCase <= T; nCase++) {
			if(flag)
				System.out.println();
			takeInput();				
			findSolution();
			flag = true;
		}
	}
	
	int ferryLength;
	int[] carList = new int[2005];
	int numCars;
		
	void takeInput() {
		ferryLength = sc.nextInt();
		ferryLength *= 100;
		
		numCars = 0;
		int car;
		while((car = sc.nextInt()) > 0) {
			carList[numCars++] = car;
		}
	}
	
	void findSolution() {
		cache = new int[10005][10005];
		for(int i = 0; i < cache.length; i++)
			Arrays.fill(cache[i], -1);
		
		maxCar = 0;
		loadCar(0, ferryLength, ferryLength, new boolean[numCars]);
		
		System.out.println("" + maxCar);
		for(int i = 0; i < maxCar; i++) {
			if(finalTaken[i])
				System.out.println("port");
			else
				System.out.println("starboard");
		}	
	}
	
	int maxCar;
	boolean[] finalTaken;	
	int[][] cache;
	
	void loadCar(int n, int port, int starboard, boolean[] taken) {
		if(cache[port][starboard] == n) {
			return;
		}		
		cache[port][starboard] = n;
		
		if(n >= numCars) {
			maxCar = numCars;
			finalTaken = taken.clone();
			return;
		}
		
		int carLength = carList[n];
		if(carLength > Math.max(port, starboard)) {
			if(n > maxCar) {
				maxCar = n;
				finalTaken = taken.clone();
			}
			
			return;
		}
		
		if(carLength <= port) {
			taken[n] = true;
			loadCar(n + 1, port - carLength, starboard, taken);
			taken[n] = false;
		}
		
		if(carLength <= starboard) {
			taken[n] = false;
			loadCar(n + 1, port, starboard - carLength, taken);
			taken[n] = true;
		}
	}
}
