package week15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class 개인정보수집유효기간_150370 {
	public int[] solution(String today, String[] terms, String[] privacies) {
		int day = getDay(today);

		Map<String, Integer> map = new HashMap<>();
		for (String term : terms) {
			String[] arr = term.split(" ");
			map.put(arr[0], Integer.valueOf(arr[1]));
		}

		ArrayList<Integer> al = new ArrayList<>();

		for (int i = 0; i < privacies.length; i++) {
			String privacy = privacies[i];
			String[] arr = privacy.split(" ");
			if (day >= getDay(arr[0]) + map.get(arr[1]) * 28) {
				al.add(i + 1);
			}
		}

		int[] answer = new int[al.size()];
		for (int i = 0; i < al.size(); i++) {
			answer[i] = al.get(i);
		}
		return answer;
	}

	private int getDay(String today) {
		String[] arr = today.split("\\.");
		int y = Integer.valueOf(arr[0]);
		int m = Integer.valueOf(arr[1]);
		int d = Integer.valueOf(arr[2]);
		return (y * 12 * 28) + (m * 28) + d;
	}

	private void print(Object obj) {
		System.out.println(String.valueOf(obj));
	}
}