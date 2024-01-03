package week15;

import java.util.HashMap;
import java.util.Map;

public class 대충만든자판_160586 {
	public int[] solution(String[] keymap, String[] targets) {
		Map<Character, Integer> map = new HashMap<>();

		for (String key : keymap) {
			for (int i = 0; i < key.length(); i++) {
				Character c = key.charAt(i);
				map.put(c, Math.min(map.getOrDefault(c, i + 1), i + 1));
			}
		}

		int[] answer = new int[targets.length];
		for (int i = 0; i < targets.length; i++) {
			String target = targets[i];
			for (int j = 0; j < target.length(); j++) {
				Character c = target.charAt(j);
				if (!map.containsKey(c)) {
					answer[i] = -1;
					break;
				}
				answer[i] += map.get(c);
			}
		}

		return answer;
	}
}