package week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 트리순회_1991 {
	static int[][] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(br.readLine());

		graph = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char node = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);

			graph[node - 'A'][0] = left != '.' ? left - 'A' : -1;
			graph[node - 'A'][1] = right != '.' ? right - 'A' : -1;
		}

		preOrder(0);
		System.out.println();
		inOrder(0);
		System.out.println();
		postOrder(0);
	}

	private static void preOrder(int node) {
		if (node == -1) {
			return;
		}
		System.out.print((char) (node + 'A'));
		preOrder(graph[node][0]);
		preOrder(graph[node][1]);
	}

	private static void inOrder(int node) {
		if (node == -1) {
			return;
		}
		inOrder(graph[node][0]);
		System.out.print((char) (node + 'A'));
		inOrder(graph[node][1]);
	}

	private static void postOrder(int node) {
		if (node == -1) {
			return;
		}
		postOrder(graph[node][0]);
		postOrder(graph[node][1]);
		System.out.print((char) (node + 'A'));
	}
}
