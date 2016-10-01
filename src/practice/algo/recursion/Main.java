package practice.algo.recursion;

//import java.io.FileInputStream;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main problem = new Main();
		problem.run();		
	}
	
	Scanner sc = null;
	
	String input;
	
	void run() {
//		try {
//			System.setIn(new FileInputStream("UVa10063_Knuths_Permutation_in.txt"));
//		}catch(Exception e) {}
		
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
