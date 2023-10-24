package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 동전2_2294 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] dp = new int[k + 1];
		Arrays.fill(dp, Integer.MAX_VALUE - 1);
		dp[0] = 0;

		int[] arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= n; i++) {
			for (int j = arr[i]; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j - arr[i]] + 1);
			}
		}

		// System.out.println(Arrays.toString(dp));

		System.out.println(dp[k] != Integer.MAX_VALUE - 1 ? dp[k] : -1);

	}
}
