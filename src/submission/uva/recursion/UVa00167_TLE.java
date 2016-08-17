package submission.uva.recursion;

//import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UVa00167_TLE {

	public static void main(String[] args) {
		UVa00167_TLE problem = new UVa00167_TLE();
		problem.solve();
	}
	
	class Point {
		int x, y;
	}
	
	class Move {
		Point[] movePoints;
	}
	
	ArrayList<Move> moves = new ArrayList<>();
	void calculatePositions() {
		int[][] dummyBoard = new int[8][8];
		for(int i = 0; i < 8; i++)
			Arrays.fill(dummyBoard[i], 1);
		
		calcPosBacktrack(0, 0, 0, dummyBoard, new Point[8]);
	}
	
	private void calcPosBacktrack(int n, int x, int y, int[][] inBoard, Point[] resPoints) {
		
		if(n == 8) {
			Move move = new Move();
			move.movePoints = resPoints.clone();
			moves.add(move);
			
			return;
		}
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(inBoard[i][j] > 0) {
					int[][] newBoard = copyArray(inBoard);
					Point p = new Point();
					p.x = i;
					p.y = j;
					
					Point[] newResPoints = resPoints.clone();					
					newResPoints[n] = p;					
					placeQueen(i, j, newBoard);
					calcPosBacktrack(n + 1, i, j, newBoard, newResPoints);
				}
			}
		}
	}
	
	private Scanner sc = null;
	private int T;
	private int[][] board = null;

	private void solve() {
//		try {
//			System.setIn(new FileInputStream("UVa00167_in.txt"));
//		}catch(Exception e) {}
		
		calculatePositions(); // Calculate all moves in the given board beforehand
				
		sc = new Scanner(System.in);
		T = sc.nextInt();
		while(T-- > 0) {
			readCase();
			findSolution();
		}		
	}
	
	private void findSolution() {
		int result = 0;
		for(Move mv : moves) {
			int sum = 0;
			for(Point p : mv.movePoints) {
				sum += board[p.x][p.y];
			}
			if(sum > result)
				result = sum;
		}
		
		String resString = String.format("%05d", result);		
		char[] resChars = resString.toCharArray();
		for(int i = 0; i < resChars.length; i++) {
			if(resChars[i] == '0'){
				resChars[i] = ' ';
			}else {
				break;
			}
		}
		resString = new String(resChars);
		System.out.println(resString);
	}
	
	private void readCase() {
		board = new int[8][8];			
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = sc.nextInt();
			}
		}
	}
	
	private int[][] copyArray(int[][] inArray) {
		int[][] newArray = new int[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				newArray[i][j] = inArray[i][j];			
			}
		}
		
		return newArray;
	}
	
	private void placeQueen(int x, int y, int[][] places) {
		for(int i = 0; i < 8; i++) {
			places[i][y] = 0;
			places[x][i] = 0;
			
			if(x + i < 8 && y + i < 8)
				places[x + i][y + i] = 0;
			
			if(x + i < 8 && y - i >=0)
				places[x + i][y - i] = 0;
			
			if(x - i >= 0 && y - i >=0)
				places[x - i][y - i] = 0;
			
			if(x - i >= 0 && y + i < 8)
				places[x - i][y + i] = 0;
		
		}
	}	
}
