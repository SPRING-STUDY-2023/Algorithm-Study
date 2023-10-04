import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class N과M_15661 {
    static int list[];
    static int listvalue[];
    static boolean visit[];
    static int n, m;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new int[9];
        visit = new boolean[9];
        listvalue = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            listvalue[i] = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        Arrays.sort(listvalue);

        dfs(0);

        bw.write(sb.toString());

        bw.close();
        br.close();
    }


    private static void dfs(int cnt) {
        if (cnt == m) {
            for (int i = 0; i < m; i++) {
                sb.append(list[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int saveValue=-1;
        for (int i = 1; i <= n; i++) {
            if (visit[i] || saveValue==listvalue[i-1])
                continue;
            visit[i] = true;
            list[cnt] = listvalue[i - 1];
            saveValue = listvalue[i - 1];
            dfs(cnt + 1);
            visit[i] = false;
        }
    }

}