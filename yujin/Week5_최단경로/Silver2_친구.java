package Week5_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Silver2_친구 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            String command = br.readLine();
            for(int j = 0; j < n; j++) {
                char now = command.charAt(j);
                if('Y' == now) map[i][j] = true;
                else map[i][j] = false;
            }
        }

        boolean[][] temp = new boolean[n][n];
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(i != j && !map[i][j] && map[i][k] && map[k][j]) {
                        temp[i][j] = true;
                    }
                }
            }
        }

        int result = 0;
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = 0; j < n; j++) {
                if(map[i][j] || temp[i][j]) sum++;
            }
            result = Math.max(sum, result); // 가장 친구가 많은 사람
        }
    }
}
