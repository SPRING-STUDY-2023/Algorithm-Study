package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 아기상어_16236 {

	/**
	 * 놓쳤던 점
	 * 1. class 사용
	 * 2. 거리가 가까운 모든 물고기 위치를 모아서 우선 순위 판별 (dx, dy 순서를 잘 지정해서만 해결하려고 함, 너무 깊게 생각하지 말 것)
	 */

	static class Shark {
		int x;
		int y;
		int size;
		int count;
		int time;

		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
			this.size = 2;
			this.count = 0;
			this.time = 0;
		}

		public void eat(int[] target) {
			this.x = target[0]; // 위치 재지정
			this.y = target[1];
			this.time += target[2]; // 거리만큼 시간 업데이트
			this.count++; // 고기 먹은 수
			if (this.count == this.size) { // 고기 먹은 수가 크기랑 같아질 때
				this.size++;
				this.count = 0;
			}
		}
	}

	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	static Shark shark;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark = new Shark(i, j);
					map[i][j] = 0;
				}
			}
		}

		while (true) {
			Queue<int[]> fishes = search(map, N); // 먹을 수 있는 고기 위치 후보 찾기

			if (fishes.isEmpty()) {
				break;
			}

			int[] target = fishes.poll();
			while (!fishes.isEmpty()) {
				int[] cur = fishes.poll();
				if (cur[2] < target[2]) {
					target = cur;
				} else if (cur[2] == target[2]) {
					if (cur[0] < target[0]) {
						target = cur;
					} else if (cur[0] == target[0] && cur[1] < target[1]) {
						target = cur;
					}
				}
			}

			map[target[0]][target[1]] = 0; // 고기 잡고 비우기
			shark.eat(target); // 고기 냠냠
		}

		System.out.println(shark.time);

	}

	private static Queue<int[]> search(int[][] map, int N) {
		Queue<int[]> fishes = new LinkedList<>(); // 먹을 수 있는 고기 위치 후보

		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {shark.x, shark.y, 0});

		boolean[][] visited = new boolean[N][N];
		visited[shark.x][shark.y] = true;

		int distance = Integer.MAX_VALUE; // 먹을 수 있는 고기 최소 거리

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			if (cur[2] > distance) break; // 최소 거리 이미 넘었으면 끝

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i], ny = cur[1] + dy[i];
				if (0 <= nx && nx < N && 0 <= ny && ny < N) {
					if (visited[nx][ny] || map[nx][ny] > shark.size) continue; // 방문한 곳이거나 상어 크기보다 큰 물고기가 있거나
					visited[nx][ny] = true;
					if (map[nx][ny] != 0 && map[nx][ny] < shark.size) { // 상어 크기보다 작은 물고기가 있는 곳
						distance = cur[2] + 1;
						fishes.offer(new int[] {nx, ny, cur[2] + 1});
					}
					queue.offer(new int[] {nx, ny, cur[2] + 1});
				}
			}
		}

		return fishes;
	}

}
