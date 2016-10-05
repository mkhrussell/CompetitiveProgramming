package practice.algo.recursion;

import java.util.Scanner;

public class Knuth_permutation {

	public static void main(String[] args) {
		Knuth_permutation sol = new Knuth_permutation();
		sol.run();
	}
	
	Scanner sc;
	String input;
	
	void run() {
		sc = new Scanner(System.in);
		boolean flag = false;
		while(sc.hasNext()) {
			if(flag) {
				System.out.println();
			}
			
			input = sc.next();
			permute(0, new ResultBuilder());
			flag = true;
		}
	}
	
	void permute(int pos, ResultBuilder result) {
		if(pos == input.length()) {
			System.out.println(result.toString());
			return;
		}
		
		for(int i = 0; i <= result.length(); i++) {
			permute(pos + 1, result.insert(i, input.charAt(pos)));
			result.delete(i, i + 1);
		}
	}
	
	class ResultBuilder {
		int count;
		int capacity = 16;
		char[] value;
		
		public ResultBuilder() {
			value = new char[capacity];
		}
		
		ResultBuilder insert(int offset, char c) {
			
			int newLength = count + capacity;			
			char[] copy = new char[newLength];
	        System.arraycopy(value, 0, copy, 0, min(value.length, newLength));
	        value = copy;
			
			System.arraycopy(value, offset, value, offset + 1, count - offset);
	        value[offset] = c;
	        count += 1;
	        return this;
		}
		
		ResultBuilder delete(int start, int end) {
	        if (end > count)
	            end = count;
	        if (start > end)
	            return this;
	        
	        int len = end - start;
	        if (len > 0) {
	            System.arraycopy(value, start+len, value, start, count-end);
	            count -= len;
	        }
	        return this;
	    }
		
		public int length() {
	        return count;
	    }
		
		@Override
		public String toString() {
			return new String(value);
		}
		
		int min(int a, int b) {
	        return (a <= b) ? a : b;
	    }
	}
	
}
