package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 불_4179 {

	/**
	 * 놓쳤던 점
	 * 1. 지훈이를 먼저 이동시킴 (문제를 꼼꼼하게 읽자)
	 */

	static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		String[][] map = new String[N][M];
		Queue<int[]> jq = new LinkedList<>();
		Queue<int[]> fq = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			String[] arr = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = arr[j];
				if (map[i][j].equals("J")) {
					jq.add(new int[] {i, j});
					map[i][j] = ".";
				} else if (map[i][j].equals("F")) {
					fq.add(new int[] {i, j});
				}
			}
		}

		solve(map, N, M, jq, fq);

	}

	private static void solve(String[][] map, int N, int M, Queue<int[]> jq, Queue<int[]> fq) {
		int time = 0;

		while (true) {
			time++;

			int fs = fq.size();
			while (fs-- > 0 && !fq.isEmpty()) {
				int[] cur = fq.poll();
				for (int i = 0; i < 4; i++) {
					int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
					if (0 <= nx && nx < N && 0 <= ny && ny < M) {
						if (map[nx][ny].equals("#") || map[nx][ny].equals("F")) {
							continue;
						}
						map[nx][ny] = "F";
						fq.add(new int[] {nx, ny});
					}
				}
			}

			int js = jq.size();
			while (js-- > 0 && !jq.isEmpty()) {
				int[] cur = jq.poll();
				for (int i = 0; i < 4; i++) {
					int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
					if (0 <= nx && nx < N && 0 <= ny && ny < M) {
						if (!map[nx][ny].equals(".")) continue;
						map[nx][ny] = "J";
						jq.add(new int[] {nx, ny});
					} else {
						System.out.println(time);
						return;
					}
				}
			}

			if (jq.isEmpty()) {
				System.out.println("IMPOSSIBLE");
				return;
			}
		}

	}
}
