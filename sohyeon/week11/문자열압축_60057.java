package week11;

public class 문자열압축_60057 {
	int answer;

	public int solution(String s) {
		answer = s.length();

		for (int i = 1; i < s.length()/2 + 2; i++) {
			String res = compress(s, i);
			answer = Math.min(answer, res.length());
		}

		return answer;
	}

	private String compress(String str, int size) {
		StringBuilder result = new StringBuilder();
		int count = 1;
		String temp = str.substring(0, size);

		for (int i = size; i <= str.length(); i += size) {
			int endIndex = Math.min(i + size, str.length());
			String compareStr = str.substring(i, endIndex);

			if (temp.equals(compareStr)) {
				count++;
			} else {
				if (count >= 2) {
					result.append(count);
				}
				result.append(temp);
				count = 1;
				temp = compareStr;
			}
		}

		result.append(temp);

		return result.toString();
	}
}
