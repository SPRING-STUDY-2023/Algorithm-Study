package week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 킹_1063 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Map<String, int[]> map = new HashMap<>();
		map.put("R", new int[] {0, 1});
		map.put("L", new int[] {0, -1});
		map.put("B", new int[] {1, 0});
		map.put("T", new int[] {-1, 0});
		map.put("RT", new int[] {-1, 1,});
		map.put("LT", new int[] {-1, -1});
		map.put("RB", new int[] {1, 1});
		map.put("LB", new int[] {1, -1});

		String king = st.nextToken();
		String rock = st.nextToken();
		int N = Integer.parseInt(st.nextToken());

		int kingCol = 9 - Integer.parseInt(String.valueOf(king.charAt(1)));
		int kingRow = king.charAt(0) - 'A';

		int rockCol = 9 - Integer.parseInt(String.valueOf(rock.charAt(1)));
		int rockRow = rock.charAt(0) - 'A';

		// System.out.println(kingCol + " " + kingRow);

		while (N-- > 0) {
			String str = br.readLine();
			int[] dist = map.get(str);

			// System.out.println(getPos(kingCol, kingRow) + " " + getPos(rockCol, rockRow) + " " + str);

			int nextCol = kingCol + dist[0];
			int nextRow = kingRow + dist[1];

			if (0 < nextCol && nextCol <= 8 && 0 <= nextRow && nextRow <= 'H' - 'A') {
				// System.out.println("test");
				if (nextCol == rockCol && nextRow == rockRow) {
					// System.out.println("test");
					int nextRockCol = rockCol + dist[0];
					int nextRockRow = rockRow + dist[1];
					// System.out.println(nextRockCol + " " + nextRockRow);
					if (0 < nextRockCol && nextRockCol <= 8 && 0 <= nextRockRow && nextRockRow <= 'H' - 'A') {
						kingCol = nextCol;
						kingRow = nextRow;
						rockCol = nextRockCol;
						rockRow = nextRockRow;
					}
				} else {
					kingCol = nextCol;
					kingRow = nextRow;
				}
			}
		}

		System.out.println(getPos(kingCol, kingRow));
		System.out.println(getPos(rockCol, rockRow));
	}

	private static String getPos(int col, int row) {
		return (char)(row + 'A') + String.valueOf(9 - col);
	}
}
