package week12;

import java.util.ArrayList;
import java.util.List;

public class 네트워크_43162 {
	boolean[] visited;
	List<List<Integer>> graph = new ArrayList<>();

	public int solution(int n, int[][] computers) {
		initGraph(computers, n);
		visited = new boolean[n];
		int answer = 0;

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(computers, n, i);
				answer++;
			}
		}

		return answer;
	}

	private void dfs(int[][] computers, int n, int i) {
		for (int next : graph.get(i)) {
			if (!visited[next]) {
				visited[next] = true;
				dfs(computers, n, next);
			}
		}
	}

	private void initGraph(int[][] computers, int n) {
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (computers[i][j] == 1) {
					graph.get(i).add(j);
				}
			}
		}
	}
}
