package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 추월_2002 {	static String title;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(br.readLine());
		Queue<String> in = new LinkedList<>();
		Queue<String> out = new LinkedList<>();

		// 들어간 차
		for (int i = 0; i < N; i++) {
			in.add(br.readLine());
		}

		// 나온 차
		for (int i = 0; i < N; i++) {
			out.add(br.readLine());
		}

		int answer = 0;
		while (!out.isEmpty()) {
			String car = out.poll();
			if (!in.peek().equals(car)) { // 추월
				answer++;
				in.remove(car);
			} else {
				in.poll();
			}
		}

		System.out.println(answer);
	}
}
