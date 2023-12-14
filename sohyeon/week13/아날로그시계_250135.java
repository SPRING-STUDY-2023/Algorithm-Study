package week13;

import java.util.List;

public class 아날로그시계_250135 {
	class Time {
		int h, m, s;

		Time(int h, int m, int s) {
			this.h = h;
			this.m = m;
			this.s = s;
		}

		Time(int seconds) {
			this.h = seconds / 3600;
			this.m = (seconds % 3600) / 60;
			this.s = (seconds % 3600) % 60;
		}

		int toSeconds() {
			return h * 3600 + m * 60 + s;
		}

		List<Double> getDegree() {
			Double hDegree = (h % 12) * 30d + m * 0.5d + s * (1/120d);
			Double mDegree = m * 6d + s * (0.1d);
			Double sDegree = s * 6d;
			return List.of(hDegree, mDegree, sDegree);
		}
	}

	public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
		int answer = 0;

		int start = new Time(h1, m1, s1).toSeconds();
		int end = new Time(h2, m2, s2).toSeconds();

		for (int i = start; i < end; i++) { // 현재 i초
			List<Double> cnt = new Time(i).getDegree();
			List<Double> next = new Time(i + 1).getDegree();

			boolean hMatch = hourMatch(cnt, next);
			boolean mMatch = minuteMatch(cnt, next);

			if (hMatch && mMatch) { // 시침, 분침 겹침
				// 시침, 분침의 각도가 같으면 +1, 아니면 +2
				if (Double.compare(next.get(0), next.get(1)) == 0) answer++;
				else answer += 2;
			} else if (hMatch || mMatch) {
				answer++;
			}
		}

		// 시작이 0시 또는 12시이면, 한 번 겹치고 시작하므로 +1
		if (start == 0 || start == 43200) answer++;

		return answer;
	}

	boolean hourMatch(List<Double> cnt, List<Double> next) {
		if (Double.compare(cnt.get(0), cnt.get(2)) > 0 && Double.compare(next.get(0), next.get(2)) < 0) {
			return true;
		}

		/**
		 위 초침을 구하는 계산 공식대로라면 0분 59초에서 1분으로 갈 때,
		 354 -> 360도가 되는 것이 아니라, 354 -> 0도가 된다.

		 이렇게 되면 각도의 크기를 통해 겹침을 판단하는 로직을 사용할 수 없다.
		 그래서 이 경우는 예외로 따로 처리해 주는 것이 좋다.
		 */

		// 초침이 354도 > 0도 넘어갈 때의 경우
		if (Double.compare(cnt.get(2), 354d) == 0 && Double.compare(cnt.get(0), 354d) > 0) {
			return true;
		}

		return false;
	}

	boolean minuteMatch(List<Double> cnt, List<Double> next) {
		if(Double.compare(cnt.get(1),cnt.get(2)) > 0 && Double.compare(next.get(1),next.get(2)) <= 0) {
			return true;
		}

		// 초침이 354도 > 0도로 넘어갈 때의 경우
		if(Double.compare(cnt.get(2),354d) == 0 && Double.compare(cnt.get(1),354d) > 0){
			return true;
		}
		return false;
	}
}