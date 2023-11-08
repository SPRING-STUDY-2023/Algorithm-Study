import static java.lang.Math.min;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;

public class 동전2_2294 {
    private static final int INF = 987654321;
    private static int n, k;
    private static int[] coin = new int[101];
    private static int[] DP = new int[10001];

    private static void dynamicProgramming() {
        for (int i = 1 ; i <= n; i++) {
            for (int j = coin[i]; j <= k; j++) {
                DP[j] = min(DP[j], DP[j - coin[i]] + 1);
            }
        }

        if (DP[k] == INF) {
            System.out.println("-1");
            return;
        }
        System.out.println(DP[k]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            coin[i] = scanner.nextInt();
        }

        for (int i = 1; i <= k; i++) {
            DP[i] = INF;
        }

        dynamicProgramming();
    }
}
