package practice.algo.subset;

import java.util.Arrays;
import java.util.Scanner;

public class BinaryGen {
	 
	class IntReference {
		private int intVar;
		public IntReference(int value) {
			intVar = value;
		}
		
		public int increaseByOne() {
			return intVar++;
		}
		
		public int decreaseByOne() {
			return intVar--;
		}
		
		public int getValue() {
			return intVar;
		}
	}
	
	private Scanner sc = null;

	public static void main(String[] args) {
		BinaryGen binGen = new BinaryGen();
		binGen.run();		
	}
	
	public void run() {
		sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		String[] binaryNums = generateBinaryNumbers(n);
		System.out.println(Arrays.toString(binaryNums));
	}
	
	private void backtrack(int counter, int digitLimit, int[] visited, String[] result, IntReference resultCounter) {
		if(counter == digitLimit) {
			StringBuilder stringBuilder = new StringBuilder();
			for(int num : visited) {
				 stringBuilder.append(num);
			}			
			
			result[resultCounter.getValue()] = stringBuilder.toString();
			resultCounter.increaseByOne();
			
			return;
		}
		
		visited[counter] = 0;
		backtrack(counter + 1, digitLimit, visited, result, resultCounter);
		
		visited[counter] = 1;
		backtrack(counter + 1, digitLimit, visited, result, resultCounter);
	}
	
	public String[] generateBinaryNumbers(int digitLimit) {
		String[] result = new String[(int) Math.pow(2, digitLimit)];
		IntReference resultCounter = new IntReference(0);
		
		backtrack(0, digitLimit, new int[digitLimit], result, resultCounter);
		
		return result;
	}
}
