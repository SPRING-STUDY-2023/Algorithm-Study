package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 블로그_21921 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 지난 일 수
		int X = Integer.parseInt(st.nextToken()); // 방문자 수를 확인하는 일 수

		int[] arr = new int[N + 1];
		int[] ps = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
		}

		for (int i = X; i <= N; i++) {
			ps[i] = arr[i] - arr[i - X];
		}

		int max = 0;
		int cnt = 0;
		for (int i = X; i < ps.length; i++) {
			if (ps[i] > max) {
				cnt = 1;
				max = ps[i];
			} else if (ps[i] == max) {
				cnt++;
			}
		}

		if (max != 0) {
			System.out.println(max);
			System.out.println(cnt);
		} else {
			System.out.println("SAD");
		}

	}
}
