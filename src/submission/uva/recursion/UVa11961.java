package submission.uva.recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class UVa11961 {

	public static void main(String[] args) {
		UVa11961 sol = new UVa11961();
		sol.run();
	}
	
	Scanner sc;
	int T;
	
	int N, K;
	char[] dnaSequence;	
	HashSet<String> resultSequences = new HashSet<>();
		
	void run() {
		
		sc = new Scanner(System.in);
		T = sc.nextInt();
		
		while(T-- > 0) {
			N = sc.nextInt();
			K = sc.nextInt();
			dnaSequence = sc.next().toCharArray();
			
			// Reset
			resultSequences.clear();
			
			// Generate Sequences
			mutate(0, dnaSequence.clone(), new boolean[dnaSequence.length]);
			
			// Post Process
			List<String> sortedSequences = new ArrayList<>(resultSequences);
			Collections.sort(sortedSequences);
			
			// Print Result
			System.out.println("" + resultSequences.size());			
			for(String seq : sortedSequences) {
				System.out.println(seq);
			}
		}
	}
	
	String acgt = "ACGT"; // Range Set
			
	void mutate(int k, char[] sequence, boolean[] taken) {
		if(k == K) {			
			resultSequences.add(new String(sequence));
			return;
		}
		
		for(int j = 0; j < N; j++) {
			if(!taken[j]) {
				taken[j] = true;
				
				for(int m = 0; m < acgt.length(); m++) {
					char prevChar = sequence[j];
					sequence[j] = acgt.charAt(m);
					mutate(k + 1, sequence, taken);
					sequence[j] = prevChar;					
				}
				
				taken[j] = false;
			}
		}
	}

}