import java.util.*;

public class Solution {
	public int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];

		for (int i = 0; i < commands.length; i++) {
			int s = commands[i][0];
			int e = commands[i][1];
			int k = commands[i][2];
			answer[i] = find(array, s, e, k);
		}

		return answer;
	}

	private int find(int[] arr, int s, int e, int k) {
		List<Integer> al = new ArrayList<>();
		for (int i = s - 1; i <= e - 1; i++) {
			al.add(arr[i]);
		}
		Collections.sort(al);
		return al.get(k - 1);
	}
}
