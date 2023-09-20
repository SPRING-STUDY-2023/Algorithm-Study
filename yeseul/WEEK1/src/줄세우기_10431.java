import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 줄세우기_10431 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());
        int[] arr = new int[20];
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int TC = 1; TC <= T; TC++) {
            st = new StringTokenizer(in.readLine()," ");
            sb.append(st.nextToken()).append(" ");

            int cnt = 0;
            for(int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < i; j++) {
                    if(arr[j] > arr[i]) {
                        cnt++;햐
                    }
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }
}
