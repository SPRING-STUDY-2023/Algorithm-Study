package Week7_greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2_거스름돈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = 1000-Integer.parseInt(br.readLine());

        int[] coin = new int[]{500,100,50,10,5,1};
        int answer = 0;
        for(int c : coin) {
            answer += n / c;
            n %= c;
        }
        System.out.println(answer);
    }
}
