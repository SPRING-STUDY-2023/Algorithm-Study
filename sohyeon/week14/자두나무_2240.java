package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 자두나무_2240 {	static String title;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken()); // 자두가 떨어지는 시간(초)
		int W = Integer.parseInt(st.nextToken()); // 자두가 움직이는 최대 횟수

		int[] plum = new int[T + 1];
		for (int i = 1; i <= T; i++) {
			plum[i] = Integer.parseInt(br.readLine());
		}

		int pos = 1; // 자두 위치
		int[][] dp = new int[T + 1][W + 1];
		int answer = 0;

		for (int t = 1; t <= T; t++) {
			int plumPos = plum[t];

			for (int w = 0; w <= W; w++) {
				if (w == 0) { // 이동하지 않음
					pos = 1;
					if (plumPos == pos) { // 자두 받아먹음
						dp[t][w] = dp[t - 1][w] + 1;
					} else {
						dp[t][w] = dp[t - 1][w];
					}
					continue;
				}

				pos = (w % 2 == 0) ? 1 : 2; // 짝수 번 이동(1번 나무), 홀수 번 이동(2번 나무)
				if (plumPos == pos) {
					dp[t][w] = Math.max(dp[t - 1][w] + 1, dp[t - 1][w - 1]);
				} else {
					dp[t][w] = Math.max(dp[t - 1][w - 1] + 1, dp[t - 1][w]);
				}

				answer = Math.max(answer, dp[t][w]);
			}
		}

		System.out.println(answer);
	}
}
