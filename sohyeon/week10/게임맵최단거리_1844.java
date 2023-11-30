package week10;

import java.util.LinkedList;
import java.util.Queue;

public class 게임맵최단거리_1844 {
	public int solution(int[][] maps) {
		return bfs(maps);
	}

	private int bfs(int[][] maps) {
		int n = maps.length;
		int m = maps[0].length;

		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {0, 0, 1});

		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};

		boolean[][] visited = new boolean[n][m];
		visited[0][0] = true;

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			if (now[0] == n - 1 && now[1] == m - 1) {
				return now[2];
			}

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if (check(maps, n, m, nx, ny, visited)) {
					visited[nx][ny] = true;
					queue.add(new int[] {nx, ny, now[2] + 1});
				}
			}
		}

		return -1;
	}

	private boolean check(int[][] maps, int n, int m, int nx, int ny, boolean[][] visited) {
		return 0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny] && maps[nx][ny] == 1;
	}
}
