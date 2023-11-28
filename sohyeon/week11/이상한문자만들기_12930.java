package week11;

public class 이상한문자만들기_12930 {
	public String solution(String s) {
		StringBuilder sb = new StringBuilder();
		String[] arr = s.split(" ");

		for (int i = 0; i < arr.length; i++) {
			sb.append(change(arr[i]));
			if (i != arr.length - 1) {
				sb.append(" ");
			}
		}

		if (sb.length() < s.length()) {
			sb.append(s.substring(sb.length()));
		}

		return sb.toString();
	}

	private String change(String str) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {
			String now = String.valueOf(str.charAt(i));
			sb.append((i % 2 == 0) ? now.toUpperCase() : now.toLowerCase());
		}

		return sb.toString();
	}
}
