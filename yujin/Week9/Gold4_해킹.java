package Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Gold4_해킹 {
    static int n,d,c;
    static int[] distance;
    static ArrayList<ArrayList<Node>> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;

        while(t-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            map = new ArrayList<>();
            for(int i = 0; i <= n; i++) {
                map.add(new ArrayList<>());
            }

            for(int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                map.get(b).add(new Node(a,s));
            }
            dijkstra(c);

            int total = 0, count = 0;
            for(int i = 0; i < distance.length; i++) {
                if(distance[i] != Integer.MAX_VALUE) {
                    count++;
                    total = Math.max(distance[i], total);
                }
            }
            System.out.println(count + " " + total);
        }
    }
    public static void dijkstra(int start) {
        distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            int nowV = now.v;
            if(distance[nowV] < now.weight) continue;
            for(Node node : map.get(nowV)) {
                if(node.weight + distance[nowV] < distance[node.v]) {
                    distance[node.v] = node.weight + distance[nowV];
                    pq.add(new Node(node.v, distance[node.v]));
                }
            }
        }
    }
    public static class Node implements Comparable<Node> {
        int v, weight;
        Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
