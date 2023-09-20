package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 낚시왕_17143 {

	/**
	 * 놓쳤던 점
	 * 1. 이동할 때 한 번의 계산으로 처리하려고 했다. (시간복잡도 고려해서 한 칸씩 움직이는 방법도 생각해보면 좋았을 듯)
	 */

	static class Shark {
		int r, c, s, d, z;
		boolean isCatch;

		Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.isCatch = false;
		}

		public void beCaught() {
			this.isCatch = true;
		}

		public void setPosition(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public void setDirection(int d) {
			this.d = d;
		}
	}

	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, 1, -1}; // 위 아래 오른쪽 왼쪽

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken()); // 격자판 세로
		int C = Integer.parseInt(st.nextToken()); // 격자판 가로
		int M = Integer.parseInt(st.nextToken()); // 상어 개수

		int[][] map = new int[R][C];
		Map<Integer, Shark> sharkMap = new HashMap<>();

		for (int i = 0; i < M; i++) { // 상어 정보
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1; // 상어 위치 세로
			int c = Integer.parseInt(st.nextToken()) - 1; // 상어 위치 가로
			int s = Integer.parseInt(st.nextToken()); // 상어 속력
			int d = Integer.parseInt(st.nextToken()) - 1; // 상어 방향
			int z = Integer.parseInt(st.nextToken()); // 상어 크기 (모두 다름)
			sharkMap.put(z, new Shark(r, c, s, d, z));
			map[r][c] = z;
		}

		int sum = 0;

		for (int t = 0; t < C; t++) { // C초 이동

			// 낚시
			int size = fishing(map, R, t, sharkMap);
			sum += size;

			// 상어 이동
			moving(map, R, C, sharkMap);

			// 중복 체크
			for (int key : sharkMap.keySet()) {
				Shark shark = sharkMap.get(key);
				if (shark.isCatch) continue;

				if (map[shark.r][shark.c] == 0 || map[shark.r][shark.c] < shark.z) { // 빈 칸이거나 이미 있던 상어보다 크면
					int preSharkSize = map[shark.r][shark.c];
					if (preSharkSize != 0) sharkMap.get(preSharkSize).beCaught(); // 크기가 작은 상어가 잡아먹힌다
					map[shark.r][shark.c] = shark.z;
				} else { // 잡아먹힘
					shark.beCaught();
				}
			}

		}

		System.out.println(sum);

	}

	private static void moving(int[][] map, int R, int C, Map<Integer, Shark> sharkMap) {
		for (int key : sharkMap.keySet()) {
			Shark shark = sharkMap.get(key);
			if (shark.isCatch) continue; // 잡혔으면 pass

			map[shark.r][shark.c] = 0; // 이동하니까 비우기

			int speed = shark.s; // 속도 = 이동 횟수
			int nr = shark.r; // 이동할 위치
			int nc = shark.c;

			while (speed > 0) {
				nr += dx[shark.d];
				nc += dy[shark.d];
				if (0 <= nr && nr < R && 0 <= nc && nc < C) { // 이동 가능한 곳이면 이동하고 이동 횟수 차감
					speed--;
				} else { // 이동 못한다 => 이전 위치로 돌려주고 방향만 반대로 바꿔주기
					nr -= dx[shark.d];
					nc -= dy[shark.d];
					if (shark.d == 0) shark.setDirection(1);
					else if (shark.d == 1) shark.setDirection(0);
					else if (shark.d == 2) shark.setDirection(3);
					else shark.setDirection(2);
				}
			}

			shark.setPosition(nr, nc); // 이동한 위치로 업데이트
		}
	}

	private static int fishing(int[][] map, int R, int c, Map<Integer, Shark> sharkMap) {
		for (int i = 0; i < R; i++) {
			if (map[i][c] != 0) {
				int z = map[i][c]; // 잡은 상어의 크기
				sharkMap.get(z).beCaught(); // isCatch = true
				map[i][c] = 0; // 잡고 비워주기
				return z;
			}
		}
		return 0; // 못 잡음
	}

}
