package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 줄세우기_10431 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int cnt = 0;
			List<Integer> al = new ArrayList<>();
			for (int i = 0; i < 20; i++) {
				int h = Integer.parseInt(st.nextToken());
				if (al.isEmpty()) {
					al.add(h);
				} else {
					if (h > al.get(al.size() - 1)) {
						al.add(h);
						continue;
					} else if (h < al.get(0)) {
						al.add(0, h);
						cnt += al.size() - 1;
						continue;
					}
					for (int j = al.size() - 2; j >= 0; j--) {
						if (h > al.get(j)) {
							al.add(j + 1, h);
							cnt += al.size() - j - 2;
							break;
						}
					}
				}
			}
			System.out.println(P + " " + cnt);
		}
	}

}
