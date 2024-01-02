package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 과제는끝나지않아_17952 {
	static class Homework {
		int score;
		int minute;

		Homework(int score, int minute) {
			this.score = score;
			this.minute = minute;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(br.readLine());
		Stack<Homework> stack = new Stack<>();
		int res = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			if (st.nextToken().charAt(0) == '1') {
				int score = Integer.parseInt(st.nextToken());
				int minute = Integer.parseInt(st.nextToken());
				if (minute == 1) {
					res += score;
				} else {
					stack.push(new Homework(score, minute - 1));
				}
			} else if (!stack.isEmpty()) {
				Homework temp = stack.pop();
				temp.minute--;
				if (temp.minute == 0) {
					res += temp.score;
				} else {
					stack.push(temp);
				}
			}
		}

		System.out.println(res);
	}
}
