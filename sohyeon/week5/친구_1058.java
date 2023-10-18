package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 친구_1058 {

	static class Road implements Comparable<Road> {
		int s;
		int e;
		int c;

		Road(int s, int e, int c) {
			this.s = s;
			this.e = e;
			this.c = c;
		}

		@Override
		public int compareTo(Road o) {
			if (this.s == o.s) {
				return this.e - o.e;
			}
			return this.s - o.s;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		List<List<Integer>> al = new ArrayList<>();
		for (int i = 0; i < N; i++) al.add(new ArrayList<>());

		for (int i = 0; i < N; i++) {
			String[] arr = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				if (arr[j].equals("Y")) al.get(i).add(j);
			}
		}

		int[][] dist = new int[N][N];
		for (int[] arr : dist) Arrays.fill(arr, Integer.MAX_VALUE);

		for (int i = 0; i < N; i++) {
			dijkstra(al, dist[i], i);
		}

		int max = 0;

		for (int[] arr : dist) {
			int cnt = 0;
			for (int r : arr) {
				if (r != 0 && r <= 2) cnt++;
			}
			max = Math.max(max, cnt);
		}

		System.out.println(max);


	}

	private static void dijkstra(List<List<Integer>> al, int[] dist, int p) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(p);
		dist[p] = 0;

		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int next : al.get(now)) {
				if (dist[next] > dist[now] + 1) {
					dist[next] = dist[now] + 1;
					queue.add(next);
				}
			}
		}
	}
}
