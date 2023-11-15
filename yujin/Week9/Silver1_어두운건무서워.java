package Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver1_어두운건무서워 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[][] map = new int[r+1][c+1];

        for(int i = 1; i < r+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < c+1; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] += map[i-1][j] + map[i][j-1] - map[i-1][j-1] + num;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int sum = map[r2][c2] - map[r2][c1-1] - map[r1-1][c2] + map[r1 - 1][c1 - 1];
            int area = (r2-r1+1) * (c2-c1+1);
            sb.append(sum / (area)).append("\n");
        }
        System.out.println(sb);
    }
}
