package week12;

public class 정수삼각형_43105 {
	public int solution(int[][] triangle) {
		int n = triangle.length;
		int m = triangle[triangle.length - 1].length;
		int[][] dp = new int[n][m];

		for (int i = 0; i < m; i++) {
			dp[n - 1][i] = triangle[n - 1][i];
		}

		for (int i = n - 2; i >= 0; i--) {
			for (int j = 0; j < triangle[i].length; j++) {
				dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j];
			}
		}

		return dp[0][0];
	}
}
