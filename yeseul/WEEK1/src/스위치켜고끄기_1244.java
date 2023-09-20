import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스위치켜고끄기_1244 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] switches = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int student = Integer.parseInt(br.readLine());

        for (int i = 0; i < student; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int key = Integer.parseInt(st.nextToken());

            // 남자
            if (gender == 1) {
                for (int j = key - 1; j < N; j += key) {
                    switches[j] = 1 - switches[j];
                }
            }
            // 여자
            else if (gender == 2) {
                int left = key - 1;
                int right = key - 1;
                while (left >= 0 && right < N && switches[left] == switches[right]) {
                    switches[left] = 1 - switches[left];
                    if (left != right)
                        switches[right] = 1 - switches[right];
                    left--;
                    right++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.print(switches[i] + " ");
            if ((i + 1) % 20 == 0)
                System.out.println();
        }
    }
}
