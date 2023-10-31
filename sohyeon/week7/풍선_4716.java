package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 풍선_4716 {
	static class Team implements Comparable<Team> {
		int count;
		int distA;
		int distB;

		Team(int count, int distA, int distB) {
			this.count = count;
			this.distA = distA;
			this.distB = distB;
		}

		@Override
		public int compareTo(Team o) { // 극단적인 거리 차이를 가진 팀부터 풍선 달기
			return Math.abs(o.distA - o.distB) - Math.abs(this.distA - this.distB);
		}
	}

	static BufferedReader br;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 팀의 수
			int A = Integer.parseInt(st.nextToken()); // 풍선 개수
			int B = Integer.parseInt(st.nextToken()); // 풍선 개수

			if (N == 0 && A == 0 && B == 0) {
				break;
			}

			solve(N, A, B);
		}
	}

	private static void solve(int N, int A, int B) throws Exception {
		List<Team> al = new ArrayList<>(); // 팀 정보 리스트
		int answer = 0; // 최소 거리 합

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken()); // 달아줘야 하는 풍선 개수
			int DA = Integer.parseInt(st.nextToken()); // A방까지의 거리
			int DB = Integer.parseInt(st.nextToken()); // B방까지의 거리

			if (K == 0 && DA == 0 && DB == 0) {
				break;
			}

			al.add(new Team(K, DA, DB));
		}

		Collections.sort(al);

		for (int i = 0; i < N; i++) {
			Team team = al.get(i);

			if (team.distA < team.distB) { // A 거리가 더 가깝다.
				if (A >= team.count) {
					answer += team.count * team.distA;
					A -= team.count;
				} else {
					answer += A * team.distA;
					team.count -= A;
					A = 0;

					answer += team.count * team.distB;
					B -= team.count;
				}
			} else { // B 거리가 더 가깝다.
				if (B >= team.count) {
					answer += team.count * team.distB;
					B -= team.count;
				} else {
					answer += B * team.distB;
					team.count -= B;
					B = 0;

					answer += team.count * team.distA;
					A -= team.count;
				}
			}
		}

		System.out.println(answer);
	}
}
