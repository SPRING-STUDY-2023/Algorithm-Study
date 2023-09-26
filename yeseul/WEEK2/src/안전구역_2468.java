import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 안전구역_2468 {
    static int N;
    static int min_value = 101;
    static int max_value = 0;
    static int[][] arr;
    static boolean[][] isVisited;
    static int result = 1;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static void DFS(int x, int y, int height) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if (arr[nx][ny] <= height || isVisited[nx][ny]) continue;

            isVisited[nx][ny] = true;
            DFS(nx, ny, height);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        isVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                min_value = Math.min(min_value, arr[i][j]);
                max_value = Math.max(max_value, arr[i][j]);
            }
        }

        for (int h = 0; h <= max_value; h++) {
            int cnt = 0;
            isVisited = new boolean[N][N]; // isVisited 배열 초기화
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (arr[j][k] > h && !isVisited[j][k]) {
                        isVisited[j][k] = true;
                        cnt++;
                        DFS(j, k, h);
                    }
                }
            }
            result = Math.max(result, cnt);
        }
        System.out.println(result);
    }
}
