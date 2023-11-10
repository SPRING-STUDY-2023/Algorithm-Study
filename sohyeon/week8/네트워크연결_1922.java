package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 네트워크연결_1922 {
	static class Edge implements Comparable<Edge> {
		int n1, n2, cost;

		Edge(int n1, int n2, int cost) {
			this.n1 = n1;
			this.n2 = n2;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(br.readLine()); // 컴퓨터 수
		int M = Integer.parseInt(br.readLine()); // 연결 선의 수

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		Queue<Edge> pq = new PriorityQueue<>();

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			pq.add(new Edge(n1, n2, cost));
		}

		int res = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (find(cur.n1) != find(cur.n2)) {
				union(cur.n1, cur.n2);
				res += cur.cost;
			}
		}

		System.out.println(res);
	}

	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	static void union(int n1, int n2) {
		int p1 = find(n1);
		int p2 = find(n2);

		if (p1 < p2) {
			parent[p2] = p1;
		} else {
			parent[p1] = p2;
		}
	}
}
