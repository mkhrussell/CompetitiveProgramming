package submission.uva.recursion;

import java.util.Scanner;

public class UVa10063 {

	public static void main(String[] args) {
		UVa10063 sol = new UVa10063();
		sol.run();
	}
	
	Scanner sc = null;	
	String input;
	
	void run() {
		sc = new Scanner(System.in);
		boolean flag = false;
		while(sc.hasNext()) {
			if(flag) {
				System.out.println();
			}
			
			input = sc.next();
			permute(0, new StringBuilder());
			flag = true;
		}
	}
	
	void permute(int pos, StringBuilder result) {
		if(pos == input.length()) {
			System.out.println(result.toString());
			return;
		}
		
		for(int i = 0; i <= result.length(); i++) {
			permute(pos + 1, result.insert(i, input.charAt(pos)));
			result.delete(i, i + 1);
		}
	}
}