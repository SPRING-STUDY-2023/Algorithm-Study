import java.util.PriorityQueue;
import java.util.Scanner;

class 네트워크연결_1922 {
    static class Com implements Comparable<Com> {
        int node1;
        int node2;
        int cost;

        Com(int n1, int n2, int c) {
            node1 = n1;
            node2 = n2;
            cost = c;
        }

        // cost를 기준으로 오름차순 정렬
        @Override
        public int compareTo(Com c) {
            return Integer.compare(this.cost, c.cost);
        }
    }

    static PriorityQueue<Com> pq = new PriorityQueue<>();
    static int[] computer;
    static int n, m;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        init();

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            pq.add(new Com(a, b, c));
        }

        int result = 0;
        int cnt = 0;

        while (!pq.isEmpty()) {
            if (cnt == n - 1)
                break;
            // 최소 신장 트리 찾기 (크루스칼 알고리즘)
            Com now = pq.poll();

            // 두 노드가 연결되어 있지 않으면
            if (find(now.node1) != find(now.node2)) {
                union_com(now.node1, now.node2);
                result += now.cost;
                cnt++;
            }
        }

        System.out.println(result);
    }

    static void init() {
        computer = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            computer[i] = i;
        }
    }

    static int find(int n) {
        if (computer[n] == n)
            return n;
        else
            return computer[n] = find(computer[n]);
    }

    static void union_com(int a, int b) {
        computer[find(a)] = find(b);
    }
}

