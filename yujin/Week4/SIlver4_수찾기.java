package Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SIlver4_수찾기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] array = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            if(binarySearch(Integer.parseInt(st.nextToken()), array)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }

    }
    public static boolean binarySearch(int n, int[] arr) {
        int l = 0, r = arr.length - 1;

        while(l < r) {
            int mid = l + r / 2;
            if(arr[mid] == n) {
                return true;
            } else if(arr[mid] > n) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }
}
