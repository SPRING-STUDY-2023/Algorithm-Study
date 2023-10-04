mport java.io.*;
import java.util.*;

public class 컴백홈_1189 {
    static int ans,r,c,k;
    static boolean[][] map;
    static int [] dx= {1,-1,0,0};
    static int [] dy= {0,0,1,-1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new boolean [r][c];

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++)
                if (str.charAt(j) == 'T')	// T로 막힌 부분은 탐색시 이미 방문했다고 처리하면 그 위치는 탐색하지 않음
                    map[i][j] = true;
        }
        map[r - 1][0] = true;
        DFS(r-1,0,1);
        System.out.println(ans);
    }
    public static void DFS(int x,int y, int d) {
        if (d >= k) {	// 방문한 수가 이미 D보다 크면 추가 탐색하지 않음
            if (x==0 && y==c-1 && d==k)	// 도착 위치이면서 방문횟수가 K랑 같을때만 경로수 추가
                ans++;
            return;	// 추가 탐색 X
        }
        for (int i=0; i < 4; i++) {
            if (x + dx[i] >= 0 && x + dx[i] < r && y + dy[i] >=0 && y + dy[i] < c) {
                if(!map[x+dx[i]][y+dy[i]]) {
                    map[x+dx[i]][y+dy[i]]=true;
                    DFS(map, x+dx[i], y+dy[i], d+1);
                    map[x+dx[i]][y+dy[i]]=false;
                }
            }
        }
    }
}