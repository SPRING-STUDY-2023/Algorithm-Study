import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 인구이동_16234 {
    static int N, L, R;
    static int[][] MAP;
    static boolean[][] Visit;
    static int Country_Number;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean Check = true;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();

        MAP = new int[N][N];
        Visit = new boolean[N][N];
        Country_Number = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                MAP[i][j] = sc.nextInt();
            }
        }

        sc.close();

        Solution();
    }

    static boolean Can_Combination2(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                if (L <= Math.abs(MAP[x][y] - MAP[nx][ny]) && Math.abs(MAP[x][y] - MAP[nx][ny]) <= R)
                    return true;
            }
        }
        return false;
    }

    static boolean Can_Combination(int x, int y, int xx, int yy) {
        return L <= Math.abs(MAP[x][y] - MAP[xx][yy]) && Math.abs(MAP[x][y] - MAP[xx][yy]) <= R;
    }

    static void BFS(int a, int b) {
        Queue<Pair> Q = new LinkedList<>();
        Queue<Pair> Nq = new LinkedList<>();
        Q.offer(new Pair(a, b));
        Nq.offer(new Pair(a, b));
        Visit[a][b] = true;
        int Sum = 0;
        int Cnt = 0;

        while (!Q.isEmpty()) {
            int x = Q.peek().x;
            int y = Q.peek().y;
            Q.poll();

            Sum = Sum + MAP[x][y];
            Cnt = Cnt + 1;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (Visit[nx][ny]) continue;
                if (Can_Combination(x, y, nx, ny)) {
                    Visit[nx][ny] = true;
                    Q.offer(new Pair(nx, ny));
                    Nq.offer(new Pair(nx, ny));
                }
            }
        }

        int Value = Sum / Cnt;

        while (!Nq.isEmpty()) {
            int x = Nq.peek().x;
            int y = Nq.peek().y;
            Nq.poll();
            MAP[x][y] = Value;
        }
    }

    static void Solution() {
        int Day = 0;
        while (Check) {
            Check = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!Visit[i][j] && Can_Combination2(i, j)) {
                        BFS(i, j);
                        Check = true;
                    }
                }
            }
            if (Check) Day++;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    Visit[i][j] = false;
                }
            }
        }
        System.out.println(Day);
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
