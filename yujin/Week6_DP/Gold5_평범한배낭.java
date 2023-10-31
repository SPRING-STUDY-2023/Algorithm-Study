package Week6_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Gold5_평범한배낭 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Bag[] bag = new Bag[n];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            bag[i] = new Bag(weight, value);
        }

        int[] dp = new int[k + 1];
        for(Bag b : bag) {
            for(int i = k; i >= b.weight; i--) {
//            for(int i = b.weight; i <= k; i++) {
                dp[i] = Math.max(dp[i - b.weight] + b.value, dp[i]);
            }
        }
        System.out.println(dp[k]);
    }
    public static class Bag{
        int weight;
        int value;
        Bag(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
