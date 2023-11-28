package week10;

public class 광고삽입_72414 {
	public String solution(String play_time, String adv_time, String[] logs) {
		int playTime = transferToSec(play_time);
		int advTime = transferToSec(adv_time);
		int[] viewAmounts = new int[playTime + 1];

		for (String log : logs) {
			String[] arr = log.split("-");
			int startSec = transferToSec(arr[0]);
			int endSec = transferToSec(arr[1]);
			for (int i = startSec; i < endSec; i++) {
				viewAmounts[i]++;
			}
		}

		long maxView = 0;
		for (int i = 0; i < advTime; i++) {
			maxView += viewAmounts[i];
		}

		long nowView = maxView;
		int answer = 0;
		for (int i = 0, j = advTime; j < playTime; i++, j++) {
			nowView = nowView - viewAmounts[i] + viewAmounts[j]; // (i+1)초 ~ j초 구간 시청기록
			if (nowView > maxView) {
				maxView = nowView;
				answer = i + 1;
			}
		}

		return secToStr(answer);
	}

	private String secToStr(int sec) {
		String hour = (sec/3600) > 9 ? String.valueOf(sec/3600) : "0" + (sec/3600);
		sec %= 3600;

		String minute = (sec/60) > 9 ? String.valueOf(sec/60) : "0" + (sec/60);
		sec %= 60;

		String second = sec > 9 ? String.valueOf(sec) : "0" + sec;

		return hour + ":" + minute + ":" + second;
	}

	private int transferToSec(String time) {
		// time = HH:mm:ss
		String[] arr = time.split(":");
		int hour = Integer.valueOf(arr[0]) * 3600;
		int minute = Integer.valueOf(arr[1]) * 60;
		int second = Integer.valueOf(arr[2]);

		return hour + minute + second;
	}
}
