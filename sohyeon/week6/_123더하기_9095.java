import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int num = Integer.parseInt(br.readLine());
			System.out.println(dp(num));
		}

	}

	private static int dp(int num) {
		if (num <= 3) {
			if (num == 1) {
				return 1;
			} else if (num == 2) {
				return 2;
			} else if (num == 3) {
				return 4;
			}
		}

		int[] dp = new int[num + 1];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int i = 4; i <= num; i++) {
			dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
		}

		return dp[num];
	}
}
