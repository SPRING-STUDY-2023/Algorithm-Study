package Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Silver1_안전영역 {
    static boolean[][] visited;
    static Queue<int[]> q;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        q = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
                min = Math.min(min, map[i][j]);
            }
        }
        int maxCount = 0;
        for(int d = min; d <= max; d++) {
            int area = 0;
            visited = new boolean[n][n];
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(map[i][j] >= d && !visited[i][j]) {
                        visited[i][j] = true;
                        dfs(d, i, j);
                        area++;
                    }
                }
            }
            maxCount = Math.max(area, maxCount);
        }
        System.out.println(maxCount);
    }
    public static void dfs(int depth, int y, int x) {
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if(nx < 0 || ny < 0 || nx >= map[0].length || ny >= map.length) continue;
            if(map[ny][nx] >= depth && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(depth, ny,nx);
            }
        }
    }
}
