package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토_7569 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[][][] map = new int[N][M][H];
		Queue<int[]> queue = new LinkedList<>();

		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					// 1 익은 토마토, 0 안 익은 토마토, -1 빈 칸
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if (map[i][j][k] == 1) {
						queue.offer(new int[] {i, j, k});
					}
				}
			}
		}

		// bfs
		boolean[][][] visited = new boolean[N][M][H];
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		int[] dz = {1, -1};

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0], y = cur[1], z = cur[2];

			for (int d = 0; d < 4; d++) { // 동서남북
				int nx = x + dx[d], ny = y + dy[d];
				if (0 <= nx && nx < N && 0 <= ny && ny < M) {
					if (visited[nx][ny][z] || map[nx][ny][z] != 0) { // 방문한 곳이거나, 안 익은 토마토가 있는 곳이 아니면 pass
						continue;
					}
					visited[nx][ny][z] = true;
					map[nx][ny][z] = map[cur[0]][cur[1]][z] + 1;
					queue.offer(new int[] {nx, ny, z});
				}
			}

			for (int d = 0; d < 2; d++ ) { // 위아래
				int nz = z + dz[d];
				if (0 <= nz && nz < H) {
					if (visited[x][y][nz] || map[x][y][nz] != 0) {
						continue;
					}
					visited[x][y][nz] = true;
					map[x][y][nz] = map[x][y][z] + 1;
					queue.offer(new int[] {x, y, nz});
				}
			}
		}

		System.out.println(solve(map, N, M, H));

	}

	private static int solve(int[][][] map, int N, int M, int H) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < H; k++) {
					if (map[i][j][k] == 0) return -1;
					max = Math.max(max, map[i][j][k]);
				}
			}
		}
		return max - 1;
	}

}
