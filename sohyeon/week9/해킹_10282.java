package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 해킹_10282 {
	static class Edge implements Comparable<Edge> {
		int num;
		int cost;

		Edge(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge e) {
			return this.cost - e.cost;
		}

		@Override
		public String toString() {
			return "(" + this.num + " " + this.cost + ")";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			List<List<Edge>> al = new ArrayList<>();
			for (int i = 0; i <= n; i++)
				al.add(new ArrayList<>());

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				al.get(b).add(new Edge(a, s));
			}

			dijkstra(al, n, c);
		}

	}

	static void dijkstra(List<List<Edge>> al, int n, int c) {
		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[c] = 0;

		Queue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(c, 0));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (dist[cur.num] < cur.cost)
				continue;

			for (Edge next : al.get(cur.num)) {
				int cost = dist[cur.num] + next.cost;
				if (cost < dist[next.num]) {
					dist[next.num] = cost;
					pq.add(new Edge(next.num, cost));
				}
			}
		}

		int cnt = 0;
		int time = 0;
		for (int i = 1; i <= n; i++) {
			if (dist[i] != Integer.MAX_VALUE) {
				cnt++;
				time = Math.max(time, dist[i]);
			}
		}

		System.out.println(cnt + " " + time);
	}
}
