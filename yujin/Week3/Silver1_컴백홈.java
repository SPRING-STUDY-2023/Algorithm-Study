package Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Silver1_컴백홈 {
    static boolean[][] visited;
    static int[][] map;
    static int count;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        visited = new boolean[r][c];

        map = new int[r][c];
        for(int i = 0; i < r; i++) {
            String[] arr = br.readLine().split("");
            for(int j = 0; j < c; j++) {
                map[i][j] = arr[j].equals(".") ? 1 : 0;
            }
        }
        visited[r - 1][0] = true;
        dfs(0, r - 1, k, r, c, 1);
        System.out.println(count);
    }
    public static void dfs(int x, int y, int k, int r, int c, int depth) {
        if(x == c - 1 && y == 0) {
            if(depth == k) count++;
            return;
        }
        for(int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || ny >= r || nx >= c || visited[ny][nx] || map[ny][nx] == 0) continue;
            visited[ny][nx] = true;
            dfs(nx,ny,k,r,c,depth+1);
            visited[ny][nx] = false;
        }
    }
}
