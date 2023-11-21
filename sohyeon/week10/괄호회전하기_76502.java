package week10;

import java.util.Stack;

public class 괄호회전하기_76502 {
	public int solution(String s) {
		int answer = 0;

		for (int i = 0; i < s.length(); i++) {
			String str = i == 0 ? s : s.substring(i) + s.substring(0, i);
			if (check(str)) {
				answer++;
			}
		}

		return answer;
	}

	private boolean check(String str) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (isOpen(c)) {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				char pre = stack.pop(); // open
				if (!isCouple(pre, c)) {
					return false;
				}
			}
		}

		return stack.isEmpty();
	}

	private boolean isCouple(char open, char close) {
		return (open == '(' && close == ')')
			|| (open == '{' && close == '}')
			|| (open == '[' && close == ']');
	}

	private boolean isOpen(char c) {
		return c == '(' || c == '{' || c == '[';
	}
}
