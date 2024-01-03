package week15;

public class 콜라문제_132267 {
	public int solution(int a, int b, int n) {
		int answer = 0;

		while (n >= a) {
			int setCount = n / a;
			n = n - setCount * a;
			answer += (setCount * b);
			n += (setCount * b);
		}

		return answer;
	}
}