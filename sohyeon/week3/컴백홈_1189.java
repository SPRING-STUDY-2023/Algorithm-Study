import java.util.*;
import java.io.*;

public class Main {

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	static int R, C, K, res = 0;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		String[][] map = new String[R][C];

		for (int i = 0; i < R; i++) {
			String[] arr = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				map[i][j] = arr[j];
			}
		}

		visited = new boolean[R][C];
		visited[R - 1][0] = true;
		solve(map, R - 1, 0, 1);
		System.out.println(res);

	}

	private static void solve(String[][] map, int x, int y, int n) {
		if (n > K) return;
		if (x == 0 && y == C - 1 && n != K) return;

		if (n == K) {
			if (x == 0 && y == C - 1) {
				res++;
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i], ny = y + dy[i];
			if (0 <= nx && nx < R && 0 <= ny && ny < C) {
				if (visited[nx][ny] || map[nx][ny].equals("T")) {
					continue;
				}
				visited[nx][ny] = true;
				solve(map, nx, ny, n + 1);
				visited[nx][ny] = false;
			}
		}
	}

}
