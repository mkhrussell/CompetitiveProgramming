package submission.uva.subset;

import java.util.Scanner;

public class UVa00624 {

	public static void main(String[] args) {
		UVa00624 problem = new UVa00624();
		problem.solve();		
	}
	
	private Scanner sc = null;
	private int N;
	private int numTracks;
	private int[] tracks;
	
	private void readCase() {
		N = sc.nextInt();
		numTracks = sc.nextInt();
		tracks = new int[numTracks];
		for(int i = 0; i < numTracks; i++) {
			tracks[i] = sc.nextInt();
		}
		
		if(sc.hasNextLine())
			sc.nextLine();
	}	
	
	public void solve() {
		/*
		try {
			System.setIn(new FileInputStream("UVa00624_in.txt"));
			System.setOut(new PrintStream("UVa00624_out.txt"));
		}catch(Exception e) {
			e.printStackTrace();
		} */
		
		sc = new Scanner(System.in);
		
		while(sc.hasNextInt()) {
			readCase();			
			findSolution();
			printSolution();
		}
	}
	
	private boolean[] finalTaken;
	private int finalSum;
	private void printSolution() {		
		for(int i = 0; i < finalTaken.length; i++) {
			if(finalTaken[i] == true)
				System.out.print(tracks[i] + " ");
		}		
		System.out.println("sum:" + finalSum);
	}
	
	private void findSolution() {
		finalTaken = new boolean[numTracks];
		finalSum = 0;
		takeTrack(0, new boolean[numTracks], 0);
	}
	
	private void takeTrack(int i, boolean[] taken, int sum) {
		if(i == numTracks || sum == N) {
			if(sum <= N) {
				if(sum > finalSum) {
					finalSum = sum;
					finalTaken = taken.clone();
				}
			}
			
			return;
		}
		
		taken[i] = true;
		takeTrack(i + 1, taken, sum + tracks[i]);
		
		taken[i] = false;
		takeTrack(i + 1, taken, sum);
	}
}
