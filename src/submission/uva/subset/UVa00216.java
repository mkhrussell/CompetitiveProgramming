package submission.uva.subset;

//import java.io.FileInputStream;
//import java.io.PrintStream;
import java.util.Scanner;

/*
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=24&page=show_problem&problem=152
 * https://uva.onlinejudge.org/external/2/216.pdf
 * https://www.udebug.com/UVa/216
 */

public class UVa00216 {
	
	private class Computer {
		int x, y;
		
		public Computer(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public double getDistance(Computer from) {
			double dx = this.x - from.x;
			double dy = this.y - from.y;
			dx = Math.pow(dx, 2.0);
			dy = Math.pow(dy, 2.0);
			
			double result = Math.sqrt(dx + dy);
			result += 16.0; // Additional 16.0 meters needed
			
			return result;
		}
		
		@Override
		public String toString() {
			return new String("(" + x +"," + y + ")");
		}
		
	}
	
	public static void main(String[] args) {
		UVa00216 problem = new UVa00216();
		problem.solve();
	}
	
	private Scanner sc = null;
	
	private int nCase = 0;	
	private int numComputers;
	
	private void solve() {
//		try {
//			System.setIn(new FileInputStream("UVa00216_in.txt"));
//			System.setOut(new PrintStream("UVa00216_out.txt"));
//		}catch(Exception e) {	
//		}
		
		sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			nCase++;
			numComputers = sc.nextInt();
			if(numComputers == 0)
				break; // End of case
			// Solve
			comps = new Computer[numComputers];
			readCase();
			
			finalDistance = Double.MAX_VALUE;
			finalPath = new int[numComputers];
			
			findSolution();
			printSolution();
			
			// End Solve
		}		
	}
	
	private Computer[] comps = null;
	private void readCase() {
		int x, y;		
		for(int i = 0; i < numComputers; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			comps[i] = new Computer(x, y);
		}		
	}
	
	private int[] finalPath;
	private double finalDistance;
	
	private void findSolution() {
		boolean[] visited = new boolean[numComputers];
		int[] path = new int[numComputers];
		visit(0, visited.clone(), path.clone());
	}

	private void printSolution() {
		System.out.println("**********************************************************");
		System.out.println("Network #" + nCase);
		for(int i = 1; i < numComputers; i++) {
			System.out.printf("Cable requirement to connect %s to %s is %.2f feet.", comps[finalPath[i - 1]], comps[finalPath[i]], comps[finalPath[i-1]].getDistance(comps[finalPath[i]]));
			System.out.println();
		}		
		System.out.printf("Number of feet of cable required is %.2f.", finalDistance);
		System.out.println();		
	}
	
	private void visit(int n, boolean[] visited, int[] path) {
		if(n == numComputers) {
			double dist = 0.0;
			for(int i = 1; i < numComputers; i++) {
				dist += comps[path[i-1]].getDistance(comps[path[i]]);				
			}
						
			if(dist < finalDistance) {
				finalDistance = dist;
				finalPath = path.clone();
			}
			
			return;
		}
		
		for(int i = 0; i < numComputers; i++) {
			if(!visited[i]) {
				boolean[] newVisited = visited.clone();
				int[] newPath = path.clone();
				newVisited[i] = true;
				newPath[i] = n;
				visit(n + 1, newVisited, newPath);
			}
		}
	}

}
