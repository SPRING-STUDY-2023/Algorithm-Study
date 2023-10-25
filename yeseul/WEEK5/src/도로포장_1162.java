import java.io.*;
import java.util.*;
public class 도로포장_1162 {
    static final long INF = Long.MAX_VALUE;
    static ArrayList<Road>[] graph;
    static long[][] dist;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        dist = new long[N + 1][K + 1]; //최단 거리를 저장하는 배열로, 2차원 배열이며 [i][j]는 i번 노드에 도로를 j번 포장하며 도달할 때의 최단 거리

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dist[i], INF);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());
            graph[from].add(new Road(to, cost, 0));
            graph[to].add(new Road(from, cost, 0));
        }

        dijkstra();

        long answer = INF;
        for (int k = 0; k <= K; k++) {
            if (dist[N][k] != INF) {
                answer = Math.min(answer, dist[N][k]);
            }
        }

        System.out.println(answer);
    }

    static void dijkstra() {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        pq.add(new Road(1, 0, 0));
        dist[1][0] = 0;

        while (!pq.isEmpty()) {
            Road cur = pq.poll();
            int curNode = cur.to;
            long curCost = cur.cost;
            int curCnt = cur.cnt;

            if (dist[curNode][curCnt] < curCost) continue; // dist[curNode][curCnt]가 현재까지의 비용(curCost)보다 크다면, 이미 더 짧은 경로가 존재하는 것이므로 무시

            //현재 지점에서 다음 지점으로 갈 때, 도로를 추가로 포장하는 경우와 포장하지 않는 경우를 고려
            for (Road nextRoad : graph[curNode]) {
                int nextNode = nextRoad.to;
                long nextCost = nextRoad.cost;

                //현재 포장한 도로의 개수 curCnt가 아직 허용 가능한 포장 횟수 K보다 작고, 다음 지점으로 가는데 curCnt번 도로를 포장한 경로보다 더 짧은 경로가 발견되었을 때
                if (curCnt < K && dist[nextNode][curCnt + 1] > dist[curNode][curCnt]) {
                    dist[nextNode][curCnt + 1] = dist[curNode][curCnt]; // 다음 지점으로 가는데 curCnt + 1번 도로를 포장하면 더 효율적인 경로가 될 수 있음을 의미함
                    pq.add(new Road(nextNode, dist[nextNode][curCnt + 1], curCnt + 1));
                }
                //다음 지점으로 가는데 현재 포장한 도로의 개수 curCnt를 유지하면서, 현재까지의 최단 경로보다 더 짧은 경로가 발견되었을 때
                if (dist[nextNode][curCnt] > dist[curNode][curCnt] + nextCost) {
                    dist[nextNode][curCnt] = dist[curNode][curCnt] + nextCost;
                    pq.add(new Road(nextNode, dist[nextNode][curCnt], curCnt));
                }
            }
        }
    }

    static class Road implements Comparable<Road> {
        int to; //다음 지점
        long cost; //현재 지점에서 다음 지점까지의 비용
        int cnt; //현재까지 포장한 도로의 개수

        public Road(int to, long cost, int cnt) {
            this.to = to;
            this.cost = cost;
            this.cnt = cnt;
        }

        /**
         * compareTo 메소드는 Comparable 인터페이스를 구현한 메소드로, 다른 Road 객체와 비교하여 비용(cost)에 따른 우선순위를 정의한다.
         * 이 메소드는 우선순위 큐에서 요소를 정렬할 때 사용되며, 비용이 작은 순서대로 요소가 정렬된다.
         */
        @Override
        public int compareTo(Road o) {
            return (int) (this.cost - o.cost);
        }
    }
}
