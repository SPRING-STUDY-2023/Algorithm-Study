package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 간판_5534 {	static String title;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(br.readLine());
		title = br.readLine();
		int res = 0;

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			// System.out.println(str);
			if (check(str)) {
				res++;
			}
		}

		System.out.println(res);
	}

	private static boolean check(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == title.charAt(0)) {
				for (int j = i ; j < str.length(); j++) {
					if (str.charAt(j) == title.charAt(title.length() - 1)) {
						int gap = (j - i) / (title.length() - 1);
						int cnt = 0;
						// System.out.println(i + " " + j + " " + gap);
						boolean flag = true;
						while (cnt < title.length()) {
							if (str.charAt(i + gap * cnt) == title.charAt(cnt)) {
								cnt++;
							} else {
								flag = false;
								break;
							}
						}
						if (flag) return true;
					}
				}
			}
		}
		return false;
	}
}
