package Programmers;

public class 정수삼각형 {
    public int solution(int[][] triangle) {
        int answer = 0;

        int[] dp = new int[triangle.length];
        dp[0] = triangle[0][0];

        for(int d = 1; d < triangle.length; d++) {
            for(int i = triangle[d].length - 1; i >= 0; i--) { // 뒤부터 쌓아 올려야 최대 값을 구할 수 있다.
                if(i == 0) dp[d] += triangle[d][i];
                else if(i == triangle[d].length - 1) {
                    dp[d] += dp[i - 1] + triangle[d][i];
                }
                else {
                    dp[d] += Math.max(dp[i - 1] + triangle[d][i], dp[d] + triangle[d][i]);
                }
            }
        }

        int max = 0;
        for(int i = 0; i < dp.length; i++) {
            if(max < dp[i]) max = dp[i];
        }
        return max;
    }
    public static void main(String[] args) {
    }
}
