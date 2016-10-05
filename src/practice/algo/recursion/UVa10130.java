package practice.algo.recursion;

import java.util.Scanner;

public class UVa10130 {

	public static void main(String[] args) {
		UVa10130 sol = new UVa10130();
		sol.run();
	}
	
	Scanner sc;
	int T;
	
	void run() {
		sc = new Scanner(System.in);
		T = sc.nextInt();
		
		while(T-- > 0) {
			takeInput();
			findSolution();
		}
	}
	
	class Item {
		int price, weight;
		
		public Item(int p, int w) {
			this.price = p;
			this.weight = w;
		}
	}
	
	int N, G;
	Item[] items;
	int[] peopleWeightLimit;
	
	void takeInput() {
		N = sc.nextInt();
		items = new Item[N];
		
		for(int i = 0; i < N; i++) {
			int p = sc.nextInt();
			int w = sc.nextInt();
			items[i] = new Item(p, w);
		}
		
		G = sc.nextInt();
		peopleWeightLimit = new int[G];
		
		for(int i = 0; i < G; i++) {
			peopleWeightLimit[i] = sc.nextInt();
		}
	}
	
	int result;
	int[][] bestValueByWeightLimit;
	
	void findSolution() {		
		bestValueByWeightLimit = new int[N + 50][35];
		initBestValues();
		
		result = 0;
		for(int i = 0; i < G; i++) {
			result += getBestValueByWeightLimit(0, peopleWeightLimit[i]);			
		}
		
		System.out.println("" + result);
	}
	
	int getBestValueByWeightLimit(int itemIndex, int weightLimit) {
		int bestValue = 0;
		
		if(itemIndex >= N) {
			return bestValue;
		}
		
		bestValue = bestValueByWeightLimit[itemIndex][weightLimit];
		if(bestValue != -1) {
			return bestValue;
		}
		
		bestValue = getBestValueByWeightLimit(itemIndex + 1, weightLimit);
		if(weightLimit >= items[itemIndex].weight) {
			int withItemBestValue = items[itemIndex].price + getBestValueByWeightLimit(itemIndex + 1, weightLimit - items[itemIndex].weight);
			bestValue = max(bestValue, withItemBestValue);
		}
		
		bestValueByWeightLimit[itemIndex][weightLimit] = bestValue;
		
		return bestValue;		
	}
	
	int max(int a, int b) {
        return (a >= b) ? a : b;
    }
	
	void initBestValues() {
		for(int i = 0; i < bestValueByWeightLimit.length; i++) {
			for(int j = 0; j < bestValueByWeightLimit[i].length; j++) {
				bestValueByWeightLimit[i][j] = -1;
			}
		}
	}
	
}
