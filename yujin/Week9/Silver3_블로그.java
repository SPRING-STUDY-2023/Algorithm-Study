package Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver3_블로그 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        int sum = 0;
        for(int i = 0; i < x; i++) {
            sum += arr[i];
        }

        int max = sum, cnt = 1;
        for(int i = x; i < n; i++) {
            sum -= arr[i - x];
            sum += arr[i];

            if(max < sum) {
                max = sum;
                cnt = 1;
            } else if(max == sum) {
                cnt++;
            }
        }

        if(max == 0) System.out.println("SAD");
        else {
            System.out.println(max);
            System.out.println(cnt);
        }
    }
}
