package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class 특정거리의도시찾기_18352 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 도시 개수
		int M = Integer.parseInt(st.nextToken()); // 도로 개수
		int K = Integer.parseInt(st.nextToken()); // 거리 정보
		int X = Integer.parseInt(st.nextToken()); // 출발 도시 정보

		List<List<Integer>> al = new ArrayList<>();
		for (int i = 0; i <= N; i++) al.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// A -> B : 단방향 도로
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			al.get(A).add(B);
		}

		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[X] = 0;

		Queue<Integer> queue = new LinkedList<>();
		queue.add(X);

		while (!queue.isEmpty()) {
			int now = queue.poll();
			for (int next : al.get(now)) {
				if (dist[next] > dist[now] + 1) {
					dist[next] = dist[now] + 1;
					queue.add(next);
				}
			}
		}

		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (dist[i] == K) {
				System.out.println(i);
				cnt++;
			}
		}

		if (cnt == 0) {
			System.out.println(-1);
		}
	}
}
