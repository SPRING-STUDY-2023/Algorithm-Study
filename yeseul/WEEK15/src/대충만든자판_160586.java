import java.util.HashMap;

public class 대충만든자판_160586 {
  public int[] solution(String[] keymap, String[] targets) {
    HashMap<Character, Integer> map = new HashMap<>(); // 알파벳이 몇번째에 있는지 저장하는 맵
    int[] result = new int[targets.length];

    for (String s: keymap) {
      for (int i = 0; i < s.length(); i++) {
        char key = s.charAt(i);
        map.put(key, Math.min(i + 1, map.getOrDefault(key, Integer.MAX_VALUE))); // 몇번 눌러야 해당 알파벳이 나올 수 있는지
      }
    }

    for (int i=0 ; i < targets.length; i++) {
      int cnt = 0;
      for(int j=0 ; j < targets[i].length(); j++) {
        char c = targets[i].charAt(j);
        if (!map.containsKey(c)) {
          cnt = 0;
          break;
        } else {
          cnt += map.get(c);
        }
      }
      result[i] = cnt == 0 ? -1 : cnt;
    }
    return result;
  }
}
