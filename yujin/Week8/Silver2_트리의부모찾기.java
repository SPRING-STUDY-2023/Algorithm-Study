package Week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Silver2_트리의부모찾기 {
    static int[] parent;
    static boolean[] visited;
    static int n;
    static ArrayList<Integer>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        parent = new int[n + 1];

        for(int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }

        visited = new boolean[n + 1];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            list[x1].add(x2);
            list[x2].add(x1);
        }
        dfs(1);

        for(int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }
    }
    public static void dfs(int v) {
        visited[v] = true;
        for(int i : list[v]) {
            if(!visited[i]) {
                parent[i] = v;
                dfs(i);
            }
        }
    }
}
