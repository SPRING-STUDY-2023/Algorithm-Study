package week14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 졸려_9519 {	static String title;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();

		StringBuilder head = new StringBuilder();
		StringBuilder tail = new StringBuilder();
		Map<String, Integer> map = new HashMap<>();
		List<String> al = new ArrayList<>();
		String tempStr = str;
		int temp = N;
		int cnt = 0;

		al.add(str);

		while (temp > 0) {
			// 초기화
			head.setLength(0);
			tail.setLength(0);

			for (int j = str.length() - (str.length() % 2 == 0 ? 1 : 2); j >= 0; j -= 2) {
				tail.append(tempStr.charAt(j));
			}

			for (int j = 0; j < str.length(); j+= 2) {
				head.append(tempStr.charAt(j));
			}

			head.append(tail);
			tempStr = head.toString();
			al.add(tempStr);

			if (!map.containsKey(tempStr)) {
				map.put(tempStr, cnt++);
			} else {
				break;
			}

			temp--;
		}

		if (temp > 0) { // 주기 발견
			temp = N % cnt;
			System.out.println(al.get(temp));
		} else {
			System.out.println(tempStr);
		}
	}
}
