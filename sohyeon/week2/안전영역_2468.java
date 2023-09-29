package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 안전영역_2468 {

	/**
	 * 놓쳤던 점
	 * 1. 물에 잠기는 지대가 없는 경우를 고려하지 않음(h = min-1)
	 */

	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];

		int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				min = Math.min(map[i][j], min);
				max = Math.max(map[i][j], max);
			}
		}

		int res = Integer.MIN_VALUE;

		for (int h = min - 1; h <= max; h++) {
			int cnt = 0; // 높이 h일 때 안전지대 개수
			boolean[][] visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > h && !visited[i][j]) {
						// 물에 안 잠기는 곳, 아직 방문 안 한 곳
						bfs(map, N, h, i, j, visited);
						cnt++;
					}
				}
			}
			res = Math.max(res, cnt);
		}

		System.out.println(res);

	}

	private static void bfs(int[][] map, int N, int h, int i, int j, boolean[][] visited) {
		visited[i][j] = true;

		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {i, j});

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d], ny = cur[1] + dy[d];
				if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny] && map[nx][ny] > h) {
					// 범위 내, 방문 안 한 곳, 물에 안 잠기는 곳
					visited[nx][ny] = true;
					queue.offer(new int[] {nx, ny});
				}
			}
		}
	}

}
