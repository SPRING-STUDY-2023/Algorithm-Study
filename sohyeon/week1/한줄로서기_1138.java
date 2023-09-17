package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 한줄로서기_1138 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> al = new ArrayList<>();

		for (int i = N - 1; i >= 0; i--) { // 키 큰 사람부터
			int cnt = arr[i];
			if (cnt < al.size()) {
				al.add(cnt, i + 1);
			} else {
				al.add(i + 1);
			}
		}

		for (int num : al) {
			System.out.print(num + " ");
		}

	}

}
