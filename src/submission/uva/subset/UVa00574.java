package submission.uva.subset;

import java.util.Scanner;

public class UVa00574 {

	public static void main(String[] args) {
		UVa00574 problem = new UVa00574();
		problem.solve();
	}

	private Scanner sc = null;
	private int targetSum;
	private int N;
	private Integer[] givenNumbers;
		
	private void solve() {
		sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
						
			readCase();
			if(N == 0)
				break;
			
			findSolution();
		}
	}

	private boolean inputHasZero = false;
	
	private void readCase() {
		targetSum = sc.nextInt();
		N = sc.nextInt();
		givenNumbers = new Integer[N];
		
		for(int i = 0; i < N; i++) {
			givenNumbers[i] = sc.nextInt();			
			if(givenNumbers[i] == 0) {
				inputHasZero = true;
			}
		}
	}
	
	private boolean combinationFound;
	private void findSolution() {
		System.out.println("Sums of " + targetSum + ":");
		if(targetSum == 0) {
			
			if(inputHasZero)
				System.out.println("0");
			else
				System.out.println("");
			
		}else {
			
			combinationFound = false;
			inputHasZero = false;
			
			int[] sequence = new int[13];
		    findSumRecursively(0, 0, sequence, 0);
			
			if(!combinationFound) {
				System.out.println("NONE");
			}
			
		}
	}
	
	private void findSumRecursively(int index, int currentSum, int[] sequence, int top) {
        if(currentSum > targetSum)
            return;
        
        if(currentSum == targetSum) {
        	combinationFound = true;
        	
            for (int i = 0; i < top-1; i++) {
                System.out.print(sequence[i] + "+");
            }            
            System.out.print("" + sequence[top-1]);
            System.out.println();
            
            return;
        }
        
        if(index >= N)
            return;
        
        boolean visited[] = new boolean[1001];
        for (int i = index; i < N; i++) {
            if(!visited[givenNumbers[i]]) {
                int[] newSequence = sequence.clone();
                newSequence[top] = givenNumbers[i];

                visited[givenNumbers[i]] = true;
                findSumRecursively(i + 1, currentSum + givenNumbers[i], newSequence, top + 1);
            }
        }
    }	
}
