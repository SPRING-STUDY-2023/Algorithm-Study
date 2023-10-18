import java.util.*;

public class 파티_1238 {
    static final int INF = 1000000000;
    static List<int[]>[] graph;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int X = sc.nextInt();

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int time = sc.nextInt();
            graph[start].add(new int[]{end, time});
        }

        int[] XtoHome = dijkstra(N, X); // 파티 장소로부터 각 마을로 돌아오는 최단거리
        int maxTime = 0;

        for (int i = 1; i <= N; i++) {
            int[] HomeToX = dijkstra(N, i);
            maxTime = Math.max(maxTime, XtoHome[i] + HomeToX[X]); // 파티 장소에서 i로 가는 최단거리와 i에서 파티 장소로 돌아오는 최단거리 합산
        }

        System.out.println(maxTime);
    }

    static int[] dijkstra(int N, int start) { //start 마을에서 각 마을까지의 최단거리 구하기
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int current = cur[0];
            int distance = cur[1];

            if (distance > dist[current]) continue;

            for (int[] neighbor : graph[current]) {
                int next = neighbor[0];
                int nextDistance = distance + neighbor[1];

                if (nextDistance < dist[next]) {
                    dist[next] = nextDistance;
                    pq.add(new int[]{next, nextDistance});
                }
            }
        }

        return dist;
    }
}
