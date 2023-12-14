import java.util.*;

public class Solution {
	public int solution(String[][] board, int h, int w) {
		int answer = 0;
		int n = board.length;
		int m = board[0].length;
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};

		for (int i = 0; i < 4; i++) {
			int nh = h + dx[i];
			int nw = w + dy[i];
			if (0 <= nh && nh < n && 0 <= nw && nw < m) {
				if (board[h][w].equals(board[nh][nw])) {
					answer++;
				}
			}
		}

		return answer;
	}
}
