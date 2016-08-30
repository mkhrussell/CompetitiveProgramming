package virtcontest.codeforces.aug30_2016;

/*
 * http://codeforces.com/contest/581/problem/B
 */

import java.util.Arrays;
import java.util.Scanner;

public class LuxuriousHouses {

	public static void main(String[] args) {
		LuxuriousHouses prob = new LuxuriousHouses();
		prob.solve();
	}

	private Scanner sc = null;
	
	private void solve() {
		sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		long[] houseFloors = new long[N];
		
		for(int i = 0; i < houseFloors.length; i++) {
			houseFloors[i] = sc.nextLong();
		}
		
		long[] floorsToAdd = new long[houseFloors.length];
		Arrays.fill(floorsToAdd, 0L);
		
		long maxFloor = houseFloors[houseFloors.length - 1];		
		for(int i = houseFloors.length - 2; i >= 0; i--) {
			if(houseFloors[i] <= maxFloor) {				
				floorsToAdd[i] = maxFloor - houseFloors[i] + 1;
			}else {			
				maxFloor = houseFloors[i];
				//floorsToAdd[i] = 0L;
			}
		}
		
		for(int j = 0; j < floorsToAdd.length - 1; j++) {
			System.out.print(floorsToAdd[j] + " ");
		}
		System.out.println("" + floorsToAdd[houseFloors.length - 1]);
		
		sc.close();
	}
}
