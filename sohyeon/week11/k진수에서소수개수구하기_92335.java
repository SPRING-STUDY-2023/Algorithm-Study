package week11;

public class k진수에서소수개수구하기_92335 {
	public int solution(int n, int k) {
		String digit = transfer(n, k);
		String[] arr = digit.split("0");
		int answer = 0;

		for (String str : arr) {
			if (str.equals("")) {
				continue;
			}
			if (isPrime(Long.valueOf(str))) {
				answer++;
			}
		}

		return answer;
	}

	private String transfer(int n, int k) {
		StringBuilder sb = new StringBuilder();

		while (n > 0) {
			sb.append(n % k);
			n /= k;
		}

		return sb.reverse().toString();
	}

	private boolean isPrime(long num) {
		if (num < 2) {
			return false;
		}

		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}

		return true;
	}
}
