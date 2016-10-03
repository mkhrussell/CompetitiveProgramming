package practice.algo.recursion;

//import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Main problem = new Main();
		problem.run();		
	}
	
	Scanner sc;
	int T;
	
	int N, K;
	char[] dnaSequence;	
	HashSet<String> resultSequences = new HashSet<>();
		
	void run() {
//		try {
//			System.setIn(new FileInputStream("UVa11961_DNA_in.txt"));
//		}catch(Exception e) {}
		
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
