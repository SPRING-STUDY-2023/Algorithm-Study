package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스위치켜고끄기_1244 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] switches = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int gen = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if (gen == 1) { // 남학생
				for (int j = num; j <= N; j += num) {
					switches[j - 1] = switches[j - 1] == 0 ? 1 : 0;
				}
			} else if (gen == 2) { // 여학생
				int j = 1;
				switches[num - 1] = switches[num - 1] == 0 ? 1 : 0;
				while (num + j <= N && num - j > 0) {
					if (switches[num + j - 1] != switches[num - j - 1]) {
						break;
					}
					switches[num + j - 1] = switches[num + j - 1] == 0 ? 1 : 0;
					switches[num - j - 1] = switches[num - j - 1] == 0 ? 1 : 0;
					j++;
				}
			}
		}

		for (int i = 0; i < switches.length; i++) {
			System.out.print(switches[i] + " ");
			if ((i + 1) % 20 == 0) {
				System.out.println();
			}
		}

	}

}
