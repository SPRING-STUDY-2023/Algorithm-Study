package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class 압축 {
    public int[] solution(String msg) {
        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0; i < 26; i++) {
            map.put((char)('A'+i)+"", i + 1);
        }

        char[] msgArr = msg.toCharArray();
        StringBuilder sb = new StringBuilder();
        int index = 1;

        ArrayList<Integer> answer = new ArrayList<>();
        while(index <= msg.length()) {
            sb = new StringBuilder(msgArr[--index]+"");
            while(++index < msgArr.length && map.containsKey(sb.toString())) {
                sb.append(msgArr[index]);
            }
            if(index == msgArr.length && map.containsKey(sb.toString())) break;
            map.put(sb.toString(), map.size() + 1);
            String string = sb.substring(0,sb.length() - 1);
            System.out.println(string);
            answer.add(map.get(string));
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        압축 압축 = new 압축();
        System.out.println(Arrays.toString(압축.solution("TOBEORNOTTOBEORTOBEORNOT")));
    }
}