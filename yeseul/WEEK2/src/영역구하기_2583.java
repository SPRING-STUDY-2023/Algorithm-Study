import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 영역구하기_2583 {

    static boolean[][] isVisited;
    static int[][] map;
    static int M, N, K;
    static int cnt;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        isVisited = new boolean[M][N];
        map = new int[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int y = y1; y < y2; y++) {
                for (int x = x1; x < x2; x++) {
                    map[y][x] = 1;
                }
            }
        }

        ArrayList<Integer> area = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0 && !isVisited[i][j]) {
                    cnt = 1;
                    dfs(i, j);
                    area.add(cnt);
                }
            }
        }

        Collections.sort(area);

        System.out.println(area.size());
        for (Integer integer : area) {
            System.out.print(integer + " ");
        }
    }

    static void dfs(int y, int x) {
        isVisited[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if ( nx >= 0 && ny >= 0 && nx < N && ny < M && map[ny][nx] == 0 && !isVisited[ny][nx]) {
                cnt++;
                dfs(ny, nx);
            }
        }
    }
}
