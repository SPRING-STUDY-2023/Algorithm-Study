package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한배낭_12865 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken()); // 무게
			int v = Integer.parseInt(st.nextToken()); // 가치
			arr[i] = new int[] {w, v};
		}

		int[][] dp = new int[n + 1][k + 1];
		for (int j = 0; j <= k; j++) {
			for (int i = 1; i <= n; i++) {
				if (j < arr[i - 1][0]) { // 못 담는다.
					dp[i][j] = dp[i - 1][j];
				} else {
					int pre = j - arr[i - 1][0];
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][pre] + arr[i - 1][1]);
				}
			}
		}

		System.out.println(dp[n][k]);

	}
}
