package Week10;

import java.util.Stack;

public class LV2_괄호회전하기 {
    public int solution(String s) {
        int answer = -1;
        char[] arr = s.toCharArray();
        int count = 0;

        for(int i = 0; i < arr.length; i++) {
            count += check(i, arr);
        }
        return count;
    }

    public int check(int index, char[] arr){
        int count = 0;
        Stack<Character> stack = new Stack<>();
        for(int in = index; in < arr.length + index; in++) {
            char charr = arr[in % arr.length];
            if(charr == '[') stack.push(']');
            else if(charr == '(') stack.push(')');
            else if(charr == '{') stack.push('}');
            else { // 뒤집힌 문자면
                if(stack.isEmpty()) return 0;
                if(stack.peek() == charr) {
                    stack.pop();
                    continue;
                }
                return 0;
            }
        }
        if(!stack.isEmpty()) return 0;
        return 1;
    }
}
