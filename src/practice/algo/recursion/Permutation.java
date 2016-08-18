package practice.algo.recursion;

public class Permutation {

	public static void main(String[] args) {
		
		int[] resutl = new int[4];
		getPermutation(0, resutl);
	}
	
	static int[] input = new int[]{2, 1, 5, 3};
	static void getPermutation(int n, int[] result) {
		if(n == result.length) {
			for(int i = 0; i < result.length; i++) {
				System.out.print(input[result[i]] + " ");				
			}
			System.out.println();
			
			return;
		}
		
		for(int i = 0; i < result.length; i++) {
			//if(result[n] )
			result[n] = i;
			getPermutation(n + 1, result);			
		}
	}
}
