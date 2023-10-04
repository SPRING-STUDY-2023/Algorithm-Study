package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동_16234 {

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static boolean[][] visited;
	static int N, L, R;
	static int move;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;

		while (true) {
			move = 0;
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j]) continue;
					visited[i][j] = true;
					moving(i, j, map);
				}
			}

			if (move == 0) break;

			cnt++;
		}

		System.out.println(cnt);

	}

	private static void moving(int x, int y, int[][] map) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});

		int sum = map[x][y];

		List<int[]> al = new ArrayList<>();
		al.add(new int[] {x, y});

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
				if (0 <= nx && nx < N && 0 <= ny && ny < N) {
					if (visited[nx][ny]) continue;

					int diff = Math.abs(map[nx][ny] - map[cur[0]][cur[1]]);
					if (!(L <= diff && diff <= R)) continue;

					visited[nx][ny] = true;
					sum += map[nx][ny];
					queue.add(new int[] {nx, ny});
					al.add(new int[] {nx, ny});
					move++;
				}
			}
		}

		int avg = sum / al.size();
		for (int[] pos : al) {
			map[pos[0]][pos[1]] = avg;
		}
	}
}
