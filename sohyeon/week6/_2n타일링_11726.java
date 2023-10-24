package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _2n타일링_11726 {

	static final int MOD = 10007;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		if (n == 1) {
			System.out.println(1);
		} else if (n == 2) {
			System.out.println(2);
		} else {
			int[] dp = new int[n + 1];
			dp[1] = 1;
			dp[2] = 2;
			for (int i = 3; i <= n; i++) {
				dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
			}
			System.out.println(dp[n]);
		}
	}
}
