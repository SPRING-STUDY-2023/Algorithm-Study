import static java.lang.Math.max;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한배낭_12865 {
    private static int N, K;
    static int[] weight;
    static int[] value;
    static int[][] DP;

    private static void dynamicProgramming() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= K; c++) {
                DP[r][c] = DP[r-1][c];
                if(c - weight[r] >= 0) {
                    DP[r][c] = max(DP[r][c], DP[r-1][c-weight[r]] + value[r]);
                }
            }
        }
        System.out.println(DP[N][K]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        weight = new int[N + 1];
        value = new int[N + 1];
        DP = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        dynamicProgramming();
    }
}
