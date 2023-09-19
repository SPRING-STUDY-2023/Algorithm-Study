package Week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver4_스위치켜고끄기 {
    // 남 : 스위치 번호가 자기의 받은 배수이면, 스위치 상태를 바꾼다
    // 여 : 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아서, 그 구간에 속한 스위치의 상태를 모두 ㅏㅂ꾼다.
    // 구간에 속한 스위치는 항상 홀수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] switches = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }
        int student = Integer.parseInt(br.readLine());
        for (int s = 0; s < student; s++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if (gender == 1) { // 남성
                for (int i = 0; i < switches.length; i++) {
                    if ((i + 1) % num == 0) switches[i] = switches[i] == 0 ? 1 : 0;
                }
            } else {
                int rangeLeft = num - 1;
                int rangeRight = num - 1;
                while (true) {
                    if(rangeLeft < 0 || rangeRight >= switches.length || switches[rangeLeft] != switches[rangeRight]) {
                        rangeLeft++; rangeRight--;
                        break;
                    }
                    rangeLeft--; rangeRight++;
                }
                for (int i = rangeLeft; i <= rangeRight; i++) {
                    switches[i] = switches[i] == 0 ? 1 : 0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(switches[i] + " ");
            if ((i + 1) % 20 == 0)
                System.out.println();
        }
    }
}
