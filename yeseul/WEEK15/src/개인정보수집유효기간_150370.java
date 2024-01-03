import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class 개인정보수집유효기간_150370 {
  public int[] solution(String today, String[] terms, String[] privacies) {
    List<Integer> answer = new ArrayList<>();
    today = today.replace(".","");
    int year = Integer.parseInt(today.substring(0,4));
    int month = Integer.parseInt(today.substring(4,6));
    int day = Integer.parseInt(today.substring(6,8));

    int totalDays = (year * 12 * 28) + (month * 28) + day;

    HashMap<Character,Integer> termsMap = new HashMap<>();

    for(String term : terms){
      char termAlpha = term.charAt(0);
      int termMonth = Integer.parseInt(term.substring(2));
      termsMap.put(termAlpha, termMonth);
    }

    for(int i = 0; i < privacies.length; i++){
      char privacyAlpha = privacies[i].charAt(11);
      int privacyYear = Integer.parseInt(privacies[i].substring(0,4));
      int privacyMonth = Integer.parseInt(privacies[i].substring(5,7));
      int privacyDay = Integer.parseInt(privacies[i].substring(8,10));
      int totalPrivacyDays = (privacyYear * 12 * 28) + (privacyMonth * 28) + privacyDay;
      int termMonth = termsMap.get(privacyAlpha);
      int termDay = termMonth * 28;
      if(totalPrivacyDays + termDay <= totalDays){
        answer.add(i+1);
      }
    }

    return answer.stream().mapToInt(Integer::intValue).toArray();
  }

}
