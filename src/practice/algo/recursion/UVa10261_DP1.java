package practice.algo.recursion;

/*
 * Problem : https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=24&page=show_problem&problem=1202
 * http://blog.csdn.net/metaphysis/article/details/6877397
 * http://jeno5980515.github.io/2015/03/16/%E8%A7%A3%E9%A1%8C%E5%8D%80/UVa/10261%20-%20Ferry%20Loading/
 */

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class UVa10261_DP1 {

	public static void main(String[] args) {
		UVa10261_DP1 problem = new UVa10261_DP1();
		problem.run();		
	}
	
	Scanner sc;
	int T, nCase;
		
	void run() {
		try {
			System.setIn(new FileInputStream("UVa10261_Ferry_Loading_in.txt"));
			//System.setOut(new PrintStream("UVa10261_Ferry_Loading_out.txt"));
		}catch(Exception e) {}
		
		sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(nCase = 1; nCase <= T; nCase++) {
			if(nCase > 1) {
				System.out.println();
			}
			
			takeInput();				
			findSolution();
		}
	}
	
	int ferryLength;
	ArrayList<Integer> carList = new ArrayList<>();
		
	void takeInput() {
		ferryLength = sc.nextInt();
		ferryLength *= 100;
		carList.clear();
		int car;
		while((car = sc.nextInt()) > 0) {
			carList.add(car);
		}
	}
	
	void findSolution() {		
		maxCar = 0;		
		loadCar(0, ferryLength, ferryLength, new boolean[carList.size()]); 
		
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
	
	void loadCar(int n, int port, int starboard, boolean[] taken) {
		//System.out.println("Case #" + nCase + " loadCar: n=" + n + " port=" + port + " starboard=" + starboard);
		
		if(n >= carList.size()) {
			
			n = carList.size();			
			if(n > maxCar) {
				maxCar = n;
				// Ans
				finalTaken = taken.clone();
			}
			
			return;
		}
		
		int carLength = carList.get(n);
		if(carLength > Math.max(port, starboard)) {
			if(n > maxCar) {
				maxCar = n;
				// Ans
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
