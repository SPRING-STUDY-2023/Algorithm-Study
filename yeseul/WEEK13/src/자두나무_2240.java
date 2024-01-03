import java.util.Scanner;

public class 자두나무_2240 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T, W; //자두가 떨어지는 초수, 움직이는 횟수
        T = sc.nextInt();
        W = sc.nextInt();

        int[][][] DP = new int[31][2][1001]; //DP[이동 횟수][자두의 현재 위치][흐른 시간]
        int[] dropTree = new int[1001]; //자두가 떨어지는 나무 번호

        for (int i = 1; i <= T; i++) {
            dropTree[i] = sc.nextInt();
        }

        //배열 초기화
        for (int j = 0; j <= W; j++) {
            for (int i = 0; i < 2; i++) {
                for (int k = 1; k <= T; k++) {
                    DP[j][i][k] = -1;
                }
            }
        }

        // DP 배열 만들기
        for (int i = 0; i <= W; i++) { //움직이는 횟수
            for (int j = 1; j <= T; j++) { //흐르는 초수

                if (i == 0) { //처음 시작은 1번 나무, 즉 1번 위치의 값만 채우면 됨.
                    DP[i][0][j] = DP[i][0][j - 1] + (dropTree[j] == 1 ? 1 : 0);
                } else {
                    // j초 기준으로 위치가 1번 나무일 때
                    DP[i][0][j] = Math.max(DP[i][0][j - 1] + (dropTree[j] == 1 ? 1 : 0), DP[i - 1][1][j - 1] + (dropTree[j] == 1 ? 1 : 0));
                    // j초 기준으로 위치가 2번 나무일 때
                    DP[i][1][j] = Math.max(DP[i][1][j - 1] + (dropTree[j] == 2 ? 1 : 0), DP[i - 1][0][j - 1] + (dropTree[j] == 2 ? 1 : 0));

                    // max (이동했고 현재 자두가 떨어지는 나무에 있는가?, 이동하지 않았고 현재 자두가 떨어지는 나무에 있는가?)
                    // 이 때 j-1 때의 DP 값을 봐야함. (1초 전의 상황)
                }
            }
        }

        // 최댓값 찾기
        int result = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j <= W; j++) {
                result = Math.max(result, DP[j][i][T]);
            }
        }

        System.out.println(result);
    }
}
