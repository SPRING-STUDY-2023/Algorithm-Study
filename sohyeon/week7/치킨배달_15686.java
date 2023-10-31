package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 치킨배달_15686 {
	static boolean[] open;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][N];
		List<int[]> houseList = new ArrayList<>();
		List<int[]> chickenList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					houseList.add(new int[] {i, j});
				} else if (arr[i][j] == 2) {
					chickenList.add(new int[] {i, j});
				}
			}
		}

		open = new boolean[chickenList.size()];
		dfs(0, 0, M, houseList, chickenList);

		System.out.println(answer);
	}

	private static void dfs(int start, int cnt, int M, List<int[]> houseList, List<int[]> chickenList) {
		if (cnt == M) {
			int res = 0;

			for (int i = 0; i < houseList.size(); i++) {
				int temp = Integer.MAX_VALUE;
				for (int j = 0; j < chickenList.size(); j++) {
					if (open[j]) {
						int[] house = houseList.get(i);
						int[] chicken = chickenList.get(j);
						int distance = Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]);
						temp = Math.min(temp, distance);
					}
				}
				res += temp;
			}

			answer = Math.min(answer, res);
			return;
		}

		for (int i = start; i < chickenList.size(); i++) {
			open[i] = true;
			dfs(i + 1, cnt + 1, M, houseList, chickenList);
			open[i] = false;
		}
	}
}
