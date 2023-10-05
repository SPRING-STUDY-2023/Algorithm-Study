package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 공유기설치_2110 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		int left = 1;
		int right = Math.abs(arr[0] - arr[N - 1]);
		int max = Integer.MIN_VALUE;

		while (left <= right) {
			int mid = (left + right) / 2;

			if (put(arr, mid, N, C)) {
				max = Math.max(max, mid);
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(max);
	}

	private static boolean put(int[] arr, int distance, int N, int C) {
		int start = arr[0];
		int cnt = 1;

		for (int i = 1; i < N; i++) {
			if (arr[i] >= start + distance) {
				cnt++;
				start = arr[i];
			}
		}

		return cnt >= C;
	}
}
