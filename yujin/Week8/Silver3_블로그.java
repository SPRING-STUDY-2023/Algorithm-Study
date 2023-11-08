package Week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver3_블로그 {
    static int N, X, cnt = 1;
    static int[] hit; // 블로그 방문자 수
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 변수 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        hit = new int[N];

        // 배열 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            hit[i] = Integer.parseInt(st.nextToken());
        }

        solve();
    }
    public static void solve() {

        int sum = 0;
        for(int i = 0; i < X; i++) {
            sum += hit[i];
        }

        int max = sum;
        for(int i = X; i < N; i++) {
            sum -= hit[i - X];
            sum += hit[i];

            if(max < sum) {
                cnt = 1;
                max = sum;
            } else if(max == sum) cnt++;
        }

        if(max == 0) {
            System.out.println("SAD");
            return;
        }

        sb.append(max).append("\n").append(cnt);
        System.out.println(sb);
    }
}
