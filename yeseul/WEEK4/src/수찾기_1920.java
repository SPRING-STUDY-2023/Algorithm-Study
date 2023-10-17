import java.util.*;
import java.io.*;

public class 수찾기_1920{

    public static boolean BinarySearch(int key, int list[], int listSize) {
        int start = 0;
        int end = listSize - 1;
        boolean result = false;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (key < list[mid])
                end = mid - 1;
            else if (key > list[mid])
                start = mid + 1;
            else {
                result = true;
                return result;
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        StringTokenizer st1 = new StringTokenizer(bf.readLine());

        int array1[] = new int[N];
        for (int i = 0; i < N; i++)
            array1[i] = Integer.parseInt(st1.nextToken());

        int M= Integer.parseInt(bf.readLine());
        StringTokenizer st2 = new StringTokenizer(bf.readLine());

        int array2[] = new int[M];
        for (int i = 0; i < M; i++)
            array2[i] = Integer.parseInt(st2.nextToken());

        // array1에서 탐색할 것 이므로 정렬하기
        Arrays.sort(array1);

        for (int i = 0; i < M; i++) {
            if (BinarySearch(array2[i], array1, N))
                System.out.println("1");
            else
                System.out.println("0");
        }
    }
}
