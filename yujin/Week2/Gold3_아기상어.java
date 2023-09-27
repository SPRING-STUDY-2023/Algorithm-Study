package Week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gold3_아기상어 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        Point now = null;
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) now = (new Point(j,i,0));
            }
        }
        int[] dx = {0,0,-1,1};
        int[] dy = {1,-1,0,0};

        int size = 2;
        int eat = 0;
        int time = 0;
        while(true) {
            PriorityQueue<Point> pq = new PriorityQueue<>(((o1, o2) -> {
                if(o1.distance == o2.distance) {
                    if(o1.y == o2.y) return o1.x - o2.x;
                    return o1.y - o2.y;
                } return o1.distance - o2.distance;
            }));

            pq.add(new Point(now.x, now.y, 0));
            boolean[][] visited = new boolean[n][n];
            visited[now.y][now.x] = true;
            boolean eatFlag = false;
            while(!pq.isEmpty()) {
                now = pq.poll();

                if(map[now.y][now.x] != 0 && map[now.y][now.x] < size) {
                    eat++;
                    map[now.y][now.x] = 0;
                    time += now.distance;
                    eatFlag = true;
                    break;
                }

                for(int i = 0; i < dx.length; i++) {
                    int nx = dx[i] + now.x;
                    int ny = dy[i] + now.y;
                    if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    if(map[ny][nx] <= size && !visited[ny][nx]) {
                        pq.add(new Point(nx, ny, now.distance + 1));
                        visited[ny][nx] = true;
                    }
                }
            }
            if(!eatFlag) break;
            if(size == eat) {
                size += 1;
                eat = 0;
            }
        }

    }
    static class Point {
        int x; int y; int distance;
        Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
