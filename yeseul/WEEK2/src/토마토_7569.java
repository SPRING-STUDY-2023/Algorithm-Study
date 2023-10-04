import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 토마토_7569 {
    static int[][][] box;
    static int dx[] = {0, 0, -1, 1, 0, 0}; // 상, 하, 좌, 우, 위, 아래
    static int dy[] = {-1, 1, 0, 0, 0, 0};
    static int dz[] = {0, 0, 0, 0, 1, -1};
    static int M, N, H;
    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    // bfs를 시작하는 노드를 큐에 추가
                    if (box[i][j][k] == 1) {
                        queue.add(new int[]{i, j, k});
                    }
                }
            }
        }

        bfs(queue);
    }

    static void bfs(Queue<int[]> queue) {
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] tomato = queue.poll();
                int x = tomato[0];
                int y = tomato[1];
                int z = tomato[2];

                for (int j = 0; j < 6; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    int nz = z + dz[j];

                    if (nx >= 0 && nx < H && ny >= 0 && ny < N && nz >= 0 && nz < M &&
                            box[nx][ny][nz] == 0) {
                        box[nx][ny][nz] = 1;
                        queue.add(new int[]{nx, ny, nz});
                    }
                }
            }
            result++;
        }

        // 모든 토마토가 익었는지 확인
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (box[i][j][k] == 0) {
                        System.out.println(-1); // 익지 않은 토마토가 남아있는 경우
                        return;
                    }
                }
            }
        }

        System.out.println(result - 1); // 시작일을 0일로 카운트했으므로 1을 빼줌
    }
}
