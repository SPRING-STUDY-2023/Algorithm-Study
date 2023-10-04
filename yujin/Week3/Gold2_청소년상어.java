package Week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 최대로 많이 먹은 경우 탐색 -> 완전 탐색 (깊이 우선 탐색)
public class Gold2_청소년상어 {
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Point[] fishes = new Point[17];
        int[][] map = new int[4][4];
        for(int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine()," ");
            for(int j = 0; j < 4; j++) {
                int fish = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken()) - 1;
                map[i][j] = fish;
                fishes[fish] = new Point(j,i,direction);
            }
        }
        dfs(map, fishes, 0,0,0);
        System.out.println(result);
    }
    public static void dfs(int[][] map, Point[] fishes, int sharkX, int sharkY, int sum) {

        Point[] tempFish = new Point[17];
        int[][] tempMap = new int[4][4];
        for(int i = 1; i < 17; i++) {
            if(fishes[i] == null) continue;
            tempFish[i] = new Point(fishes[i].x, fishes[i].y, fishes[i].direction);
        }
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                tempMap[i][j] = map[i][j];
            }
        }
        int fishNumber = tempMap[sharkY][sharkX];
        int sharKDirection = tempFish[fishNumber].direction;
        tempFish[fishNumber] = null;
        tempMap[sharkY][sharkX] = -1;

        sum += fishNumber;
        if(sum > result) result = sum;

        for(int i=1;i<17;i++){
            if(tempFish[i] == null) continue;
            int x = tempFish[i].x;
            int y = tempFish[i].y;
            int d = tempFish[i].direction;
            int nx = x + dx[d];
            int ny = y + dy[d];
            int nd = d;
            while (ny < 0 || ny >= 4 || nx < 0 || nx >= 4 || (ny == sharkY && nx == sharkX)) {
                nd = (nd + 1) % 8;
                ny = y + dy[nd];
                nx = x + dx[nd];
            }

            if (tempMap[ny][nx] != -1){
                int target = tempMap[ny][nx];
                tempFish[target].x = x;
                tempFish[target].y = y;

                tempFish[i].x = nx;
                tempFish[i].y  = ny;
                tempFish[i].direction = nd;

                tempMap[y][x] = target;
                tempMap[ny][nx] = i;
            }
            else{
                tempFish[i].x = nx;
                tempFish[i].y  = ny;
                tempFish[i].direction = nd;

                tempMap[y][x] = -1;
                tempMap[ny][nx] = i;
            }

        }

        // 4의 map 공간이여서 이동가능이 최대 3칸
        for(int i=1;i<4;i++){
            int x = sharkX + dx[sharKDirection]  * i;
            int y = sharkY + dy[sharKDirection]  * i;
            if(x>=4 || x< 0 || y>=4 || y<0) break;
            if(tempMap[y][x] != -1){
                dfs(tempMap, tempFish, x, y, sum);
            }
        }
    }
    static class Point{
        int x;
        int y;
        int direction;
        Point(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}
