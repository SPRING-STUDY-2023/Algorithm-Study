package week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나무자르기_2805 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 나무 개수
		long M = Long.parseLong(st.nextToken()); // 가져가려고 하는 길이
		long[] arr = new long[N];
		long max = Integer.MIN_VALUE;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
			max = Math.max(max, arr[i]);
		}

		long left = 0;
		long right = max;
		long heightMax = Long.MIN_VALUE;

		while (left <= right) {
			long mid = (left + right) / 2;

			if (cutting(arr, mid, M)) {
				heightMax = Math.max(heightMax, mid);
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(heightMax);

	}

	private static boolean cutting(long[] arr, long height, long M) {
		long sum = 0;

		for (long num : arr) {
			if (num < height) continue;
			sum += num - height;
		}

		return sum >= M;
	}
}
