package Week3_완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Gold4_불 {
    // 최단 탈출경로를 찾는거기에 BFS
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static char[][] map;
    static boolean[][] visited;
    static Queue<Point> queue;
    static LinkedList<Point> fire;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        queue = new LinkedList<>();
        fire = new LinkedList<>();
        map = new char[r][c];
        visited = new boolean[r][c];
        for(int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j = 0; j < c; j++) {
                if(map[i][j] == 'J') {
                    queue.add(new Point(j,i,0));
                    map[i][j] = '.';
                } else if(map[i][j] == 'F') {
                    fire.add(new Point(j,i,0));
                }
            }
        }
        while(true) {
            moveFire();
            int j_size = queue.size(); // 이동할 수 있을만큼만
            for(int j = 0; j < j_size; j++) {
                Point now = queue.poll();
                for(int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];
                    if(nx < 0 || ny < 0 || nx >= map[0].length || ny >= map.length ) {
                        System.out.println(now.distance + 1);
                        return;
                    }
                    else if(!visited[ny][nx] && map[ny][nx] == '.') {
                        visited[ny][nx] = true;
                        queue.add(new Point(nx,ny, now.distance + 1));
                    }
                }
            }
            if(queue.isEmpty()) {
                System.out.println("IMPOSSIBLE");
                return;
            }
        }
    }
    public static void moveFire() {
        int fire_size = fire.size();
        for(int i = 0; i < fire_size; i++) {
            Point f = fire.poll();
            for (int d = 0; d < 4; d++) {
                int nx = f.x + dx[d];
                int ny = f.y + dy[d];
                if (nx >= 0 && ny >= 0 && nx < map[0].length && ny < map.length && map[ny][nx] == '.') {
                    map[ny][nx] = 'F';
                    fire.add(new Point(nx,ny,0));
                }
            }
        }
    }
    static class Point{
        int x;
        int y;
        int distance;
        Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
