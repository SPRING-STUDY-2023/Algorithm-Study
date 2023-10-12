package Week4_이진탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Gold4_공유기설치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] house = new int[n];

        for(int i = 0; i < n; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);

        int left = 1;
        int right = house[n - 1] - house[0] + 1; // 찾고자 하는 값을 초과하는 첫번째 인덱스
        while(left < right) {
            int mid = (left + right) / 2;
            int result = count(mid, house);
            if (result < c) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left - 1);
    }
    public static int count(int gap, int[] house) {
        int cnt = 1;
        int lastLoacte = house[0];

        for(int i = 1; i < house.length; i++) {
            int locate = house[i];
            if(locate - lastLoacte >= gap) {
                cnt++;
                lastLoacte = house[i];
            }
        }
        return cnt;
    }
}
