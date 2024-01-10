package week16;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 일_4375 {	static String title;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		String str;
		StringBuilder answer = new StringBuilder();

		while ((str = br.readLine()) != null) {
			int num = Integer.parseInt(str);
			int base = 1;
			int cnt = 1;

			while ((base = base % num) != 0) {
				cnt++;
				base = base * 10 + 1;
			}

			answer.append(cnt).append("\n");
		}

		System.out.println(answer);
	}
}
