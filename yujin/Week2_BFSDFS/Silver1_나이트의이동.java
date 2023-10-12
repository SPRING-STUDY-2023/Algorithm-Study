package Week2_BFSDFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Silver1_나이트의이동 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        StringTokenizer st;
        while(tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] knight = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
            st = new StringTokenizer(br.readLine());
            int[] destination = new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
            System.out.println(bfs(n, knight, destination));
        }
    }
    public static int bfs(int n, int[] knight, int[] destination) {
        int[][] visited = new int[n][n];
        int[] dx = { -1, -2, -2, -1, 1, 2, 2, 1 };
        int[] dy = { -2, -1, 1, 2, 2, 1, -1, -2 };
        for(int i = 0; i < n; i++) {
            Arrays.fill(visited[i],-1);
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {knight[0], knight[1]});
        visited[knight[1]][knight[0]] = 0;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            if(destination[0] == now[0] && destination[1] == now[1]) {
                return visited[destination[1]][destination[0]];
            }
            for(int i = 0; i < 8; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(visited[ny][nx] == -1 || visited[ny][nx] > visited[now[1]][now[0]] + 1) {
                    visited[ny][nx] = visited[now[1]][now[0]] + 1;
                    q.add(new int[]{nx,ny});
                }
            }
        }
        return -1;
    }
}
