package virtcontest.codeforces.aug23_2016;

import java.util.Scanner;

public class QueueAtTheSchool {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		sc.nextInt(); // Ignore
		int time = sc.nextInt();
		sc.nextLine(); // Ignore
				
		String line = sc.nextLine().trim();
		
		sc.close();
		
		char[] que = line.toCharArray();
		
		for(int t = 0; t < time; t++) {
			
			for(int i = que.length - 1; i > 0; i--) {				
				if(que[i] == 'G' && que[i - 1] == 'B') {
					que[i] = 'B';
					que[i - 1] = 'G';
					i = i - 1;
				}
			}
			
		}
		
		System.out.println(new String(que));
	}

}
