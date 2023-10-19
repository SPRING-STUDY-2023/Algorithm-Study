package Week6_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver3_스티커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        while(t-- > 0){

            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[2][n + 1];
            int[][] dp = new int[2][n + 1];

            for(int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = map[0][1];
            dp[1][0] = map[1][1];
            for(int i = 2; i <= n; i++) {
                dp[0][i] = Math.max(dp[1][i - 2], dp[1][i - 1]) + map[0][i];
                dp[1][i] = Math.max(dp[0][i - 2], dp[0][i - 1]) + map[1][i];
            }
            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}
