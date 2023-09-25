package Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gold5_토마토 {
    static int[] dh = {0,0,0,0,-1,1};
    static int[] dx = {-1,1,0,0,0,0};
    static int[] dy = {0,0,-1,1,0,0};
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        queue = new LinkedList<>();
        int[][][] map = new int[h][n][m];
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    map[k][i][j] = Integer.parseInt(st.nextToken());
                    if(map[k][i][j] == 1) queue.add(new int[]{k,i,j});
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            for(int i = 0; i < dx.length; i++) {
                int nh = now[0] + dh[i];
                int ny = now[1] + dy[i];
                int nx = now[2] + dx[i];

                if(nh < 0 || nx < 0 || ny < 0 || nh >= h || nx >= m || ny >= n) continue;
                if(map[nh][ny][nx] == 0) {
                    queue.add(new int[]{nh,ny,nx});
                    map[nh][ny][nx] = map[now[0]][now[1]][now[2]] + 1;
                }
            }
        }

        int result = Integer.MIN_VALUE;

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < m; k++) {
                    if(map[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    result = Math.max(result, map[i][j][k]);
                }
            }
        }
        if(result == 1) System.out.println(0);
        else System.out.println(result - 1);
    }
}
