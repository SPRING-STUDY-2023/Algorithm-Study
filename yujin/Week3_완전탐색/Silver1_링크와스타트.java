package Week3_완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver1_링크와스타트 {
    static int num = 0, n, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        answer = Integer.MAX_VALUE;
        int[][] map = new int[n][n];
        boolean[] visited = new boolean[n];
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1; i < n; i++) {
            num = i;
            dfs(0,0,visited, map);
        }
        System.out.println();
    }
    public static void dfs(int depth, int index, boolean[] visited, int[][] map) {
        if(num == depth) {
            int start = 0;
            int link = 0;

            for(int i = 0; i < n-1; i++) {
                for(int j = i + 1; j < n; j++) {
                    if(visited[i] && visited[j]) {
                        start += map[i][j] + map[j][i];
                    } else if(!visited[i] && !visited[j]){
                        link += map[i][j] + map[j][i];
                    }
                }
            }
            answer = Math.min(answer, Math.abs(start - link));
            return;
        }
        for(int i = index; i < n; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            dfs(depth + 1, index + 1, visited, map);
            visited[i] = false;
            dfs(depth + 1, index + 1, visited, map);
        }
    }
}
