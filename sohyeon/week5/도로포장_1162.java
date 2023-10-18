package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 도로포장_1162 {

	static class Edge implements Comparable<Edge> {
		int end;
		int count;
		long cost;

		Edge(int end, int count, long cost) {
			this.end = end;
			this.count = count;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		List<List<Edge>> edges = new ArrayList<>();
		long[][] dp = new long[N + 1][K + 1];

		for (int i = 0; i <= N; i++) {
			edges.add(new ArrayList<>());
			Arrays.fill(dp[i], Long.MAX_VALUE);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			edges.get(v1).add(new Edge(v2, 0, c));
			edges.get(v2).add(new Edge(v1, 0, c));
		}

		// dijkstra
		Queue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(1, 0, 0));
		dp[1][1] = 0;

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (dp[now.end][now.count] < now.cost) continue;
			for (Edge next : edges.get(now.end)) {
				if (dp[next.end][now.count] > now.cost + next.cost) {
					dp[next.end][now.count] = now.cost + next.cost;
					pq.add(new Edge(next.end, now.count, dp[next.end][now.count]));
				}

				if (now.count < K && dp[next.end][now.count + 1] > now.cost) {
					dp[next.end][now.count + 1] = now.cost;
					pq.add(new Edge(next.end, now.count + 1, now.cost));
				}
			}
		}

		long min = Long.MAX_VALUE;

		for (long cost : dp[N]) {
			min = Math.min(min, cost);
		}

		System.out.println(min);

	}
}
