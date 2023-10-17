import java.util.*;
import java.io.*;

public class 공유기설치_2110 {
    static int listSize;

    static int binarySearch(int[] list, int listSize, int low, int high, int c) {
        int res=0;

        while (low <= high) {
            int mid = (low+high)/2;
            int cnt = 1;
            int start = list[0];

            for(int i=0; i<listSize; i++) {
                int dist = list[i]-start;

                if(mid<=dist)
                {
                    cnt++;
                    start = list[i];
                }
            } //for문 이용해서 가능한 곳에 공유기 설치 (기준 mid보다 거리가 크거나 같으면 공유기 설치)

            if(cnt<c) {
                high = mid-1;
            } //설치한 공유기 개수가 적을 때-> 거리 좁히기

            else{
                low = mid+1;
                res = mid;
            } //설치한 공유 개수가 클 때-> 거리 늘리기, 설치한 공유기 개수 같아도 더 큰 최대 거리가 있을 수 있으니까 탐색

        }

        return res;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] house = new int[N];

        for(int i=0; i<N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(house);

        int result = binarySearch(house,N,1,house[N-1]-house[0],C); // low=1(집 좌표 차이는 최소 1), high는 끝 값의 차이

        System.out.print(result);
    }

}
