package practice.math.expression;

import java.util.Stack;

public class ExpressionEval {

	public static void main(String[] args) {
		ExpressionEval prob = new ExpressionEval();
		System.out.println("" + prob.eval());
	}
	
	int[] nums = new int[] {1, 2, 3, 4, 5};
	char[] opChars = new char[] {'+', '-', '*'};
	int[] ops = new int[] {2, 0, 1, 2};
	
	int eval() {
		Stack<Integer> values = new Stack<>();
		Stack<Character> operarors = new Stack<>();
		
		for(int value : nums) {
			values.push(value);
		}
		
		for(int i : ops) {
			operarors.push(opChars[i]);
		}
		
		while(!operarors.isEmpty()) {
			int v = values.pop();
			char op = operarors.pop();
			
			if(op == '+') v = values.pop() + v;
			else if(op == '-') v = values.pop() - v;
			else if(op == '*') v = values.pop() * v;			
			values.push(v);
		}
		
		return values.pop();
	}
}

//(((aπ(1) o1 aπ(2)) o2 aπ(3)) o3 aπ(4)) o4 aπ(5)

