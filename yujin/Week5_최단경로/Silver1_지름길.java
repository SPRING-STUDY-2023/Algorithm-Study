package Week5_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Silver1_지름길 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        List<Node> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(end - start <= weight) continue;
            if(end > start) continue;
            list.add(new Node(start, end, weight));
        }
        list.sort(new Comparator<Node>() {
            public int compare(Node o1, Node o2) {
                if(o1.start == o2.start) {
                    if(o1.end == o2.end) return o1.weight - o2.weight;
                    return o1.end - o2.end;
                }
                return o1.start - o2.start;
            }
        });
        int[] distance = new int[10001];
        for(int i = 0; i < 10001; i++) {
            distance[i] = i;
        }
        int prev = 0;
        for(int i = 0; i < n; i++) {
            Node now = list.get(i);
            int start = now.start;
            int end = now.end;
            int weight = now.weight;
            prev = distance[start];
            if((distance[end] > (prev + weight)) && end <= d) {
                distance[end] = prev + weight;
                for(int j = end + 1; j <= d; j++) {
                    distance[j] = Math.min(distance[j], prev + weight + j - end);
                }
            }
        }
        System.out.println(distance[d]);
    }

//    static int N, D, distance[], INF = Integer.MAX_VALUE;
//    static List<Node> graph[];
//    static StringTokenizer tokens;
//    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//
//    public static void main(String[] args) throws NumberFormatException, IOException {
//        //input = new BufferedReader(new StringReader(src));
//        distance = new int[10001];
//        graph = new List[10001];
//        for(int i=0; i<graph.length; i++) {
//            distance[i] = i;
//            graph[i] = new ArrayList<>();
//        }
//
//        tokens = new StringTokenizer(input.readLine());
//        N = Integer.parseInt(tokens.nextToken());
//        D = Integer.parseInt(tokens.nextToken());
//        for(int n=0; n<N; n++) {
//            tokens = new StringTokenizer(input.readLine());
//            int start = Integer.parseInt(tokens.nextToken());
//            int end = Integer.parseInt(tokens.nextToken());
//            int d = Integer.parseInt(tokens.nextToken());
//            graph[start].add(new Node(end, d));
//        }
//        dijkstra(0);
//
//        System.out.println(distance[D]);
//    }
//    private static void dijkstra(int start) {
//        if(start > D) {
//            return;
//        }
//        if(distance[start+1] > distance[start] + 1) {
//            distance[start+1] = distance[start] + 1;
//        }
//
//        for(int i=0; i<graph[start].size(); i++) {
//            if(distance[start] + graph[start].get(i).value < distance[graph[start].get(i).endPoint]) {
//                distance[graph[start].get(i).endPoint] = distance[start] + graph[start].get(i).value;
//            }
//        }
//        dijkstra(start+1);
//    }

    static class Node {
        int start;
        int end;
        int weight;
        Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

}
