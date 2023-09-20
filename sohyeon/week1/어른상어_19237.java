package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 어른상어_19237 {

	/**
	 * 놓친 요소
	 * 1. 냄새 중복 체크 (과거에 냄새를 남기고, 돌아와서 다시 냄새를 남길 수 있다.)
	 * 2. 1000초 포함 (1000초 "넘어가면" -1, 1000초까지는 1000초로 출력한다.)
	 */

	static class Shark {
		Integer n; // 번호
		Integer r; // 현재 위치 (r, c)
		Integer c; // 현재 위치 (r, c)
		Integer d; // 현재 방향

		// 방향 우선 순위
		int[] up;
		int[] down;
		int[] left;
		int[] right;

		Shark(int n, int r, int c) {
			this.n = n;
			this.r = r;
			this.c = c;
		}

		public void setD(int d) {
			this.d = d;
		}

		public void setUp(int[] arr) {
			this.up = arr;
		}

		public void setDown(int[] arr) {
			this.down = arr;
		}

		public void setLeft(int[] arr) {
			this.left = arr;
		}

		public void setRight(int[] arr) {
			this.right = arr;
		}

		public void setPosition(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	// 위, 아래, 왼쪽, 오른쪽
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Shark[] sharks = new Shark[M + 1]; // 상어 정보 저장
		boolean[] isOut = new boolean[M + 1]; // 쫓겨난 상어 정보 저장 (쫓겨났으면 true)

		Queue<ArrayList<int[]>> smellQueue = new LinkedList<>(); // 남아있는 냄새 위치 저장 큐
		int[][] smell = new int[N][N]; // 냄새 주인 저장
		int[][] leftTime = new int[N][N]; // 남아있는 시간 저장 (과거 냄새, 최신 냄새 중복 고려)

		int[][] map = new int[N][N]; // 격자판

		ArrayList<int[]> al = new ArrayList<>();
		for (int i = 0; i < N; i++) { // 입력값 (격자판)
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					sharks[map[i][j]] = new Shark(map[i][j], i, j);
					al.add(new int[] {i, j});
					smell[i][j] = map[i][j];
					leftTime[i][j] = K;
				}
			}
		}
		smellQueue.offer(al); // 냄새 업데이트

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) { // 입력값 (방향)
			int d = Integer.parseInt(st.nextToken());
			sharks[i].setD(d - 1);
		}

		for (int i = 1; i <= M; i++) { // 입력값 (우선순위 방향)
			for (int j = 0; j < 4; j++) { // 위, 아래, 왼, 오
				st = new StringTokenizer(br.readLine());
				int[] arr = new int[4];
				for (int u = 0; u < 4; u++) {
					arr[u] = Integer.parseInt(st.nextToken()) - 1;
				}
				if (j == 0) sharks[i].setUp(arr);
				else if (j == 1) sharks[i].setDown(arr);
				else if (j == 2) sharks[i].setLeft(arr);
				else sharks[i].setRight(arr);
			}
		}

		int time = 0; // 소요 시간

		while (!isEnd(isOut)) {
			if (time > 1000) break; // 1000초 넘어가면 중지

			// 이동
			for (int i = 1; i <= M; i++) {
				if (isOut[i]) continue; // 쫓겨났으면 pass

				Shark s = sharks[i];
				map[s.r][s.c] = 0; // 이동하기 전에 비워주기

				int d = getDirection(map, smell, s); // 움직일 방향 구하기
				int nx = s.r + dx[d];
				int ny = s.c + dy[d];

				if (map[nx][ny] == 0 || map[nx][ny] > s.n) { // 아무도 없거나, 이미 있는 상어보다 번호가 작으면
					map[nx][ny] = s.n; // 격자판 업데이트
					s.setPosition(nx, ny); // 상어 위치 정보 업데이트
					s.setD(d); // 상어 방향 정보 업데이트
				} else {
					isOut[s.n] = true; // 쫓겨남
				}

			}

			// 냄새 지우기
			if (!smellQueue.isEmpty() && smellQueue.size() >= K) { // 냄새가 쌓인지 K 시간동안 지났으면
				al = smellQueue.poll(); // K 시간 전에 남겨진 냄새
				for (int[] arr : al) {
					leftTime[arr[0]][arr[1]] -= K; // K 시간만큼 빼기 (중복 고려)
					if (leftTime[arr[0]][arr[1]] <= 0) smell[arr[0]][arr[1]] = 0; // 과거 이후에 남겨진 흔적 없으면 0 초기화
				}
			}

			// 냄새 업데이트
			al = new ArrayList<>();
			for (int i = 1; i <= M; i++) {
				if (isOut[i]) continue;
				Shark s = sharks[i];
				al.add(new int[] {s.r, s.c}); // 직전 이동으로 쌓인 냄새
				smell[s.r][s.c] = s.n; // 냄새 주인 저장
				leftTime[s.r][s.c] += K; // 냄새 남은 시간 더하기
			}
			smellQueue.offer(al); // 냄새 큐에 저장

			time++;
		}

		System.out.println(time <= 1000 ? time : -1);

	}

	private static int getDirection(int[][] map, int[][] smell, Shark s) {
		int[] directionList = getDirectionList(s);
		int me = -1;

		// 1. 아무 냄새도 없는 곳
		for (int i : directionList) {
			int tx = s.r + dx[i], ty = s.c + dy[i];
			if (0 <= tx && tx < map.length && 0 <= ty && ty < map.length) {
				if (smell[tx][ty] != 0) {
					if (me == -1 && smell[tx][ty] == s.n) {
						me = i;
					}
					continue;
				}
				return i;
			}
		}

		// 2. 자기 냄새
		return me;
	}

	private static int[] getDirectionList(Shark s) {
		if (s.d == 0) { // 위
			return s.up;
		} else if (s.d == 1) { // 아래
			return s.down;
		} else if (s.d == 2) { // 왼쪽
			return s.left;
		} else { // 오른쪽
			return s.right;
		}
	}

	private static boolean isEnd(boolean[] isOut) {
		if (isOut.length <= 1) return true; // 상어가 없으면 종료
		for (int i = 2; i < isOut.length; i++) { // 1이 아닌 남아있는 상어가 있는지 확인
			if (!isOut[i]) return false;
		}
		return true;
	}

}
