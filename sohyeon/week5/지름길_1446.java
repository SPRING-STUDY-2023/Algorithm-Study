package week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 지름길_1446 {

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
		StringTokenizer st = new StringTokenizer(br.readLine());

		List<Road> al = new ArrayList<>();

		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if (s > e || (e - s) <= c) continue;
			al.add(new Road(s, e, c));
		}

		Collections.sort(al);

		int[] distance = new int[10001];
		Arrays.fill(distance, 10001);
		distance[0] = 0;

		int idx = 0, move = 0;
		while (move < D) {
			if (idx < al.size()) {
				Road r = al.get(idx);
				if (move == r.s) {
					distance[r.e] = Math.min(distance[r.e], distance[move] + r.c);
					idx++;
					continue;
				}
			}
			distance[move + 1] = Math.min(distance[move + 1], distance[move] + 1);
			move++;
		}

		System.out.println(distance[D]);

	}
}
