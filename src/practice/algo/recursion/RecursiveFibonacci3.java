package practice.algo.recursion;

public class RecursiveFibonacci3 {

	public static void main(String[] args) {
		RecursiveFibonacci3 problem = new RecursiveFibonacci3();
		problem.solve();
	}	
	
	public void solve() {		
		int N = 9;
		int nThFiboNum = fibo(N);
		System.out.println("" + nThFiboNum);
	}
	
	private int[] fiboCache = new int[100];
	
	private int fibo(int n) {
		if(n < 0) {
			return 0;
		}
		
		if(fiboCache[n] > 0) {
			return fiboCache[n];
		}		
		
		if (n >= 0 && n <= 1) {
			fiboCache[n] = 1;
			return fiboCache[n];
		}
		
		int ret = fibo(n - 2) + fibo(n - 1);
		fiboCache[n] = ret;
		
		return fiboCache[n];
	}
}
