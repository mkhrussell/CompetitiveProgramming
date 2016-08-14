package practice.algo.recursion;

public class RecursiveFibonacci {

	public static void main(String[] args) {
		RecursiveFibonacci problem = new RecursiveFibonacci();
		problem.solve();		
	}	
	
	public void solve() {
		int N = 2;
		int nThFiboNum = fibo(N);
		System.out.println("" + nThFiboNum);
	}
	
	private int fibo(int n) {
		if(n < 0) {
			return 0;
		}
		
		if (n == 0) {			
			return 1;
		}
		
		if(n == 1) {
			return 1;
		}
		
		int ret = fibo(n - 2) + fibo(n - 1);
		
		return ret;
	}
}
