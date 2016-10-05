package virtcontest.codeforces.aug31_2016;

/*
 * http://codeforces.com/contest/581/problem/C
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DevelopingSkills {

	public static void main(String[] args) {
		DevelopingSkills prob = new DevelopingSkills();
		prob.solve();
	}
	
	private class Skill implements Cloneable {
		int value = 0;
		int rating = 0;
		
		@Override
		protected Object clone() throws CloneNotSupportedException {			
			return super.clone();
		}
	}

	private void solve() {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int k = sc.nextInt();
		
		Skill[] skills = new Skill[N];
		Comparator<Skill> cmp = new Comparator<DevelopingSkills.Skill>() {

			@Override
			public int compare(Skill left, Skill right) {
				if(left.value == right.value)
					return 0;
				else if(left.value > right.value)
					return 1;
				else
					return -1;
			}
		};
		
		memo = new int[N];		
		Arrays.fill(memo, -1);
		
		for(int i = 0; i < skills.length; i++) {
			skills[i] = new Skill();
			skills[i].value = sc.nextInt();
			
			if(skills[i].value >= 100) {
				skills[i].rating = 10;
			}else {
				skills[i].rating = skills[i].value / 10;
			}
		}
		
		Arrays.sort(skills, Collections.reverseOrder(cmp));
		
//		for(int i = 0; i < skills.length; i++) {
//			System.out.println(skills[i].value + "");
//		}
		
		maxTotalRating = 0;
		backtrack(skills, 0, k, new int[skills.length]);
		
//		// Special condition 2: Distribute bonus equally
//		int ke = k / skills.length;
//		int tmpResult = 0;
//		for(int i = 0; i < skills.length; i++) {
//			if(skills[i].value + ke <= 100) {
//				tmpResult += (skills[i].value + ke)/10;
//			}else {
//				tmpResult += skills[i].rating;
//			}
//		}
		
//		if(tmpResult > result)
//			result = tmpResult;
//		
		System.out.println("" + maxTotalRating);
		
		sc.close();
	}
	
	private int[] memo = null;
	private int maxTotalRating = 0;
	
	private void backtrack(Skill[] input, int n, int k, int[] ratings) {
		if(n == input.length) {
			int totalRating = 0;
			for(int num : ratings) {
				totalRating += num;
			}
			
			if(totalRating > maxTotalRating)
				maxTotalRating = totalRating;
			
			return;
		}
		
		int[] newRatings = ratings.clone();
		if(k <= 0) {
			// State 1
			newRatings[n] = input[n].rating;
			backtrack(input, n + 1, 0, newRatings);
			
			return;
		}
		
		int newK = k;		
		if(input[n].value >= 100) {
			// State 2
			newRatings[n] = input[n].rating;
			backtrack(input, n + 1, k, newRatings);
		}else {
			// State 3
			if(input[n].value + k < 100) {
				// State 4
				newRatings[n] = (input[n].value + k) / 10;				
				newK = k;
				int gap = 10 - (input[n].value % 10);
				if(k >= gap) {
					newK = k - gap;
					newRatings[n] = input[n].rating + 1;
				}else {
					newK = 0;
					newRatings[n] = input[n].rating;
				}
				backtrack(input, n + 1, newK, newRatings);			
			}else {
				if(input[n].value + k == 100) {
					newRatings[n] = 10;
					backtrack(input, n + 1, 0, newRatings);
				}else {
					newK = k - (100 - input[n].value);
					newRatings[n] = 10;
					backtrack(input, n + 1, newK, newRatings);
				}
			}	
		}
		
		// State 5
		ratings[n] = input[n].rating;
		backtrack(input, n + 1, k, ratings);
	}	
}
