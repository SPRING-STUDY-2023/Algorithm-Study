package week15;

public class 마법의엘레베이터_148653 {
	public int solution(int storey) {
		String str = String.valueOf(storey);
		int[] arr = new int[str.length()];

		for (int i = 0; i < str.length(); i++) {
			arr[i] = str.charAt(i) - '0';
		}

		int answer = 0;

		for (int i = arr.length - 1; i >= 0; i--) {
			if (arr[i] > 5) {
				answer += (10 - arr[i]);

				if (i == 0) answer++;
				else arr[i - 1]++;
			} else if (arr[i] == 5 && i > 0 && arr[i - 1] >= 5) {
				arr[i - 1]++;
				answer += 5;
			} else {
				answer += arr[i];
			}
		}

		return answer;
	}
}