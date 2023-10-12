import java.util.*;
import java.io.*;

public class 랜선자르기_1654 {
    static int listSize;

    static long binarySearch(int[] list, int listSize, int num) {
        long low = 1;
        long high = list[listSize - 1];
        long res = 0;

        while (low <= high) {
            long mid = (low + high) / 2;
            long cnt = 0;

            for (int i = 0; i < listSize; i++) {
                cnt += list[i] / mid;
            }

            if (num <= cnt) {
                low = mid + 1;
                res = mid;
            }

            else
                high = mid - 1;
        }

        return res;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] line = new int[K];

        for (int i = 0; i < K; i++) {
            line[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(line);

        long result = binarySearch(line, K, N);

        System.out.print(result);
    }

}
