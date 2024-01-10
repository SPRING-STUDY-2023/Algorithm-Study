package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 행운의문자열_1342 {
	static int count = 0;
	static int length;
	static int[] alphabet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		alphabet = new int[27];
		String str = br.readLine();
		length = str.length();

		for (int i = 0; i < str.length(); i++) {
			alphabet[str.charAt(i)-'a']++;
		}

		dfs(0, ' ');

		System.out.println(count);
	}

	private static void dfs(int index, char pre) {
		if (index == length){
			count++;
			return;
		}

		for (int i = 0; i < 27; i++) {
			if (alphabet[i] == 0) continue;

			if (pre != (char)(i + 'a')) {
				alphabet[i]--;
				dfs(index + 1, (char)(i + 'a'));
				alphabet[i]++;
			}

		}
	}
}