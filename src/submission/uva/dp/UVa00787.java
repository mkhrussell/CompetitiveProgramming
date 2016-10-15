package submission.uva.dp;

import java.math.BigInteger;
import java.util.Scanner;

public class UVa00787 {

	public static void main(String[] args) {
		UVa00787 sol = new UVa00787();
		sol.run();
	}
	
	Scanner sc;
	BigInteger[] sequence;
	int sequenceLength;
	
	void run() {
		sc = new Scanner(System.in);
		sequence = new BigInteger[105];
		while(sc.hasNextBigInteger()) {
			takeInput();
			findSolutionNSquare();
		}
	}
	
	BigInteger endOfInput = new BigInteger("-999999");

	void takeInput() {
		sequenceLength = 0;
		while(true) {
			BigInteger curNum = sc.nextBigInteger(); 
			if(curNum.equals(endOfInput))
				break;			
			sequence[sequenceLength++] = curNum;
		}
	}
	
	void findSolutionNSquare() {
		BigInteger maxProduct = new BigInteger("-99999999999999999999999");
		for(int start = 0; start < sequenceLength; start++) {
			BigInteger product = BigInteger.ONE;
			for(int i = start; i < sequenceLength; i++) {				
				product = product.multiply(sequence[i]);
				maxProduct = maxProduct.max(product);
			}
		}		
		System.out.println(maxProduct.toString());
	}
	
	/*// Below is the O(N-Cube) solution, which is easier to understand. 
	void findSolutionNCube() {
		BigInteger maxProduct = new BigInteger("-99999999999999999999999");
		for(int start = 0; start < sequenceLength; start++) {
			for(int end = 0; end < sequenceLength; end++) {
				BigInteger product = sequence[start];
				for(int i = start + 1; i <= end; i++) {
					product = product.multiply(sequence[i]);
				}
				maxProduct = maxProduct.max(product);
			}
		}		
		System.out.println(maxProduct.toString());
	}
	*/

}
