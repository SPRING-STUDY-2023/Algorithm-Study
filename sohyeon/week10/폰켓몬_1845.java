import java.util.*;

public class Solution {
	public int solution(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		return map.size() >= (nums.length / 2) ? (nums.length / 2) : map.size();
	}
}
