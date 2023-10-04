package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 청소년상어_19236 {

	/**
	 * 놓쳤던 점
	 * 1. 이래저래 꼼꼼 이슈 ;^^;
	 */

	static class Shark {
		int x, y, dir, sum;

		Shark(int x, int y, int dir, int sum) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.sum = sum;
		}
	}

	static class Fish {
		int id, x, y, dir;
		boolean isAlive;

		Fish(int id, int x, int y, int dir, boolean isAlive) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.isAlive = isAlive;
		}

		public void eaten(int[][] map) {
			this.isAlive = false;
			map[this.x][this.y] = -2;
		}

		public void move(int nx, int ny, int[][] map) {
			this.x = nx;
			this.y = ny;
			map[nx][ny] = this.id;
		}

		public void move(int nx, int ny, int dir, int[][] map) {
			this.x = nx;
			this.y = ny;
			this.dir = dir;
			map[nx][ny] = this.id;
		}
	}

	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	static int max = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] map = new int[4][4];
		List<Fish> fishes = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int id = Integer.parseInt(st.nextToken()) - 1; // 번호
				int dir = Integer.parseInt(st.nextToken()) - 1; // 방향
				fishes.add(new Fish(id, i, j, dir, true));
				map[i][j] = id;
			}
		}

		/**
		 *         Collections.sort(fishes, new Comparator<Fish>() {
		 *             @Override
		 *             public int compare(Fish o1, Fish o2) {
		 *                 return o1.id - o2.id;
		 *             }
		 *         });
		 */
		fishes.sort(Comparator.comparingInt(o -> o.id)); // 람다 표현식

		// 처음 물고기를 잡아먹는다.
		Fish fish = fishes.get(map[0][0]);
		Shark shark = new Shark(0, 0, fish.dir, fish.id + 1);
		fish.eaten(map);

		dfs(map, shark, fishes);
		System.out.println(max);
	}

	private static void dfs(int[][] map, Shark shark, List<Fish> fishes) {
		// 1. 최댓값 업데이트
		max = Math.max(max, shark.sum);

		// 2. 물고기 이동
		fishes.forEach(fish -> moving(fish, fishes, map));

		// 3. 상어 이동 (물고기 먹고 dfs)
		for (int i = 1; i < 4; i++) {
			int nx = shark.x + dx[shark.dir] * i, ny = shark.y + dy[shark.dir] * i;

			if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && map[nx][ny] >= 0) {
				int[][] newMap = copyArr(map);
				List<Fish> newFishes = copyList(fishes);

				newMap[shark.x][shark.y] = -1; // 빈칸
				Fish fish = newFishes.get(map[nx][ny]);
				Shark newShark = new Shark(fish.x, fish.y, fish.dir, shark.sum + fish.id + 1);
				fish.eaten(newMap);

				dfs(newMap, newShark, newFishes);
			}
		}

	}

	private static void moving(Fish fish, List<Fish> fishes, int[][] map) {
		if (!fish.isAlive) return;

		for (int i = 0; i < 8; i++) {
			int newDir = (fish.dir + i) % 8;
			int nx = fish.x + dx[newDir], ny = fish.y + dy[newDir];
			if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && map[nx][ny] > -2) {
				map[fish.x][fish.y] = -1;
				if (map[nx][ny] >= 0) { // 다른 물고기
					Fish preFish = fishes.get(map[nx][ny]);
					preFish.move(fish.x, fish.y, map);
				}
				fish.move(nx, ny, newDir, map);
				return;
			}
		}
	}

	private static int[][] copyArr(int[][] map) {
		int[][] arr = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				arr[i][j] = map[i][j];
			}
		}
		return arr;
	}

	private static List<Fish> copyList(List<Fish> fishes) {
		List<Fish> al = new ArrayList<>();
		fishes.forEach(f -> al.add(new Fish(f.id, f.x, f.y, f.dir, f.isAlive)));
		return al;
	}
}
