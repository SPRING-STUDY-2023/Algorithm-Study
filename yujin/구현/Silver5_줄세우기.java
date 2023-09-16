package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Silver5_줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int p = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(p-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            sb.append(num).append(" ");
            int[] array = new int[20];

            for(int i = 0; i < 20; i++) {
                array[i] = Integer.parseInt(st.nextToken());
            }

            int cnt = 0;
            for(int i = 0; i < 20; i++) {
                for(int j = 0; j < i; j++) {
                    if(array[i] < array[j]) {
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}
