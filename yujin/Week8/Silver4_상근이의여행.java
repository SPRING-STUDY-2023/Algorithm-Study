package Week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Silver4_상근이의여행 {
    static boolean[] visited;
    static int[][] graph;
    static int n,m;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 변수 입력

        int t = Integer.parseInt(br.readLine());
        while(t-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            graph = new int[n + 1][n + 1];
            visited = new boolean[n + 1];

            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a][b] = 1; graph[b][a] = 1;
            }

            int result = bfs();
            System.out.println(result);
        }
    }
    static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;
        int count = 0;

        while(!q.isEmpty()) {
            int prev = q.poll();
            for(int i = 2; i <= n; i++) {
                if(graph[prev][i] == 1 && !visited[i]) {
                    count++;
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
        return count;
    }
}
