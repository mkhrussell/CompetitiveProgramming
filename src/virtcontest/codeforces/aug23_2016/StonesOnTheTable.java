package virtcontest.codeforces.aug23_2016;

import java.util.Scanner;

public class StonesOnTheTable {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		sc.nextInt();
		sc.nextLine();// Ignore
		
		String line = sc.nextLine().trim();
		char[] stones = line.toCharArray();
		sc.close();
		
		int count = 0;
		for(int i = 0; i < stones.length - 1; i++) {
			for(int j = i + 1; j < stones.length; j++) {
				if(stones[j] == 'X')
					continue;
				
				if(stones[i] == stones[j]) {
					count++;
					stones[j] = 'X';
				}else {
					break;
				}
			}
		}
		
		System.out.println("" + count);
	}	
}