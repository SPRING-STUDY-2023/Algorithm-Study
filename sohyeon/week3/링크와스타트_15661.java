package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 링크와스타트_15661 {

	/**
	 * 놓쳤던 점 : 전체적인 아이디어가 부족!
	 * 1. comb를 활용하여 팀을 나누면 된다.
	 * 2. 이중 for문을 활용하여 팀의 능력치를 구한다.
	 * 3. 각 팀의 인원 수는 꼭 절반에서 최솟값이 나오는 것이 아니다.
	 */

	static int N, res = Integer.MAX_VALUE;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[N];
		for (int t = 1; t <= N -1; t++) {
			comb(0, 0, map, t);
			if (res == 0) break;
		}
		System.out.println(res);
	}

	private static void comb(int start, int count, int[][] map, int t) {
		if (res == 0) return;

		if (count == t) {
			res = Math.min(res, diff(map));
			return;
		}

		for (int i = start; i < N; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			comb(i + 1, count + 1, map, t);
			visited[i] = false;
		}
	}

	private static int diff(int[][] map) {
		int S = 0, L = 0;

		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (visited[i] == visited[j]) {
					if (visited[i]) { // S
						S += (map[i][j] + map[j][i]);
					} else { // L
						L += (map[i][j] + map[j][i]);
					}
				}
			}
		}

		return Math.abs(S - L);
	}
}
