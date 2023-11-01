package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 거스름돈_5585 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(br.readLine());
		int[] arr = {500, 100, 50, 10, 5, 1};

		int money = 1000 - n;
		int answer = 0;
		for (int coin : arr) {
			int temp = money / coin;
			// System.out.println(coin + " " + temp + " " + n);
			answer += temp;
			money -= temp * coin;
		}

		System.out.println(answer);
	}
}
