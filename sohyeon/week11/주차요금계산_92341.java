package week11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 주차요금계산_92341 {
	class Car implements Comparable<Car> {
		String num;
		List<Integer> ins = new ArrayList<>();
		List<Integer> outs = new ArrayList<>();
		int totalFee = 0;

		Car (String num) {
			this.num = num;
		}

		@Override
		public int compareTo(Car c) {
			return this.num.compareTo(c.num);
		}
	}

	int time = 0; // 기본 시간
	int fee = 0; // 기본 요금
	int unitTime = 0; // 단위 시간
	int unitFee = 0; // 단위 요금
	Map<String, Car> map = new HashMap<>();

	public int[] solution(int[] fees, String[] records) {
		time = fees[0];
		fee = fees[1];
		unitTime = fees[2];
		unitFee = fees[3];

		for (String record : records) {
			String[] arr = record.split(" ");
			if (!map.containsKey(arr[1])) {
				map.put(arr[1], new Car(arr[1]));
			}
			if (arr[2].equals("IN")) {
				map.get(arr[1]).ins.add(minute(arr[0]));
			} else { // out
				map.get(arr[1]).outs.add(minute(arr[0]));
			}
		}

		List<Car> al = new ArrayList<>();

		for (String key : map.keySet()) {
			Car car = map.get(key);
			int totalTime = 0;
			for (int i = 0; i < car.ins.size(); i++) {
				int temp = (car.outs.size() <= i) ? minute("23:59") : car.outs.get(i);
				temp -= car.ins.get(i);
				totalTime += temp;
			}
			car.totalFee = calFee(totalTime);
			al.add(car);
		}

		int[] answer = new int[al.size()];

		Collections.sort(al);
		for (int i = 0; i < al.size(); i++) {
			answer[i] = al.get(i).totalFee;
		}

		return answer;
	}

	private int calFee(int minute) {
		if (minute <= time) {
			return fee;
		}

		int totalFee = fee;
		minute -= time;

		totalFee += (minute / unitTime) * unitFee;
		if (minute % unitTime != 0) {
			totalFee += unitFee;
		}

		return totalFee;
	}

	private int minute(String str) {
		String[] arr = str.split(":");
		int hour = Integer.valueOf(arr[0]);
		int minute = Integer.valueOf(arr[1]);
		return hour * 60 + minute;
	}
}
