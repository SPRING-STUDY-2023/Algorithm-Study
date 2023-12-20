package week13;

import java.util.HashMap;
import java.util.Map;

public class 붕대감기_250137 {
	Map<Integer, Integer> attackInfo = new HashMap<>(); // 공격 시점 정보
	int curHealth; // 현재 체력
	int endTime; // 마지막 공격 시간
	int skillTime = 0; // 지속 시간

	public int solution(int[] bandage, int health, int[][] attacks) {
		curHealth = health; // 현재 체력 초기화

		/* 공격 시점 정보 추가 & 마지막 공격 시간 */

		for (int[] attack : attacks) {
			attackInfo.put(attack[0], attack[1]);
			endTime = attack[0];
		}

		/* 1~endTime 초 동안 회복하거나 공격 당하거나 */

		for (int i = 1; i <= endTime; i++) {
			if (attackInfo.containsKey(i)) { // 공격 당하는 시점이면
				curHealth -= attackInfo.get(i); // 데미지
				skillTime = 0; // 지속시간 초기화
			} else { // 공격 없으므로 회복
				curHealth += bandage[1]; // 회복
				skillTime++; // 지속 시간++

				if (skillTime == bandage[0]) { // 지속시간 채워졌을 때
					curHealth += bandage[2]; // 일정 회복
					skillTime = 0; // 지속시간 초기화
				}

				if (curHealth > health) { // 기존 체력보다 넘어가면 조정
					curHealth = health;
				}
			}

			if (curHealth <= 0) { // 체력 없으면 끝
				return -1;
			}
		}

		return curHealth;
	}
}
