package week13;

public class 수레움직이기_250134 {
	class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	final int MAX = Integer.MAX_VALUE;

	int[][] map;
	boolean redEnd, blueEnd;
	int[] dx = {-1,1,0,0};
	int[] dy = {0,0,-1,1};
	boolean[][][] visited;

	public int solution(int[][] maze) {
		Point cntRed = null;
		Point cntBlue = null;

		map = new int[maze.length][maze[0].length];
		visited = new boolean[maze.length][maze[0].length][2];

		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				map[i][j] = maze[i][j];

				if (maze[i][j] == 1) cntRed = new Point(i, j);
				else if (maze[i][j] == 2) cntBlue = new Point(i, j);
			}
		}

		// 0: red, 1: blue
		visited[cntRed.x][cntRed.y][0] = true;
		visited[cntBlue.x][cntBlue.y][1] = true;

		int answer = bt(cntRed, cntBlue, 0);
		return (answer == MAX) ? 0 : answer;
	}

	int bt(Point red, Point blue, int result) {
		if (redEnd && blueEnd) return result;

		int answer = MAX;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				Point nRed = !redEnd ? getNext(red.x, red.y, i) : red;
				Point nBlue = !blueEnd ? getNext(blue.x, blue.y, j) : blue;

				if (!isPossible(red, nRed, blue, nBlue)) continue;

				visited[nRed.x][nRed.y][0] = true;
				visited[nBlue.x][nBlue.y][1] = true;

				if (map[nRed.x][nRed.y] == 3) redEnd = true;
				if (map[nBlue.x][nBlue.y] == 4) blueEnd = true;

				answer = Math.min(answer, bt(nRed, nBlue, result + 1));

				// 백트래킹이므로 변수 초기화
				redEnd = false;
				blueEnd = false;
				visited[nRed.x][nRed.y][0] = false;
				visited[nBlue.x][nBlue.y][1] = false;
			}
		}

		return answer;
	}

	Point getNext(int x, int y, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		return new Point(nx, ny);
	}

	boolean isPossible(Point cntRed, Point red, Point cntBlue, Point blue) {
		if (0 <= red.x && red.x < map.length && 0 <= red.y && red.y < map[0].length) {
			if (0 <= blue.x && blue.x < map.length && 0 <= blue.y && blue.y < map[0].length) {
				if (map[red.x][red.y] == 5 || map[blue.x][blue.y] == 5) return false;
				if ((cntRed.x == blue.x && cntRed.y == blue.y) && (cntBlue.x == red.x && cntBlue.y == red.y)) return false;
				if ((!redEnd && visited[red.x][red.y][0]) || (!blueEnd && visited[blue.x][blue.y][1])) return false;
				if (red.x == blue.x && red.y == blue.y) return false;
				return true;
			}
		}
		return false;
	}
}