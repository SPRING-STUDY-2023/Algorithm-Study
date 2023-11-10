package week9;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 인간컴퓨터상호작용_16139 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		String str = br.readLine();
		int T = Integer.parseInt(br.readLine());

		int[][] arr = new int[str.length() + 1]['z' - 'a' + 1]; // 누적합 배열

		// 누적합 배열 초기화
		arr[1][str.charAt(0) - 'a']++; // 처음 값
		for (int i = 2; i <= str.length(); i++) {
			int idx = str.charAt(i - 1) - 'a'; // 탐색 중인 문자
			for (int j = 0; j < 'z' - 'a' + 1; j++) {
				int before = arr[i - 1][j]; // 이전 누적합
				arr[i][j] = (j == idx) ? before + 1 : before;
			}
		}

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			int l = Integer.parseInt(st.nextToken()) + 1;
			int r = Integer.parseInt(st.nextToken()) + 1;
			sb.append(arr[r][c - 'a'] - arr[l - 1][c - 'a']).append("\n");
		}

		System.out.println(sb);
	}
}
