import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 아기상어_16236 {
    static int[][] map;
    static boolean[][] isVisited;
    static int N;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static Shark shark;

    static class Shark {
        int x;
        int y;
        int size;
        int eatenFishCount;
        int maxFishEatingTime;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = 2;
            this.eatenFishCount = 0;
            this.maxFishEatingTime = 0;
        }
    }

    /**
     * 문제 조건
     * 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
     */
    public static int findClosestFish() { // BFS를 통해서 먹을 수 있는 물고기 위치 탐색
        Queue<int[]> queue = new LinkedList<>(); // 아기 상어가 이동하는 위치 저장
        queue.add(new int[]{shark.x, shark.y, 0}); // x좌표, y좌표, 이동 시간

        isVisited = new boolean[N][N];
        isVisited[shark.x][shark.y] = true;

        List<int[]> canEatFishPos = new ArrayList<>(); // 먹을 수 있는 물고기의 위치 저장

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int time = current[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !isVisited[nx][ny]) {
                    if (map[nx][ny] == 0 || map[nx][ny] == shark.size) { // 빈 칸이거나 같은 크기의 물고기가 있는 경우
                        queue.add(new int[]{nx, ny, time + 1}); // 이동 가능하기 때문에 큐에 위치 저장
                        isVisited[nx][ny] = true;
                    } else if (map[nx][ny] < shark.size) { // 먹을 수 있는 물고기인 경우
                        canEatFishPos.add(new int[]{nx, ny, time + 1});
                    }
                }
            }
        }

        if (!canEatFishPos.isEmpty()) {
            // 거리가 가장 가까운 먹이를 선택
            Collections.sort(canEatFishPos, (a, b) -> {
                if (a[2] == b[2]) {
                    if (a[0] == b[0]) {
                        return a[1] - b[1]; // 가장 위쪽에 있는 물고기
                    }
                    return a[0] - b[0]; // 가장 왼쪽에 있는 물고기
                }
                return a[2] - b[2];
            });

            int[] closestFish = canEatFishPos.get(0);
            int x = closestFish[0];
            int y = closestFish[1];
            int time = closestFish[2];

            // 물고기 먹고 상어 위치 갱신
            shark.x = x;
            shark.y = y;
            shark.eatenFishCount++;

            if (shark.eatenFishCount == shark.size) {
                shark.eatenFishCount = 0;
                shark.size++;
            }

            shark.maxFishEatingTime += time;

            map[x][y] = 0; // 먹은 물고기 칸은 빈 칸으로 처리

            return time;
        }

        return -1; // 먹을 수 있는 물고기가 없는 경우
    }

    public static int findTotalTime() {
        int totalTime = 0;
        while (true) {
            int time = findClosestFish();
            if (time == -1) {
                break;
            }
            totalTime += time;
        }
        return totalTime;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Shark(i, j);
                    map[i][j] = 0; // 아기 상어가 있던 자리는 빈 칸으로 처리
                }
            }
        }

        int totalTime = findTotalTime();
        System.out.println(totalTime);
    }
}
