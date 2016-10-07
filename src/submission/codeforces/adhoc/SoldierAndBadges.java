package submission.codeforces.adhoc;

import java.util.Scanner;

public class SoldierAndBadges {
	public static void main(String[] args) {
		SoldierAndBadges sol = new SoldierAndBadges();
		sol.run();
	}

	Scanner in;
	int T = 1;
	
	void run() {
		in = new Scanner(System.in);
		//T = in.nextInt();
		while(T-- > 0) {
			takeInput();
			findSolution();
		}		
	}

	int N;
	int[] nums;
	
	void takeInput() {
		N = in.nextInt();
		nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = in.nextInt();
		}
	}

	void findSolution() {
		sort(nums);
		int increasedScore = 0;
		for(int i = 1; i < nums.length; i++) {
			if(nums[i - 1] == nums[i]) {
				nums[i]++;
				increasedScore++;
			}else if(nums[i - 1] > nums[i]) {
				int increament = nums[i - 1] - nums[i] + 1;
				nums[i] += increament;
				increasedScore += increament;
			}
		}
		
		System.out.println("" + increasedScore);
	}
	
	void sort(int[] array) {
		quickSort(array, 0, array.length - 1);		
	}

	void quickSort(int[] array, int low, int high) {
		if(low < high) {
			int pivotIndex = partition(array, low, high);
			quickSort(array, low, pivotIndex - 1);
			quickSort(array, pivotIndex + 1, high);
		}
	}

	int partition(int[] array, int low, int high) {
		int pivotValue = array[high];
		int index = low - 1;
		for(int cmpIndex = low; cmpIndex <= high - 1; cmpIndex++) {
			if(array[cmpIndex] <= pivotValue) {
				index++;			
				exchangeValue(array, index, cmpIndex);
			}
		}
		exchangeValue(array, index + 1, high);
		
		return index + 1;
	}

	void exchangeValue(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
}