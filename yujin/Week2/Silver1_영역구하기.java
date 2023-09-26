package Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Silver1_영역구하기 {
    static boolean[][] visited;
    static int[][] map;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        visited = new boolean[m][n];

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());
            int rx = Integer.parseInt(st.nextToken());
            int ry = Integer.parseInt(st.nextToken());
            for(int x = lx; x < rx; x++) {
                for(int y = ly; y < ry; y++) {
                    map[y][x] = 1;
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        int area = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 0 && !visited[i][j]) {
                    count = 1;
                    visited[i][j] = true;
                    area++;
                    dfs(i,j);
                    list.add(count);
                }
            }
        }
        System.out.println(area);
        Collections.sort(list);
        for(int l : list) {
            System.out.print(l + " ");
        }
    }
    static void dfs(int y, int x) {
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};
        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if(nx < 0 || ny < 0 || nx >= map[0].length || ny >= map.length) continue;
            if(map[ny][nx] == 0 && !visited[ny][nx]) {
                visited[ny][nx] = true;
                dfs(ny,nx);
                count++;
            }
        }
    }
}
