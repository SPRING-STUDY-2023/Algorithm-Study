package Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver1_인간_컴퓨터상호작용 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int[] temp = new int[26];
        int[][] dp = new int[s.length()][26];
        for(int i = 0; i < s.length(); i++) {
            temp[s.charAt(i) - 'a']++;
            for(int j = 0; j < 26; j++) {
                dp[i][j] = temp[j]; // 값을 하나하나 넣지 않으면 주소 값이 변경되면서 이전의 값이 누적되지 않음.
            }
        }

        StringBuilder sb = new StringBuilder();
        int q = Integer.parseInt(br.readLine());
        for(int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int letter = st.nextToken().charAt(0) - 'a';
            int start = Integer.parseInt(st.nextToken());
            int latest = Integer.parseInt(st.nextToken());

            int count = dp[latest][letter];
            if(start != 0) count -= dp[start - 1][letter];
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
