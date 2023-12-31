package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커_9465 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[2][n];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println(dp(n, arr));
		}

	}

	private static int dp(int n, int[][] arr) {
		int[][] dp = new int[2][n + 1];
		dp[0][1] = arr[0][0];
		dp[1][1] = arr[1][0];

		for (int i = 2; i <= n; i++) {
			dp[0][i] = Math.max(dp[1][i - 2], dp[1][i - 1]) + arr[0][i - 1];
			dp[1][i] = Math.max(dp[0][i - 2], dp[0][i - 1]) + arr[1][i - 1];
		}

		return Math.max(dp[0][n], dp[1][n]);
	}
}
