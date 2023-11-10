import java.util.*;
import java.io.*;

import org.w3c.dom.Node;

public class Main {
	static class Position implements Comparable<Position> {
		int r;
		int c;
		int cost;

		Position(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Position p) {
			return this.cost - p.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		int num = 1;

		while (true) {
			int N = Integer.parseInt(br.readLine()); // 동굴의 크기

			if (N == 0)
				break;

			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int res = bfs(map, N);
			System.out.println("Problem " + num + ": " + res);
			num++;
		}
	}

	private static int bfs(int[][] map, int N) {
		Queue<Position> queue = new PriorityQueue<>();
		queue.add(new Position(0, 0, map[0][0]));

		int[][] move = new int[N][N];
		for (int[] arr : move)
			Arrays.fill(arr, Integer.MAX_VALUE);
		move[0][0] = map[0][0];

		int[] dr = {0, 0, 1, -1};
		int[] dc = {1, -1, 0, 0};

		while (!queue.isEmpty()) {
			Position cur = queue.poll();

			if (cur.r == N - 1 && cur.c == N - 1)
				return cur.cost;

			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (0 <= nr && nr < N && 0 <= nc && nc < N && move[nr][nc] > cur.cost + map[nr][nc]) {
					move[nr][nc] = cur.cost + map[nr][nc];
					queue.add(new Position(nr, nc, move[nr][nc]));
				}
			}
		}

		return -1;
	}
}
