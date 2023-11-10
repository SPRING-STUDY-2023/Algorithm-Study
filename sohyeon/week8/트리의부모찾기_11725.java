package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 트리의부모찾기_11725 {
	static List<List<Integer>> graph;
	static boolean[] visited;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(br.readLine());

		visited = new boolean[N + 1];
		parent = new int[N + 1];
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		bfs();

		for (int i = 2; i <= N; i++) {
			System.out.println(parent[i]);
		}
	}

	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);

		visited[1] = true;

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (int next : graph.get(cur)) {
				if (visited[next]) {
					continue;
				}
				visited[next] = true;
				parent[next] = cur;
				queue.add(next);
			}
		}
	}
}
