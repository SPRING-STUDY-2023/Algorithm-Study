package Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gold4_인구이동 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int n,l,r;
    static boolean isMove;
    static List<int[]> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        move(map);
    }

    static void move(int[][] map) {
        int answer = 0;
        while(true) {
            boolean[][] visited = new boolean[n][n];
            Queue<int[]> queue = new LinkedList<>();
            isMove = false;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(!visited[i][j]) {
                        int sum = bfs(i,j,queue,map,visited);
                        if(list.size() > 1) {
                            changePopulation(sum, map);
                            isMove = true;
                        }
                    }
                }
            }
            if(!isMove) {
                System.out.println(answer);
                break;
            }
            answer++;
        }
    }
    public static int bfs(int yy, int xx, Queue<int[]> queue, int[][] map, boolean[][] visited) {
        list = new LinkedList<>();
        int sum = map[yy][xx];
        visited[yy][xx] = true;
        queue.add(new int[]{xx,yy});
        list.add(new int[]{xx,yy});
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for(int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                int diff = Math.abs(map[ny][nx] - map[y][x]);
                if(diff <= r && diff >= l && !visited[ny][nx]) {
                    queue.add(new int[]{nx,ny});
                    visited[ny][nx] = true;
                    list.add(new int[]{nx,ny});
                    sum += map[ny][nx];
                }
            }
        }
        return sum;
    }

    public static void changePopulation (int sum, int[][] map) {
        for(int[] l : list) {
            int x = l[0];
            int y = l[1];
            map[y][x] = sum/list.size();
        }
    }
}
