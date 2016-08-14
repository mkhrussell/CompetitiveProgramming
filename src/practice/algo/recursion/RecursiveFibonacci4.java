package practice.algo.recursion;

public class RecursiveFibonacci4 {

	public static void main(String[] args) {
		RecursiveFibonacci4 problem = new RecursiveFibonacci4();
		problem.solve();
	}	
	
	public void solve() {		
		int N = 9;
		long nThFiboNum = fibo(N);
		System.out.println(N +"th fibonacci number is: " + nThFiboNum);
		printFiboSeries(100);
	}
	
	private long[] fiboCache = new long[100 + 1];
	
	private long fibo(int n) {
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
		
		long ret = fibo(n - 2) + fibo(n - 1);
		fiboCache[n] = ret;
		
		return fiboCache[n];
	}
	
	private void printFiboSeries(int n) {
		if(fiboCache[n] <= 0) {
			fibo(n);
		}
		
		for(int i = 0; i < fiboCache.length; i++) {
			System.out.println(i + " : " + fiboCache[i]);
		}
	}
}
