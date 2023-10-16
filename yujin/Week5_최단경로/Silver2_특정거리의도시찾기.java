package Week5_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Silver2_특정거리의도시찾기 {
    static int[] distance;
    static int n, m,k,x;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 도시의 개수
        m = Integer.parseInt(st.nextToken()); // 도로의 개수
        k = Integer.parseInt(st.nextToken()); // 거리 정보
        x = Integer.parseInt(st.nextToken()); // 출발 도시의 번호

        list = new ArrayList[n + 1];
        distance = new int[n + 1];
        Arrays.fill(distance, -1);
        for (int i = 0; i <= n; i++) {
            list[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list[s].add(e);
        }

        bfs(x);

        ArrayList<Integer> answer = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            if(distance[i] == k) answer.add(i);
        }
        if(answer.isEmpty()) System.out.println("-1");
        else {
            Collections.sort(answer);
            for(int a : answer) {
                System.out.println(a);
            }
        }
    }
    private static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        distance[node]++;

        while(!queue.isEmpty()) {
            int now = queue.poll();
            for(int next : list[now]) {
                if(distance[next] == -1) {
                    distance[next] = distance[now] + 1;
                    queue.add(next);
                }
            }
        }
    }
}
