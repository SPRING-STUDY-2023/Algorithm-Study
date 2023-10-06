package Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Silver2_랜선자르기 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        long n = Integer.parseInt(st.nextToken());
        long[] stick = new long[k];

        long max = Long.MIN_VALUE;
        for(int i = 0; i < stick.length; i++) {
            stick[i] = Integer.parseInt(br.readLine());
            if(stick[i] > max) max = stick[i];
        }

        Arrays.sort(stick);

        long left = 0;
        long right = max + 1;
        while(left < right) {
            long mid = (left + right) / 2;
            long count = 0;
            for(int i = 0; i < k; i++) {
                count += stick[i] / mid;
            }
            if(count <= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(right);
    }
}
