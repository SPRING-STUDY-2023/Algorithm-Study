package Week7_greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class S3_킹 {


    static int kingx ;
    static int kingy ;
    static int stonex;
    static int stoney;

    static boolean check(int i, int j) {
        if(1<=i&&i<=8 && 1<=j&&j<=8)return true;
        return false;
    }
    public static void main(String[] args) throws IOException {
        Map<String, int[]> move = new HashMap<>();
        move.put("R", new int[]{1,0});
        move.put("L", new int[]{-1,0});
        move.put("B", new int[]{0,-1});
        move.put("T", new int[]{0,1});
        move.put("RT", new int[]{1,1});
        move.put("LT", new int[]{-1,1});
        move.put("RB", new int[]{1,-1});
        move.put("LB", new int[]{-1,-1});

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String king = st.nextToken();
        kingx = king.charAt(1)-'0';
        kingy = king.charAt(0)-'A'+1;

        String stone = st.nextToken();
        stonex = stone.charAt(1)-'0';
        stoney = stone.charAt(0)-'A'+1;
        int movenum = Integer.parseInt(st.nextToken());

        while(movenum-- > 0) {
            int[] next = move.get(br.readLine());
            move(next[1],next[0]);
        }
        StringBuilder sb = new StringBuilder();
        sb.append((char)('A'+kingy -1)).append(kingx).append("\n").append((char)('A'+stoney -1)).append(stonex);
        System.out.println(sb.toString());
    }
    static void move(int di, int dj) {

        int kni = kingx+di;
        int knj = kingy+dj;

        if(check(kni, knj)) {
            if(kni==stonex && knj==stoney) {

                if(check(stonex+di, stoney+dj)) {
                    stonex+=di; stoney+=dj;
                    kingx = kni; kingy = knj;
                }
            }
            else {
                kingx = kni; kingy = knj;
            }
        }
    }
}
