package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이_2563 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(br.readLine()); // 색종이 수 (10 x 10)
		int[][] map = new int[1001][1001];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int j = x; j < x + 10; j++) {
				for (int k = y; k < y + 10; k++) {
					map[j][k] = 1;
				}
			}
		}

		int answer = 0;

		for (int i = 0; i <= 1000; i++) {
			for (int j = 0; j <= 1000; j++) {
				if (map[i][j] == 1) {
					answer++;
				}
			}
		}

		System.out.println(answer);
	}
}
