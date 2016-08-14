package practice.algo.recursion;

public class RecursiveFibonacci2 {

	public static void main(String[] args) {
		RecursiveFibonacci2 problem = new RecursiveFibonacci2();
		problem.solve();
	}	
	
	public void solve() {		
		int N = 9;
		int nThFiboNum = fibo(N);
		System.out.println("" + nThFiboNum);
	}
	
	private int[] fiboCache = new int[30];	
	private int fibo(int n) {
		if(n < 0) {
			return 0;
		}
		
		if(fiboCache[n] > 0) {
			return fiboCache[n];
		}
		
		
		if (n == 0) {
			fiboCache[n] = 1;
			return fiboCache[n];
		}
		
		if(n == 1) {
			fiboCache[n] = 1;
			return fiboCache[n];
		}
		
		int ret = fibo(n - 2) + fibo(n - 1);
		fiboCache[n] = ret;
		
		return ret;
	}
}
