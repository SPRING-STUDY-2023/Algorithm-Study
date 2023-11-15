package Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Gold4_녹색옷입은애가젤다지 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int[][] map, distance;
    static int min, n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        int cnt = 1;
        while(n != 0) {

            map = new int[n][n];
            distance = new int[n][n];

            for(int i = 0; i < n; i++) {
                Arrays.fill(distance[i], Integer.MAX_VALUE);
            }

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append("Problem " + cnt++ + ": " + move()).append("\n");
            n = Integer.parseInt(br.readLine());
        }
        System.out.println(sb);
    }

    public static int move() {

        distance[0][0] = map[0][0];
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(new Point(0,0,map[0][0]));

        while(!pq.isEmpty()) {
            Point cur = pq.poll();

            for(int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(distance[ny][nx] > distance[cur.y][cur.x] + map[ny][nx]) {
                    distance[ny][nx] = distance[cur.y][cur.x] + map[ny][nx];
                    pq.add(new Point(nx,ny,distance[ny][nx]));
                }
            }
        }
        return distance[n - 1][n - 1];
    }

    static class Point implements Comparable<Point> {
        int x,y,w;
        public Point(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
        @Override
        public int compareTo(Point o) {
            return this.w - o.w;
        }
    }
}
