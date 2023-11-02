package Week7_greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class G5_치킨배달 {
    static int[][] map;
    static ArrayList<Node> chickenList = new ArrayList<>();
    static ArrayList<Node> houseList = new ArrayList<>();
    static boolean[] chickenVisited;
    static int min = Integer.MAX_VALUE;
    static int n,m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) houseList.add(new Node(j,i));
                if(map[i][j] == 2) chickenList.add(new Node(j,i));
            }
        }
        chickenVisited = new boolean[chickenList.size()];
        backtracking(0,0);
        System.out.println(min);
    }
    public static void backtracking(int count, int idx) {
        if(count == m) {
            int total = 0;
            for(int i = 0; i < houseList.size(); i++) {
                int sum = Integer.MAX_VALUE;
                for(int j = 0; j < chickenList.size(); j++) {
                    if(chickenVisited[j]) {
                        int dist = Math.abs(houseList.get(i).x - chickenList.get(j).x)
                                + Math.abs(houseList.get(i).y - chickenList.get(j).y);
                        sum = Math.min(sum, dist);
                    }
                }
                total += sum;
            }
            min = Math.min(total, min);
            return;
        }
        for(int i = idx; i < chickenList.size(); i++) {
            if(!chickenVisited[i]) {
                chickenVisited[i] = true;
                backtracking(count + 1, idx + 1);
                chickenVisited[i] = false;
            }
        }
    }
    public static class Node{
        int x,y;
         Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
