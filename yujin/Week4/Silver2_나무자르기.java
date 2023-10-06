package Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Silver2_나무자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[] tree = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            tree[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(tree);

        long left = tree[0];
        long right = tree[n - 1] + 1;

        while(left < right) {
            long mid = (left + right) / 2;
            long sum = 0;
            for(int i = 0; i < n; i++) {
                if(tree[i] > mid) {
                    sum += tree[i] - mid;
                }
            }
            if(sum < m) right = mid;
            else left = mid + 1;
        }
        System.out.println(left - 1);
    }
}
