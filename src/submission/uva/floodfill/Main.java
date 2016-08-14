package submission.uva.floodfill;

//import java.io.FileInputStream;
//import java.util.Arrays;


import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main problem = new Main();
		problem.solve();
	}
	
	private Scanner sc = null;
	public void solve() {
//		try {
//			System.setIn(new FileInputStream("UVa11094_in.txt"));
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
		sc = new Scanner(System.in);
		// SOLVE
		while(sc.hasNextLine()) {
			readCase();
			//print2dArray(map);
		
			findSolution();
			printSolution();
		}
		// END SOLVE
		
	}
	
//	void print2dArray(int[][] arr) {
//		for(int i = 0; i < arr.length; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
//		System.out.println("//");
//	}
//	
//	void print2dArray(char[][] arr) {
//		for(int i = 0; i < arr.length; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
//		System.out.println("//");
//	}
	
	private void printSolution() {
		System.out.println(maxRegionSize);		
	}
	private void findSolution() {
		int ret = 0;
		ret = findAndCapture(X, Y); // Fill the king's land first
		
		maxRegionSize = 0; // Reset previous case's result		
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				if(map[i][j] == 1) {
					ret = findAndCapture(i, j);
				}
			}
		}
	}
	
	private int M, N;
	private char[][] charMap;
	private int X, Y;
	private char landChar;
	
	private void readCase() {
		M = sc.nextInt();
		N = sc.nextInt();
		String line = sc.nextLine(); // Ignore
		charMap = new char[M][N];
		map = new int[M][N];
		for(int i = 0; i < charMap.length; i++) {
			line = sc.nextLine();
			charMap[i] = line.trim().toCharArray();
		}
		
		X = sc.nextInt();
		Y = sc.nextInt();
		landChar = charMap[X][Y];
		makeIntMap(); // Create a copy of map using integer value
		sc.nextLine(); // Ignore
		
		if(sc.hasNextLine())
			sc.nextLine(); // Extra new line after each case
	}
	
	private int[][] map;
	
	private void makeIntMap() {
		for(int i = 0; i < charMap.length; i++) {
			for(int j = 0; j < charMap[i].length; j++) {
				if(charMap[i][j] == landChar)
					map[i][j] = 1;
				else
					map[i][j] = 0;
			}
		}		
	}
	
	int maxRegionSize = 0;
	
	private int findAndCapture(int x, int y) {
		int count = 0;		
		if(x >= 0 && x < map.length && y >= 0 && y < map[0].length) {		
			if(map[x][y] == 1) {
				map[x][y] = 0;
				count = 1;
				
				count += findAndCapture(x - 1, y); // Up								
				count += findAndCapture(x + 1, y); // down
								
				if(y - 1 >= 0)
					count += findAndCapture(x, y - 1); // left
				else
					count += findAndCapture(x, map[0].length - 1); // left
				
				if(y + 1 < map[0].length)
					count += findAndCapture(x, y + 1); // right
				else
					count += findAndCapture(x, 0); // right
			}
			
			if(count > maxRegionSize) {
				maxRegionSize = count;
			}
		}
		
		return count;
	}
}
