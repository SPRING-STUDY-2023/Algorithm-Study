package week10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 여행경로_43164 {
	List<String> routes = new ArrayList<>(); // 여행 경로 후보 리스트
	static boolean[] visited;

	public String[] solution(String[][] tickets) {
		visited = new boolean[tickets.length]; // 방문 배열 초기화
		dfs(tickets, "ICN", "ICN", 0);
		Collections.sort(routes); // 경로가 여러 개일 경우 알파벳 순서가 앞서는 경로 반환 필요
		return routes.get(0).split(" "); // "공항1 공항2 ..." => ["공항1", "공항2", ...]
	}

	private void dfs(String[][] tickets, String start, String route, int cnt) {
		if (cnt == tickets.length) { // 모든 도시 방문 완료
			routes.add(route);
			return;
		}

		for (int i = 0; i < tickets.length; i++) {
			if (visited[i] || !tickets[i][0].equals(start)) { // 방문한 도시이거나, 출발 공항이 일치하지 않거나
				continue;
			}
			visited[i] = true;
			dfs(tickets, tickets[i][1], route + " " + tickets[i][1], cnt + 1);
			visited[i] = false;
		}
	}
}
