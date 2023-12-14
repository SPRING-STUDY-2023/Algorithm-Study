package week13;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class 석유시추_250136 {
	int[] dx = {1, -1, 0, 0};
	int[] dy = {0, 0, 1, -1};
	int n, m;
	int[][] land;
	int[][] oil;
	boolean[][] visited;

	public int solution(int[][] land) {
		this.land = land;
		n = land.length;
		m = land[0].length;
		oil = new int[n][m];
		visited = new boolean[n][m];

		int oilId = 0;
		Map<Integer, Integer> oilSize = new HashMap<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (land[i][j] == 1 && !visited[i][j]) {
					int size = bfs(i, j, oilId);
					oilSize.put(oilId, size);
					oilId++;
				}
			}
		}

		int[] oilSum = new int[m]; // 각 열에서 뽑을 수 있는 석유 양
		for (int j = 0; j < m; j++) {
			Set<Integer> oilSet = new HashSet<>();
			for (int i = 0; i < n; i++) {
				if (land[i][j] == 1) {
					oilSet.add(oil[i][j]);
				}
			}
			for (int id : oilSet) {
				oilSum[j] += oilSize.get(id);
			}
		}

		return Arrays.stream(oilSum).max().getAsInt();
	}

	private int bfs(int x, int y, int oilId) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {x, y});
		visited[x][y] = true;
		oil[x][y] = oilId;
		int size = 1;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (0 <= nx && nx < n && 0 <= ny && ny < m) {
					if (land[nx][ny] != 1 || visited[nx][ny]) continue;
					queue.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
					oil[nx][ny] = oilId;
					size++;
				}
			}
		}

		return size;
	}
}