package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 영역구하기_2583 {

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		boolean[][] map = new boolean[N][M];

		for (int t = 0; t < K; t++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			for (int i = y1; i < y2; i++) {
				for (int j = x1; j < x2; j++) {
					map[i][j] = true;
				}
			}
		}

		boolean[][] visited = new boolean[N][M];
		ArrayList<Integer> al = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!map[i][j] && !visited[i][j]) {
					int size = bfs(map, visited, N, M, i, j);
					al.add(size);
				}
			}
		}

		System.out.println(al.size());

		Collections.sort(al);
		for (int s : al) {
			System.out.print(s + " ");
		}

	}

	private static int bfs(boolean[][] map, boolean[][] visited, int N, int M, int x, int y) {
		visited[x][y] = true;
		int size = 1;

		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {x, y});

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				if (0 <= nx && nx < N && 0 <= ny && ny < M) {
					if (visited[nx][ny] || map[nx][ny]) continue;
					visited[nx][ny] = true;
					queue.offer(new int[] {nx, ny});
					size++;
				}
			}
		}

		return size;
	}

}
