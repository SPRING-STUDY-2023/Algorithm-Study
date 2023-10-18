package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 파티_1238 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		List<List<int[]>> al = new ArrayList<>();
		for (int i = 0; i <= N; i++) al.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			al.get(s).add(new int[] {e, t});
		}

		int[][] dist = new int[N + 1][N + 1];
		for (int[] arr : dist) Arrays.fill(arr, Integer.MAX_VALUE);
		for (int i = 0; i <= N; i++) dist[i][i] = 0;

		for (int i = 1; i <= N; i++) {
			dijkstra(al, dist[i], i);
		}

		int max = Integer.MIN_VALUE;

		for (int i = 1; i <= N; i++) {
			int cost = dist[i][X] + dist[X][i];
			max = Math.max(max, cost);
		}

		System.out.println(max);

	}

	private static void dijkstra(List<List<int[]>> al, int[] dist, int x) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(x);

		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int[] next : al.get(now)) {
				if (dist[next[0]] > dist[now] + next[1]) {
					dist[next[0]] = dist[now] + next[1];
					queue.add(next[0]);
				}
			}
		}
	}
}
